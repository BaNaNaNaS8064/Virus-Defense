package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.positionnables.maladies.LecteurStatsMaladies;

public class Tumeur extends Maladie {
    public Tumeur(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatsMaladies.getStatistique("TUMEUR").pv(),
                LecteurStatsMaladies.getStatistique("TUMEUR").vitesse(),
                LecteurStatsMaladies.getStatistique("TUMEUR").recompense());
    }

    @Override
    public boolean peutSubirAlteration() {
        return false;
    }

}