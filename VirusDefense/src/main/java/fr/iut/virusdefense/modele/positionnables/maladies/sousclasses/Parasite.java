package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

public class Parasite extends Maladie {

    public Parasite(int ligne, int colonne){
        super(ligne, colonne, 80, 0.022, 10);
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

}