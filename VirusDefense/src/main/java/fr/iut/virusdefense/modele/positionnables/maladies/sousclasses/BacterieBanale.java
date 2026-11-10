package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

public class BacterieBanale extends Maladie {

    public BacterieBanale(double ligne, double colonne){
        super(ligne, colonne, 120, 0.02, 20);
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

}
