package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.maladies.capacites.CapaciteInvocation;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

public class GrandChampignon extends Maladie{
    public GrandChampignon(int ligne, int colonne){
        super(ligne, colonne, 1000, 0.015, 100);
        setCapacitePeriodique(new CapaciteInvocation(CodeMaladie.PETITCHAMPIGNON, 300, 120, 3, 5));
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }
}
