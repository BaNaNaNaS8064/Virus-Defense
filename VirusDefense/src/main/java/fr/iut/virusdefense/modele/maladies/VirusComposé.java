package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class VirusComposé extends Maladie{
    private final int stade;
    public VirusComposé(Environnement environnement, int ligne, int colonne){
        this(environnement, ligne, colonne, 1);
    }

    private VirusComposé(Environnement environnement, double ligne, double colonne, int stade){
        super(environnement, ligne, colonne, 333/(int)(Math.pow(2,(stade-1))), 0.013, 24/(int)(Math.pow(2,(stade-1))));
        this.stade = stade;
    }

    public int getStade() {
        return stade;
    }

    @Override
    public void capaciteALaMort() {
        if(stade<3 && !aAtteintLObjectif())
            for (int i=0; i<2; i++)
                getEnvironnement().getMaladies().add(new VirusComposé(getEnvironnement(), getLigne(), getColonne(), stade+1));
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }
}
