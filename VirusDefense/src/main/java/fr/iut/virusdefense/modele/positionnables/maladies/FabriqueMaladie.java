package fr.iut.virusdefense.modele.positionnables.maladies;

import fr.iut.virusdefense.modele.apparition.Niveau;
import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.decorateurs.Blindee;
import fr.iut.virusdefense.modele.positionnables.maladies.sousclasses.*;

public class FabriqueMaladie {
    private static Maladie creerNormale(CodeMaladie codeMaladie, double ligne, double colonne) {
        ligne+=0.5;
        colonne+=0.5;
        return switch (codeMaladie) {
            case BACTERIEBANALE -> new BacterieBanale(ligne, colonne);
            case PARASITE -> new Parasite(ligne, colonne);
            case VIRUS -> new Virus(ligne, colonne);
            case VIRUSCOMPOSE_1 -> new VirusComposé(ligne, colonne, 1);
            case VIRUSCOMPOSE_2 -> new VirusComposé(ligne, colonne, 2);
            case VIRUSCOMPOSE_3 -> new VirusComposé(ligne, colonne, 3);
            case PETITCHAMPIGNON -> new PetitChampignon(ligne, colonne);
            case GRANDCHAMPIGNON -> new GrandChampignon(ligne, colonne);
            case TUMEUR -> new Tumeur(ligne, colonne);
            default -> null;
        };
    }

    private static Maladie creerBlindee(CodeMaladie codeMaladie, double ligne, double colonne) {
        Maladie maladieBase = creerNormale(codeMaladie, ligne, colonne);
        return maladieBase==null ? null : new Blindee(maladieBase);
    }

    public static Maladie creerMaladie(CodeMaladie codeMaladie, double ligne, double colonne){
        return switch(Niveau.getInstance().getDifficulte()){
            case NORMAL -> creerNormale(codeMaladie, ligne, colonne);
            case DIFFICILE -> creerBlindee(codeMaladie, ligne, colonne);
            default -> null;
        };
    }
}
