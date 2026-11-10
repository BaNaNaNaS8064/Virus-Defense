package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.positionnables.maladies.capacites.CapaciteInvocation;
import fr.iut.virusdefense.modele.positionnables.maladies.LecteurStatsMaladies;

public class VirusComposé extends Maladie {
    private final int stade;

    public VirusComposé(double ligne, double colonne, int stade){
        super(ligne, colonne, LecteurStatsMaladies.getStatistique("VIRUSCOMPOSE_" + stade).pv(),
                LecteurStatsMaladies.getStatistique("VIRUSCOMPOSE_"+ stade).vitesse(),
                LecteurStatsMaladies.getStatistique("VIRUSCOMPOSE_"+ stade).recompense()
        );
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

}
