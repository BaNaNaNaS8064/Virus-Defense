package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.*;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

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
