package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Capacité.CapaciteActive;
import fr.iut.virusdefense.modele.maladies.Capacité.CapaciteActiveInvocation;

public class GrandChampignon extends Maladie{
    public GrandChampignon(Environnement environnement, int ligne, int colonne) {
        super(environnement, ligne, colonne, 1000, 0.015, 100, new CapaciteActiveInvocation(), null);
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }
}
