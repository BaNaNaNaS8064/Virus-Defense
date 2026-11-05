package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class BacterieBanale extends Maladie{

    public BacterieBanale(int ligne, int colonne){
        super(ligne, colonne, 120, 0.02, 20);
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }
}
