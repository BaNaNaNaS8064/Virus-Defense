package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.positionnables.maladies.LecteurStatsMaladies;

public class PetitChampignon extends Maladie {
    public PetitChampignon(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatsMaladies.getStatistique("PETITCHAMPIGNON").pv(),
                LecteurStatsMaladies.getStatistique("PETITCHAMPIGNON").vitesse(),
                LecteurStatsMaladies.getStatistique("PETITCHAMPIGNON").recompense()
        );
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

}
