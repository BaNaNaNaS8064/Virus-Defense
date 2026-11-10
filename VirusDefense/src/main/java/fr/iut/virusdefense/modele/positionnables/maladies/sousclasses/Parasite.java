package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

public class Parasite extends Maladie {

    public Parasite(double ligne, double colonne){
        super(ligne, colonne, 80, 0.022, 10);
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