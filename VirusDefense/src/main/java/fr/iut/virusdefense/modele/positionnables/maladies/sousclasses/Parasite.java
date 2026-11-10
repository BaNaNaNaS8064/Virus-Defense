package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.positionnables.maladies.LecteurStatsMaladies;

public class Parasite extends Maladie {

    public Parasite(int ligne, int colonne){
        super(ligne, colonne, LecteurStatsMaladies.getStatistique("PARASITE").pv(),
                LecteurStatsMaladies.getStatistique("PARASITE").vitesse(),
                LecteurStatsMaladies.getStatistique("PARASITE").recompense()
        );
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

}