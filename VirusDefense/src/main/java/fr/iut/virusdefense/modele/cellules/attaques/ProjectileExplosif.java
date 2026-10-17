package fr.iut.virusdefense.modele.cellules.attaques;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.Reconnaissance;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class ProjectileExplosif extends Projectile {
    private final double rayonZonePortee;
    private final double degatsInstantane;
    private final double rayonInstantane;
    private final int tempsZone;

    public ProjectileExplosif(Environnement environnement, Coordonees coordonees, Maladie cible, double degats, List<Alteration> alterations, double rayonZonePortee, double degatsInstantane, double rayonInstantane, int tempsZone) {
        super(environnement, new Coordonees(coordonees), cible, degats, alterations);
        this.rayonZonePortee = rayonZonePortee;
        this.degatsInstantane = degatsInstantane;
        this.rayonInstantane = rayonInstantane;
        this.tempsZone = tempsZone;
    }

    @Override
    public void attaquer(){
        Reconnaissance reconnaissance = new RecSimple(getEnvironnement(), getCoordonees(), rayonZonePortee, Integer.MAX_VALUE);
        Zone explosion = new ZoneSimple(getEnvironnement(), getCoordonees(), getCibles(), degatsInstantane, 30, getAlterations(), rayonInstantane);
        getEnvironnement().ajouterZone(explosion);
        Zone explosionDuree = new ZonePersistante(getEnvironnement(), getCoordonees(), getCibles(), getDegats(), tempsZone, getAlterations(), rayonZonePortee, reconnaissance);
        getEnvironnement().ajouterZone(explosionDuree);
    }


}
