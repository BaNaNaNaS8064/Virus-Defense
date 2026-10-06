package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class Parasite extends Maladie{

    public Parasite(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne, 80, 0.022, 10);
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }
}