package fr.iut.virusdefense.modele.maladies.capacites;

import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.PetitChampignon;

public class CapaciteActiveInvocation extends CapaciteActive {
    public CapaciteActiveInvocation() {
        super();
    }

    @Override
    public void Activer(Maladie m) {
        resetDelai();
        for (int i = 0; i < (int)((Math.random()*3) + 2); i++) {
            m.getEnvironnement().ajouterMaladie(new PetitChampignon(m.getEnvironnement(), (int) m.getLigne(), (int) m.getColonne()));
        }
    }

    public void resetDelai(){
        setDelaiActivation((int)(300 + Math.random()*120));
    }
}
