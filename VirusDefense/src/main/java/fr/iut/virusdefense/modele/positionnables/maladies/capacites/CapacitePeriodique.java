package fr.iut.virusdefense.modele.positionnables.maladies.capacites;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

public class CapacitePeriodique implements Capacite {
    private final Capacite capacite;
    private final int frequence;
    private final int variationFrequence;
    private int delai;

    public CapacitePeriodique(Capacite capacite, int frequence, int variationFrequence) {
        this.frequence = frequence;
        this.variationFrequence = variationFrequence;
        this.capacite = capacite;
        resetDelai();
    }

    private void resetDelai(){
        int variation = (int) (Math.random()*variationFrequence - (double) variationFrequence /2);
        delai = frequence + variation;
    }

    @Override
    public void activer(Maladie m) {
        if (--delai <= 0) {
            resetDelai();
            capacite.activer(m);
        }
    }
}
