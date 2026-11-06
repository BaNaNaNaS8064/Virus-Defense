package fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.utilitaires.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.positionnables.cellules.attaques.ProjectileExplosif;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;

public class GestionnaireAttaqueProjectileExplosif extends GestionnaireAttaque {
    private int tempsZone;
    private final double rayonZonePortee;
    private final double degatsInstantane;
    private final double rayonInstantane;


    public GestionnaireAttaqueProjectileExplosif(Coordonees coordonees, double degats, ArrayList<Maladie> cibles, double rayonZonePortee, double degatsInstantane, double rayonInstantane , int tempsZone){
        super(coordonees, degats, cibles);
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
        Environnement.getUniqueInstance().ajouterProjectile(new ProjectileExplosif(getCoordonees(), m, getDegats(), getAlteration(),rayonZonePortee,degatsInstantane,rayonInstantane, tempsZone));
    }

    public void attaqueCibles(){
        getCibles().forEach(this::attaque);
    }
}
