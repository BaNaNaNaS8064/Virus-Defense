package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

public class Tumeur extends Maladie {
    public Tumeur(double ligne, double colonne){
        super(ligne, colonne, 30000, 0.005, 500);
    }

    @Override
    public boolean peutSubirAlteration() {
        return false;
    }

    @Override
    public CodeMaladie getCodeMaladie() {
        return CodeMaladie.TUMEUR;
    }
}