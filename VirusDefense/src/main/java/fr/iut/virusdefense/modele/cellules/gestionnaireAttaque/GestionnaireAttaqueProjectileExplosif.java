package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaques.ProjectileExplosif;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class GestionnaireAttaqueProjectileExplosif extends GestionnaireAttaque {
    private int tempsZone;
    private final double rayonZonePortee;
    private final double degatsInstantane;
    private final double rayonInstantane;


    public GestionnaireAttaqueProjectileExplosif(Environnement environnement, Coordonees coordonees, double degats, ArrayList<Maladie> cibles, double rayonZonePortee, double degatsInstantane, double rayonInstantane , int tempsZone){
        super(environnement, coordonees, degats, cibles);
        this.tempsZone = tempsZone;
        this.rayonZonePortee = rayonZonePortee;
        this.degatsInstantane = degatsInstantane;
        this.rayonInstantane = rayonInstantane;
    }

    public int getTempsZone() {
        return tempsZone;
    }

    public void setTempsZone(int tempsZone) {
        this.tempsZone = tempsZone;
    }

    public void attaque(Maladie m){
        getEnvironnement().ajouterProjectile(new ProjectileExplosif(getEnvironnement(), getCoordonees(), m, getDegats(), getAlterations(),rayonZonePortee,degatsInstantane,rayonInstantane, tempsZone));
    }

    public void attaqueCibles(){
        getCibles().forEach(this::attaque);
    }
}
