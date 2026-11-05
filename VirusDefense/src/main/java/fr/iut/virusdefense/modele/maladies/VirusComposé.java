package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class VirusComposé extends Maladie{
    private final int stade;
    public VirusComposé(double ligne, double colonne){
        this(ligne, colonne, 1);
    }

    private VirusComposé(double ligne, double colonne, int stade){
        super(ligne, colonne, 333/(int)(Math.pow(2,(stade-1))), 0.013, 24/(int)(Math.pow(2,(stade-1))));
        this.stade = stade;
    }

    public int getStade() {
        return stade;
    }

    @Override
    public void capaciteALaMort() {
        if(stade<3 && !aAtteintLObjectif())
            for (int i=0; i<2; i++)
        Environnement.getUniqueInstance().getGestionnaireActeur().getMaladies().add(new VirusComposé(getLigne()-0.5, getColonne()-0.5, stade+1));
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }
}
