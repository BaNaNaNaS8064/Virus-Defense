package fr.iut.virusdefense.modele.positionnables.maladies.decorateurs;

import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

public abstract class DecorateurMaladie extends Maladie {
    private final Maladie maladieOrigine;

    public DecorateurMaladie(Maladie maladie) {
        super(maladie.getLigne(), maladie.getColonne(), maladie.getPv(), maladie.getVitesse(), maladie.getRecompense());
        this.maladieOrigine = maladie;

        setCapaciteMort(maladie.getCapaciteMort());
        setCapacitePeriodique(maladie.getCapacitePeriodique());
    }

    @Override
    public boolean peutSubirAlteration() {
        return maladieOrigine.peutSubirAlteration();
    }

    @Override
    public CodeMaladie getCodeMaladie() {
        return maladieOrigine.getCodeMaladie();
    }
}
