package fr.iut.virusdefense.modele.maladies.Capacité;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.PetitChampignon;

public class CapaciteActiveInvocation extends CapaciteActive {
    public CapaciteActiveInvocation() {

    }

    @Override
    public void ActivationCapacite(Environnement environnement, int ligne, int colonne) {
        resetDelai();
        for (int i = 0; i < (int)((Math.random()*3) + 2); i++) {
            environnement.ajouterMaladie(new PetitChampignon(environnement, ligne, colonne));
        }
    }

    public void resetDelai(){
        setDelaiActivation((int)(300 + Math.random()*120));
    }
}
