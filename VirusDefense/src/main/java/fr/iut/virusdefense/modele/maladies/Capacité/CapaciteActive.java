package fr.iut.virusdefense.modele.maladies.Capacité;

import fr.iut.virusdefense.modele.Environnement;

public abstract class CapaciteActive {
    private int delaiActivation;

    public CapaciteActive() {
        resetDelai();
    }

    public void setDelaiActivation(int delaiActivation) {
        this.delaiActivation = delaiActivation;
    }

    public boolean CapacitePossible() {
        return delaiActivation <= 0;
    }

    public void reduireDelai(){
        delaiActivation--;
    }

    public abstract void ActivationCapacite(Environnement environnement, int ligne, int colonne);

    public abstract void resetDelai();

}
