package fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;

import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

public class Virus extends Maladie {
    public Virus(double ligne, double colonne){
        super(ligne, colonne, 500, 0.015, 30);
    }

    @Override
    public boolean peutSubirAlteration() {
        return true;
    }

    @Override
    public CodeMaladie getCodeMaladie() {
        return CodeMaladie.VIRUS;
    }
}
