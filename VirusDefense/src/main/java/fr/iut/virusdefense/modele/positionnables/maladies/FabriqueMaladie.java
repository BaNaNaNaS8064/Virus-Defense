package fr.iut.virusdefense.modele.positionnables.maladies;

import fr.iut.virusdefense.modele.maladies.sousclasses.*;
import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.sousclasses.*;

public class FabriqueMaladie {
    public static Maladie créerMaladie(CodeMaladie codeMaladie, int ligne, int colonne) {
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


}
