package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

import java.util.ArrayList;

public class CiblageProche extends Ciblage{
    private final Coordonees coordonees;

    public CiblageProche(Coordonees coordonees) {
        super();
        this.coordonees = coordonees;
    }

    @Override
    public void remplacerSiConditionRempli(Maladie maladie) {
        double distanceMaladie = maladie.getCoordonees().distanceEuclidienne(coordonees.getLigne(), coordonees.getColonne());
        double distanceMeilleurMaladie = getMeilleureMaladie().getCoordonees().distanceEuclidienne(this.coordonees.getLigne(), this.coordonees.getColonne());
        if (conditionRempli(distanceMaladie, distanceMeilleurMaladie)) {
            setMeilleureMaladie(maladie);
        }
    }

    public boolean conditionRempli(double distanceMaladie, double distanceMeilleurMaladie) {
        return distanceMeilleurMaladie > distanceMaladie;
    }
}

