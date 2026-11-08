package fr.iut.virusdefense.modele.positionnables.maladies.capacites;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.FabriqueMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

public class CapaciteInvocation implements Capacite {
    private final CodeMaladie invoque;
    private final int minInvoque;
    private final int maxInvoque;

    public CapaciteInvocation(CodeMaladie invoque, int minInvoque, int maxInvoque) {
        this.invoque = invoque;
        this.minInvoque = minInvoque;
        this.maxInvoque = maxInvoque;
    }

    private int nombreAInvoquer(){
        if (0 <= minInvoque && minInvoque <= maxInvoque)
            return (int)(Math.random()*maxInvoque-minInvoque+1) + minInvoque;

        return 0;
    }
    
    @Override
    public void activer(Maladie m) {
        int n = nombreAInvoquer();
        for (int i = 0; i < n; i++)
            Environnement.getInstance().ajouterMaladie(FabriqueMaladie.créerMaladie(invoque, (int) m.getLigne(), (int) m.getColonne()));
    }
}
