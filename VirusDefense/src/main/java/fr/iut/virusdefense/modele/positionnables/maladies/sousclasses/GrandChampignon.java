package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.positionnables.maladies.capacites.Capacite;
import fr.iut.virusdefense.modele.positionnables.maladies.capacites.CapaciteInvocation;
import fr.iut.virusdefense.modele.positionnables.maladies.capacites.CapacitePeriodique;
import fr.iut.virusdefense.modele.enums.CodeMaladie;

public class GrandChampignon extends Maladie {
    public GrandChampignon(int ligne, int colonne){
        super(ligne, colonne, 1000, 0.015, 100);
        Capacite capaciteInvocation = new CapaciteInvocation(CodeMaladie.PETITCHAMPIGNON, 3, 5);
        setCapacitePeriodique(new CapacitePeriodique(capaciteInvocation, 300, 120));
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }
}
