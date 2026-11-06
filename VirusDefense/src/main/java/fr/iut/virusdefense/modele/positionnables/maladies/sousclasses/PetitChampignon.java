package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

public class PetitChampignon extends Maladie {
    public PetitChampignon(int ligne, int colonne){
        super(ligne, colonne, 75, 0.02, 7);
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

}
