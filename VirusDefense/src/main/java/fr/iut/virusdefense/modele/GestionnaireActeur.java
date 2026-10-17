package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.apparition.PointApparition;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.cellules.attaques.Projectile;
import fr.iut.virusdefense.modele.cellules.attaques.Rayon;
import fr.iut.virusdefense.modele.cellules.attaques.Zone;
import fr.iut.virusdefense.modele.maladies.Maladie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class GestionnaireActeur {
    private final ObservableList<Maladie> maladies;
    private final ObservableList<Rayon> rayons;
    private final ObservableList<Zone> zones;
    private final ObservableList<Projectile> projectiles;
    private final ArrayList<Alteration> alterations;
    private final ArrayList<Cellule> cellules;
    private final ArrayList<PointApparition> pointApparitions;

    public GestionnaireActeur(ArrayList<Cellule> cellules, ArrayList<PointApparition> pointApparitions) {
        maladies = FXCollections.observableArrayList();
        rayons = FXCollections.observableArrayList();
        zones =  FXCollections.observableArrayList();
        projectiles = FXCollections.observableArrayList();
        alterations = new ArrayList<>();
        this.cellules = cellules;
        this.pointApparitions = pointApparitions;
    }

    public ObservableList<Maladie> getMaladies() {
        return maladies;
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
    public ArrayList<Alteration> getAlterations() {
        return alterations;
    }
    public ArrayList<Cellule> getCellules() {
        return cellules;
    }
    public ArrayList<PointApparition> getPointApparitions() {
        return pointApparitions;
    }

    public ArrayList<Acteur> getActeurs() {
        ArrayList<Acteur> acteurs = new ArrayList<>();
        acteurs.addAll(maladies);
        acteurs.addAll(rayons);
        acteurs.addAll(zones);
        acteurs.addAll(projectiles);
        acteurs.addAll(alterations);
        acteurs.addAll(cellules);
        acteurs.addAll(pointApparitions);
        return acteurs;
    }

    public ArrayList<ActeurRetirable> getActeursRetirables() {
        ArrayList<ActeurRetirable> acteurs = new ArrayList<>();
        acteurs.addAll(maladies);
        acteurs.addAll(rayons);
        acteurs.addAll(zones);
        acteurs.addAll(projectiles);
        acteurs.addAll(alterations);
        return acteurs;
    }

    public void acteursAgir(){
        for (Acteur a : getActeurs()){
            a.agir();
        }
    }

    public void acteursARetiré(Joueur j){
        ArrayList<ActeurRetirable> acteurs = getActeursRetirables();
        for (int i = acteurs.size() - 1; i >= 0; i--) {
            ActeurRetirable acteur = acteurs.get(i);

            if (acteur.doitEtreRetiré()) {
                if (acteur instanceof Maladie){
                    Maladie m = (Maladie) acteur;

                    m.capaciteALaMort();

                    if (!m.aAtteintLObjectif())
                        j.ajouterPc(m.getRecompense());

                    getMaladies().remove(m);
                } else if (acteur instanceof Rayon){
                    getRayons().remove((Rayon) acteur);
                } else if (acteur instanceof Zone) {
                    getZones().remove((Zone) acteur);
                } else if (acteur instanceof Projectile) {
                    getProjectiles().remove((Projectile) acteur);
                } else {
                    getAlterations().remove((Alteration) acteur);
                }
            }
        }
    }

    public void toutVider(){
        maladies.clear();
        rayons.clear();
        zones.clear();
        projectiles.clear();
        cellules.clear();
        alterations.clear();
        pointApparitions.clear();
    }
}
