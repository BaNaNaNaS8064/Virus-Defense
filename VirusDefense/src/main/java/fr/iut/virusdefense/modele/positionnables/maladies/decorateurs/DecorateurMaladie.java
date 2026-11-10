package fr.iut.virusdefense.modele.positionnables.maladies.decorateurs;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

public abstract class DecorateurMaladie extends Maladie {
    private final Maladie maladie_origine;

    public DecorateurMaladie(Maladie maladie) {
        super(maladie.getLigne(), maladie.getColonne(), maladie.getPv(), maladie.getVitesse(), maladie.getRecompense());
        this.maladie_origine = maladie;

        setCapaciteMort(maladie.getCapaciteMort());
        setCapacitePeriodique(maladie.getCapacitePeriodique());
    }

    @Override
    public boolean peutSubirAlteration() {
        return maladie_origine.peutSubirAlteration();
    }

    public Maladie getMaladie_origine() {
        return maladie_origine;
    }
}
