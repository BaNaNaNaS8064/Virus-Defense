package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.positionnables.maladies.LecteurStatsMaladies;

public class Parasite extends Maladie {

    public Parasite(double ligne, double colonne){
        super(ligne, colonne, LecteurStatsMaladies.getStatistique("PARASITE").pv(),
                LecteurStatsMaladies.getStatistique("PARASITE").vitesse(),
                LecteurStatsMaladies.getStatistique("PARASITE").recompense()
        );
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

    @Override
    public CodeMaladie getCodeMaladie() {
        return CodeMaladie.PARASITE;
    }

}