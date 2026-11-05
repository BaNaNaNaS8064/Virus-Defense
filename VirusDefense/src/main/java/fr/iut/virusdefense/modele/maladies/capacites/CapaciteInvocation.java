package fr.iut.virusdefense.modele.maladies.capacites;

import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.PetitChampignon;

public class CapaciteInvocation extends CapacitePeriodique {
    public CapaciteInvocation(int frequence, int variationFrequence) {
        super(frequence, variationFrequence);
    }

    @Override
    public void activer(Maladie m) {
        for (int i = 0; i < (int)((Math.random()*3) + 2); i++)
            m.getEnvironnement().ajouterMaladie(new PetitChampignon(m.getEnvironnement(), (int) m.getLigne(), (int) m.getColonne()));
    }
}
