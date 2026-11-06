package fr.iut.virusdefense.modele.maladies.capacites;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.apparition.FabriqueMaladie;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.VirusComposé;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

public class CapaciteDivision implements CapaciteMort {
    private final CodeMaladie codeDivise;
    private final int minInvoque;
    private final int maxInvoque;

    public CapaciteDivision(CodeMaladie codeDivise, int minInvoque, int maxInvoque) {
        this.codeDivise = codeDivise;
        this.minInvoque = minInvoque;
        this.maxInvoque = maxInvoque;
    }

    private int nombreAInvoquer(){
        if (0 < minInvoque && minInvoque < maxInvoque)
            return (int)(Math.random()*maxInvoque-minInvoque+1) + minInvoque;

        return 0;
    }

    @Override
    public void activer(Maladie m){
        int n = nombreAInvoquer();
        for (int i=0; i<n; i++)
                Environnement.getUniqueInstance().getGestionnaireActeur().getMaladies().add(FabriqueMaladie.créerMaladie(codeDivise, (int) m .getLigne(), (int) m .getColonne()));
    }
}
