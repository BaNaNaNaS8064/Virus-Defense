package fr.iut.virusdefense.modele.maladies.capacites;

import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class CapaciteActive implements Capacite {
    private final int frequence;
    private final int variationFrequence;
    private int delai;

    public CapaciteActive(int frequence, int variationFrequence) {
        this.frequence = frequence;
        this.variationFrequence = variationFrequence;
        resetDelai();
    }

    private void resetDelai(){
        int variation = (int) (Math.random()*variationFrequence - (double) variationFrequence /2);
        delai = frequence + variation;
    }

    public void agir(Maladie m) {
        if (--delai <= 0) {
            resetDelai();
            activer(m);
        }
    }
}
