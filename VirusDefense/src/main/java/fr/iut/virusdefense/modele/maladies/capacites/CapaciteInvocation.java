package fr.iut.virusdefense.modele.maladies.capacites;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.apparition.FabriqueMaladie;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.PetitChampignon;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

public class CapaciteInvocation extends CapacitePeriodique {
    private final CodeMaladie invoque;
    private final int minInvoque;
    private final int maxInvoque;

    public CapaciteInvocation(CodeMaladie invoque, int frequence, int variationFrequence, int minInvoque, int maxInvoque) {
        super(frequence, variationFrequence);
        this.invoque = invoque;
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
            Environnement.getUniqueInstance().ajouterMaladie(FabriqueMaladie.créerMaladie(invoque, (int) m.getLigne(), (int) m.getColonne()));
    }
}
