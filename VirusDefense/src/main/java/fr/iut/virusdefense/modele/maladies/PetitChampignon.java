package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class PetitChampignon extends Maladie{
    public PetitChampignon(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne, 75, 0.02, 7);
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }
}
