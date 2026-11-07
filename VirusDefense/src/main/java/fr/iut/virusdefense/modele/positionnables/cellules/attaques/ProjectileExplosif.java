package fr.iut.virusdefense.modele.positionnables.cellules.attaques;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.positionnables.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.Reconnaissance;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage.CiblageProche;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

public class ProjectileExplosif extends Projectile {
    private final double rayonZonePortee;
    private final double degatsInstantane;
    private final double rayonInstantane;
    private final int tempsZone;

    public ProjectileExplosif(Coordonees coordonees, Maladie cible, double degats, Alteration alteration, double rayonZonePortee, double degatsInstantane, double rayonInstantane, int tempsZone) {
        super(new Coordonees(coordonees), cible, degats, alteration);
        this.rayonZonePortee = rayonZonePortee;
        this.degatsInstantane = degatsInstantane;
        this.rayonInstantane = rayonInstantane;
        this.tempsZone = tempsZone;
    }

    @Override
    public void attaquer(){
        Reconnaissance reconnaissance = new ReconnaissanceSimple(getCoordonees(), rayonZonePortee, Integer.MAX_VALUE,new CiblageProche(getCoordonees()));
        Zone explosion = new ZoneSimple(getCoordonees(), getCibles(), degatsInstantane, 30, getAlteration(), rayonInstantane);
        Environnement.getUniqueInstance().ajouterZone(explosion);
        Zone explosionDuree = new ZonePersistante(getCoordonees(), getCibles(), getDegats(), tempsZone, getAlteration(), rayonZonePortee, reconnaissance);
        Environnement.getUniqueInstance().ajouterZone(explosionDuree);
    }


}
