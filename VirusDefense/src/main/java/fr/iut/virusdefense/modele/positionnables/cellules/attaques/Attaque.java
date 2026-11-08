package fr.iut.virusdefense.modele.positionnables.cellules.attaques;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.acteurs.ActeurRetirable;
import fr.iut.virusdefense.modele.positionnables.Positionnable;
import fr.iut.virusdefense.modele.positionnables.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

import java.util.List;

public abstract class Attaque extends Positionnable implements ActeurRetirable {
    private List<Maladie> cibles;
    private final Alteration alteration;
    private final double degats;

    public Attaque(Coordonees coordonees, double degats, Alteration alteration, Maladie cible){
        this(coordonees, degats, alteration, List.of(cible));
    }

    public Attaque(Coordonees coordonees, double degats, Alteration alteration, List<Maladie> cibles) {
        super(coordonees);

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
        if (getAlteration() != null) {
            for (Maladie cible : cibles) {
                if (cible.peutSubirAlteration()) {
                    Alteration copie = getAlteration().copy();
                    copie.setMaladie(cible);
                    Environnement.getInstance().getGestionnaireActeur().getAlterations().add(copie);
                }
            }
        }
    }

    public void attaquer(){
        infligerDegats();
        donnerAlterations();
    }
}
