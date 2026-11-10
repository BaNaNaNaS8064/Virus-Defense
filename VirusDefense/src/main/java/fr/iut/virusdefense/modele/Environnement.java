package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.acteurs.GestionnaireActeur;
import fr.iut.virusdefense.modele.apparition.Niveau;
import fr.iut.virusdefense.modele.apparition.PointApparition;
import fr.iut.virusdefense.modele.carte.Carte;
import fr.iut.virusdefense.modele.carte.LecteurCarte;
import fr.iut.virusdefense.modele.deplacement.Deplacement;
import fr.iut.virusdefense.modele.enums.Difficulte;
import fr.iut.virusdefense.modele.enums.StatutPartie;
import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.attaques.Projectile;
import fr.iut.virusdefense.modele.positionnables.cellules.attaques.Rayon;
import fr.iut.virusdefense.modele.positionnables.cellules.attaques.Zone;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Joueur;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Environnement {

    private final Carte carte;

    private final Deplacement deplacement;

    private final Joueur joueur;

    private final GestionnaireActeur gestionnaireActeur;

    private final ObjectProperty<StatutPartie> statutPartieProperty;

    private static Environnement uniqueInstance = null;

    /**
     * Créé un terrain sans maladies
     */
    private Environnement(String idNiveau) {
        carte = new LecteurCarte(idNiveau).creer();
        deplacement = new Deplacement(carte);
        joueur = new Joueur();
        gestionnaireActeur = new GestionnaireActeur(carte.getCellules(), carte.getPointsApparitions());
        statutPartieProperty = new SimpleObjectProperty<>(StatutPartie.PASTERMINEE);
    }

    public static Environnement getInstance(){
        return uniqueInstance;
    }

    public static void creerInstance(String idNiveau, Difficulte difficulte){
        uniqueInstance = new Environnement(idNiveau);
        Niveau.creerUniqueInstance(idNiveau, difficulte);
    }

    public Carte getCarte() {
        return carte;
    }

    public Deplacement getDeplacement() {
        return deplacement;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public GestionnaireActeur getGestionnaireActeur() {return gestionnaireActeur;}



    public final StatutPartie getStatutPartie(){
        return statutPartieProperty.getValue();
    }

    public final ObjectProperty<StatutPartie> statutPartieProperty(){
        return statutPartieProperty;
    }

    public final void setStatutPartie(StatutPartie statutPartie){
        statutPartieProperty.setValue(statutPartie);
    }

    /**
     * Ajoute {@code m} à {@code maladies}
     *
     * @param m une maladie à ajouter
     */
    public void ajouterMaladie(Maladie m) {
        gestionnaireActeur.getMaladies().add(m);
    }

    public void ajouterSiConforme(Cellule c){
        if (c != null && joueur.getPc() >= c.getCout()) {
            gestionnaireActeur.getCellules().add(c);
            joueur.retirerPc(c.getCout());
            deplacement.faireAlgo();

            if (maladieOuPointsApparitionsBloques()) {
                retirerCellule(c, true);
                deplacement.faireAlgo();
            }
        }
    }

    public void retirerCellule(Cellule c, boolean rendrePC){
        gestionnaireActeur.getCellules().remove(c);

        if (rendrePC)
            joueur.ajouterPc(c.getCout());

        deplacement.faireAlgo();
    }

    public void retirerCelluleALEmplacement(int ligne, int colonne, boolean rendrePC){
        int i=0;
        boolean trouve = false;

        while (!trouve && i < gestionnaireActeur.getCellules().size()){
            if ((int)gestionnaireActeur.getCellules().get(i).getLigne() == ligne && (int)gestionnaireActeur.getCellules().get(i).getColonne() == colonne){
                trouve = true;
                retirerCellule(gestionnaireActeur.getCellules().get(i), rendrePC);
            }
            i++;
        }
        deplacement.faireAlgo();
    }

    public void ajouterRayon(Rayon r){
        gestionnaireActeur.getRayons().add(r);
    }

    public void ajouterProjectile(Projectile p){
        gestionnaireActeur.getProjectiles().add(p);
    }

    public void ajouterZone(Zone z){
        gestionnaireActeur.getZones().add(z);
    }

    /**
     * La méthode qui s'éxécute à chaque tour
     */
    public void unTour() {
        if (getStatutPartie() == StatutPartie.PASTERMINEE) {
            if (joueur.getPv() <= 0 || (Niveau.getInstance().estTermine() && gestionnaireActeur.getMaladies().isEmpty())) {
                if(joueur.getPv()>0)
                    setStatutPartie(StatutPartie.GAGNEE);
                else
                    setStatutPartie(StatutPartie.PERDUE);
            }
            else{
                Niveau.getInstance().update();

                gestionnaireActeur.acteursARetiré(getJoueur());

                gestionnaireActeur.acteursAgir();
            }
        }
    }

    public boolean maladiesBloquees(){
        int i = 0;
        while( i < gestionnaireActeur.getMaladies().size()){
            if(getDeplacement().estBloquee(gestionnaireActeur.getMaladies().get(i).position()))
                return true;
            i++;
        }
        return false;
    }


    /**
     * Vérifie si les générateurs ont un chemins disponible pour les maladies vers la fin.
     * @return Vrai si bloqué
     */
    public boolean pointsApparitionBloques(){
        for (PointApparition pointApparition : gestionnaireActeur.getPointApparitions()) {
            if(deplacement.estBloquee(pointApparition.position()))
                return true;
        }
        return false;
    }

    public boolean maladieOuPointsApparitionsBloques(){
        return maladiesBloquees() || pointsApparitionBloques();
    }
}
