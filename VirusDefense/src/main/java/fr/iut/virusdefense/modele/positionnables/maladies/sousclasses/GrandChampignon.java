package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.positionnables.maladies.capacites.Capacite;
import fr.iut.virusdefense.modele.positionnables.maladies.capacites.CapaciteInvocation;
import fr.iut.virusdefense.modele.positionnables.maladies.capacites.CapacitePeriodique;
import fr.iut.virusdefense.modele.positionnables.maladies.LecteurStatsMaladies;

public class GrandChampignon extends Maladie {
    public GrandChampignon(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatsMaladies.getStatistique("GrandChampignon").pv(),
                LecteurStatsMaladies.getStatistique("GrandChampignon").vitesse(),
                LecteurStatsMaladies.getStatistique("GrandChampignon").recompense()
        );
        Capacite capaciteInvocation = new CapaciteInvocation(CodeMaladie.PETITCHAMPIGNON, 3, 5);
        setCapacitePeriodique(new CapacitePeriodique(capaciteInvocation, 300, 120));
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }
}
