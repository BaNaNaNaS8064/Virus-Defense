package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.positionnables.maladies.capacites.CapaciteInvocation;

public class VirusComposé extends Maladie {
    private final int stade;

    public VirusComposé(double ligne, double colonne, int stade){
        super(ligne, colonne, 333/(int)(Math.pow(2,(stade-1))), 0.013, 24/(int)(Math.pow(2,(stade-1))));
        this.stade = stade;

        if (stade > 0 && stade < 3)
            setCapaciteMort(new CapaciteInvocation(CodeMaladie.valueOf("VIRUSCOMPOSE_" + (stade+1)), 2, 2));
    }

    public int getStade() {
        return stade;
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

    @Override
    public CodeMaladie getCodeMaladie() {
        return CodeMaladie.valueOf("VIRUSCOMPOSE_"+ getStade());
    }

}
