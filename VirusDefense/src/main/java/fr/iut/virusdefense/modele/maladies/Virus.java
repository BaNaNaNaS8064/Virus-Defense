package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Environnement;

public class Virus extends Maladie{
    public Virus(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne, 500, 0.015, 30);
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }
}
