package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.maladies.capacites.CapaciteDivision;

public class VirusComposé extends Maladie{
    private final int stade;
    public VirusComposé(double ligne, double colonne){
        this(ligne, colonne, 1);
    }

    public VirusComposé(double ligne, double colonne, int stade){
        super(ligne, colonne, 333/(int)(Math.pow(2,(stade-1))), 0.013, 24/(int)(Math.pow(2,(stade-1))));
        this.stade = stade;
        setCapaciteMort(new CapaciteDivision(stade));
    }

    public int getStade() {
        return stade;
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

}
