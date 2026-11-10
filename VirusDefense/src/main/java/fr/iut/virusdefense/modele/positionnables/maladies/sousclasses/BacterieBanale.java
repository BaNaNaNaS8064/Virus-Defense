package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.positionnables.maladies.LecteurStatsMaladies;

public class BacterieBanale extends Maladie {

    public BacterieBanale(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatsMaladies.getStatistique("BACTERIEBANALE").pv(),
                LecteurStatsMaladies.getStatistique("BACTERIEBANALE").vitesse(),
                LecteurStatsMaladies.getStatistique("BACTERIEBANALE").recompense()
                );
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

}
