package fr.iut.virusdefense.modele.cellules.attaques;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.ActeurRetirable;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Positionnable;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public abstract class Attaque extends Positionnable implements ActeurRetirable {
    private List<Maladie> cibles;
    private final Alteration alteration;
    private final double degats;

    public Attaque(Environnement environnement, Coordonees coordonees, double degats, Alteration alteration, Maladie cible){
        this(environnement, coordonees, degats, alteration, List.of(cible));
    }

    public Attaque(Environnement environnement, Coordonees coordonees, double degats, Alteration alteration, List<Maladie> cibles) {
        super(environnement, coordonees);
        this.degats = degats;
        this.alteration = alteration;
        this.cibles = cibles;
    }

    public double getDegats(){
        return degats;
    }

    public Alteration getAlteration() {
        return alteration;
    }

    public List<Maladie> getCibles(){
        return cibles;
    }

    public void setCibles(List<Maladie> cibles) {
        this.cibles = cibles;
    }

    public final void infligerDegats() {
        for (Maladie cible : cibles){
            cible.prendreDegats(getDegats());
        }
    }

    public final void donnerAlterations() {
        for (Maladie cible : cibles) {
            if (cible.peutSubirAlteration() && getAlteration() != null) {
                Alteration copie = getAlteration().copy();
                copie.setMaladie(cible);
                getEnvironnement().getGestionnaireActeur().getAlterations().add(copie);
            }
        }
    }

    public void attaquer(){
        infligerDegats();
        donnerAlterations();
    }
}
