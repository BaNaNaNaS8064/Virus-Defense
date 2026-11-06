package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.maladies.capacites.CapaciteDivision;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

public class VirusComposé extends Maladie{
    private final int stade;

    public VirusComposé(double ligne, double colonne, int stade){
        super(ligne, colonne, 333/(int)(Math.pow(2,(stade-1))), 0.013, 24/(int)(Math.pow(2,(stade-1))));
        this.stade = stade;

        if (stade > 0 && stade < 3)
            setCapaciteMort(new CapaciteDivision(CodeMaladie.valueOf("VIRUSCOMPOSE_" + (stade+1)), 2, 2));
    }

    public int getStade() {
        return stade;
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

}
