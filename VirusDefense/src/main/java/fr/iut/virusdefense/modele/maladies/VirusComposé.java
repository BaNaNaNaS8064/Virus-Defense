package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Capacité.CapaciteMortDivision;

public class VirusComposé extends Maladie{
    private final int stade;
    public VirusComposé(Environnement environnement, double ligne, double colonne){
        this(environnement, ligne, colonne, 1);
    }

    public VirusComposé(Environnement environnement, double ligne, double colonne, int stade){
        super(environnement, ligne, colonne, 333/(int)(Math.pow(2,(stade-1))), 0.013, 24/(int)(Math.pow(2,(stade-1))), null, new CapaciteMortDivision(stade));
        this.stade = stade;
    }

    public int getStade() {
        return stade;
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

}
