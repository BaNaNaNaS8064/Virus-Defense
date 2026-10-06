package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.apparition.PointApparition;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.cellules.attaques.Attaque;
import fr.iut.virusdefense.modele.cellules.attaques.Projectile;
import fr.iut.virusdefense.modele.cellules.attaques.Rayon;
import fr.iut.virusdefense.modele.cellules.attaques.Zone;
import fr.iut.virusdefense.modele.maladies.Maladie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class GestionnaireActeur {
    private final ArrayList<Alteration> alterations;

    private final ObservableList<Maladie> maladies;

    private final ObservableList<Rayon> rayons;

    private final ObservableList<Zone> zones;

    private final ObservableList<Projectile> projectiles;

    private final ArrayList<Cellule> cellules;

    private final ArrayList<PointApparition> pointApparitions;

    public GestionnaireActeur(ArrayList<Cellule> cellules, ArrayList<PointApparition> pointApparitions) {
        maladies = FXCollections.observableArrayList();
        rayons = FXCollections.observableArrayList();
        zones =  FXCollections.observableArrayList();
        projectiles = FXCollections.observableArrayList();
        alterations = new ArrayList<>();
        this.pointApparitions = pointApparitions;
        this.cellules = cellules;
    }

    public ArrayList<Alteration> getAlterations() {
        return alterations;
    }

    public ArrayList<Cellule> getCellules() {
        return cellules;
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

    public ObservableList<Maladie> getMaladies() {
        return maladies;
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
        acteurs.addAll(cellules);
        acteurs.addAll(alterations);
        acteurs.addAll(pointApparitions);
        return acteurs;
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
