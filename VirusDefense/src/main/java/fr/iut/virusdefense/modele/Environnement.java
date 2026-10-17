package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.apparition.Niveau;
import fr.iut.virusdefense.modele.apparition.PointApparition;
import fr.iut.virusdefense.modele.carte.Carte;
import fr.iut.virusdefense.modele.carte.LecteurDeCarte;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.cellules.attaques.Projectile;
import fr.iut.virusdefense.modele.cellules.attaques.Rayon;
import fr.iut.virusdefense.modele.cellules.attaques.Zone;
import fr.iut.virusdefense.modele.deplacement.Deplacement;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.StatutPartie;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Environnement {

    private final Carte carte;

    private final Deplacement deplacement;

    private final Joueur joueur;

    private final Niveau niveau;

    private final ArrayList<Alteration> alterations;

    private final ObservableList<Maladie> maladies;

    private final ObservableList<Rayon> rayons;

    private final ObservableList<Zone> zones;

    private final ObservableList<Projectile> projectiles;

    private final ObjectProperty<StatutPartie> statutPartieProperty;

    /**
     * Créé un terrain sans maladies
     */
    public Environnement(String idNiveau) {
        maladies = FXCollections.observableArrayList();
        rayons = FXCollections.observableArrayList();
        zones =  FXCollections.observableArrayList();
        projectiles = FXCollections.observableArrayList();
        carte = new LecteurDeCarte(this, idNiveau).creer();
        deplacement = new Deplacement(carte);
        joueur = new Joueur();
        niveau = new Niveau(this, idNiveau);
        alterations = new ArrayList<>();
        statutPartieProperty = new SimpleObjectProperty<>(StatutPartie.PASTERMINEE);
    }

    public Carte getCarte() {
        return carte;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public Deplacement getDeplacement() {
        return deplacement;
    }

    public ObservableList<Maladie> getMaladies() {
        return maladies;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public ArrayList<Alteration> getAlterations() {
        return alterations;
    }

    public ObservableList<Rayon> getRayons() {
        return rayons;
    }

    public ObservableList<Zone> getZones() {
        return zones;
    }

    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }

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
        maladies.add(m);
    }

    public void ajouterSiConforme(Cellule c){
        if (c != null && joueur.getPc() >= c.getCout()) {
            carte.getCellules().add(c);
            joueur.retirerPc(c.getCout());
            deplacement.faireAlgo();

            if (maladieOuPointsApparitionsBloques()) {
                retirerCellule(c, true);
                deplacement.faireAlgo();
            }
        }
    }

    public void retirerCellule(Cellule c, boolean rendrePC){
        carte.getCellules().remove(c);

        if (rendrePC)
            joueur.ajouterPc(c.getCout());

        deplacement.faireAlgo();
    }

    public void retirerCelluleALEmplacement(int ligne, int colonne, boolean rendrePC){
        int i=0;
        boolean trouve = false;

        while (!trouve && i < carte.getCellules().size()){
            if ((int)carte.getCellules().get(i).getLigne() == ligne && (int)carte.getCellules().get(i).getColonne() == colonne){
                trouve = true;
                retirerCellule(carte.getCellules().get(i), rendrePC);
            }
            i++;
        }
        deplacement.faireAlgo();
    }

    public void ajouterRayon(Rayon r){
        rayons.add(r);
    }

    public void ajouterProjectile(Projectile p){
        projectiles.add(p);
    }

    public void ajouterZone(Zone z){
        zones.add(z);
    }

    /**
     * La méthode qui s'éxécute à chaque tour
     */
    public void unTour() {
        if (getStatutPartie() == StatutPartie.PASTERMINEE) {
            if (joueur.getPv() > 0 && (!niveau.estTermine() || !maladies.isEmpty())) {
                for (int i = rayons.size() - 1; i >= 0; i--)
                    if (rayons.get(i).aDepasseAgeMaximal())
                        rayons.remove(i);

                for (int i = zones.size() - 1; i >= 0; i--)
                    if (zones.get(i).aDepasseAgeMaximal())
                        zones.remove(i);

                for (int i = projectiles.size() - 1; i >= 0; i--)
                    if (projectiles.get(i).getCibleTouché())
                        projectiles.remove(i);

                for (int i = alterations.size() - 1; i >= 0; i--)
                    if (alterations.get(i).estFinie())
                        alterations.remove(i);

                for (Rayon r : rayons)
                    r.agir();

                for (Zone z : zones)
                    z.agir();

                for(Projectile p : projectiles)
                    p.agir();

                for (Cellule c : carte.getCellules())
                    c.agir();

                niveau.update();

                for (PointApparition p : carte.getPointsApparitions())
                    p.agir();

                for (Alteration alt : alterations) {
                    alt.agir();
                }

                for (int i = maladies.size() - 1; i >= 0; i--) {
                    maladies.get(i).agir();
                    if (!maladies.get(i).estVivant()) {
                        maladies.get(i).capaciteALaMort();
                        if (!maladies.get(i).aAtteintLObjectif())
                            joueur.ajouterPc(maladies.get(i).getRecompense());
                        maladies.remove(i);
                    }
                }
            }
            else{
                if(joueur.getPv()>0)
                    setStatutPartie(StatutPartie.GAGNEE);
                else
                    setStatutPartie(StatutPartie.PERDUE);
            }
        }
    }

    public boolean maladiesBloquees(){
        int i = 0;
        while( i < getMaladies().size()){
            if(getDeplacement().estBloquee(getMaladies().get(i).position()))
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
        for (PointApparition pointApparition : carte.getPointsApparitions()) {
            if(deplacement.estBloquee(pointApparition.position()))
                return true;
        }
        return false;
    }

    public boolean maladieOuPointsApparitionsBloques(){
        return maladiesBloquees() || pointsApparitionBloques();
    }

    public void toutVider(){
        maladies.clear();
        zones.clear();
        rayons.clear();
        projectiles.clear();
    }
}
