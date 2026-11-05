package fr.iut.virusdefense.modele.maladies.capacites;

public abstract class CapaciteActive implements Capacite {
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

    public abstract void resetDelai();

}
