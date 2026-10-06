package fr.iut.virusdefense.modele.cellules.attaques;

import fr.iut.virusdefense.modele.Acteur;
import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Positionnable;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.Tumeur;

import java.util.List;

public abstract class Attaque extends Positionnable implements Acteur {
    private List<Maladie> cibles;
    private final List<Alteration> alterations;
    private final double degats;

    public Attaque(Environnement environnement, Coordonees coordonees, double degats, List<Alteration> alterations, Maladie cible){
        this(environnement, coordonees, degats, alterations, List.of(cible));
    }

    public Attaque(Environnement environnement, Coordonees coordonees, double degats, List<Alteration> alterations, List<Maladie> cibles) {
        super(environnement, coordonees);
        this.degats = degats;
        this.alterations = alterations;
        this.cibles = cibles;
    }

    public double getDegats(){
        return degats;
    }

    public List<Alteration> getAlterations() {
        return alterations;
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
            if (!(cible instanceof Tumeur)) {
                for (Alteration alt : getAlterations()) {
                    alt.setMaladie(cible);
                    getEnvironnement().getAlterations().add(alt);
                }
            }
        }
    }

    public void attaquer(){
        infligerDegats();
        donnerAlterations();
    }
}
