package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class Tumeur extends Maladie{
    public Tumeur(int ligne, int colonne){
        super(ligne, colonne, 30000, 0.005, 500);
    }

    @Override
    public boolean peutSubirAlteration() {
        return false;
    }
}