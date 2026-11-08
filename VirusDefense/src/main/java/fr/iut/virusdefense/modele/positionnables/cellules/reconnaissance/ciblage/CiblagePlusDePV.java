package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;

public class CiblagePlusDePV extends Ciblage {

    public CiblagePlusDePV() {
        super();
    }

    @Override
    public void remplacerSiConditionRempli(Maladie maladie) {
        if (getMeilleureMaladie().getPv() < maladie.getPv()) {
            setMeilleureMaladie(maladie);
        }
    }
}
