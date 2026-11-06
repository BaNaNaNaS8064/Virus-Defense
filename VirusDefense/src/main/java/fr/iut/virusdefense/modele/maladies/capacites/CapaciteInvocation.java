package fr.iut.virusdefense.modele.maladies.capacites;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.PetitChampignon;

public class CapaciteInvocation extends CapacitePeriodique {
    private final int minInvoque;
    private final int maxInvoque;

    public CapaciteInvocation(int frequence, int variationFrequence, int minInvoque, int maxInvoque) {
        super(frequence, variationFrequence);
        this.minInvoque = minInvoque;
        this.maxInvoque = maxInvoque;
    }

    private int nombreAInvoquer(){
        if (0 < minInvoque && minInvoque < maxInvoque)
            return (int)(Math.random()*maxInvoque-minInvoque+1) + minInvoque;

        return 0;
    }
    
    @Override
    public void activer(Maladie m) {
        for (int i = 0; i < nombreAInvoquer(); i++)
            Environnement.getUniqueInstance().ajouterMaladie(new PetitChampignon((int) m.getLigne(), (int) m.getColonne()));
    }
}
