package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.positionnables.maladies.LecteurStatsMaladies;

public class Virus extends Maladie {
    public Virus(int ligne, int colonne){
        super(ligne, colonne, LecteurStatsMaladies.getStatistique("VIRUS").pv(),
                LecteurStatsMaladies.getStatistique("VIRUS").vitesse(),
                LecteurStatsMaladies.getStatistique("VIRUS").recompense());
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

}
