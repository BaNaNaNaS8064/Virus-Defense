package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.*;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

public class FabriqueMaladie {
    public static Maladie créerMaladie(CodeMaladie codeMaladie, Environnement environnement, int ligne, int colonne) {
        return switch (codeMaladie) {
            case BACTERIEBANALE -> new BacterieBanale(environnement, ligne, colonne);
            case PARASITE -> new Parasite(environnement, ligne, colonne);
            case VIRUS -> new Virus(environnement, ligne, colonne);
            case VIRUSCOMPOSE_1 -> new VirusComposé(environnement, ligne, colonne);
            case PETITCHAMPIGNON -> new PetitChampignon(environnement, ligne, colonne);
            case GRANDCHAMPIGNON -> new GrandChampignon(environnement, ligne, colonne);
            case TUMEUR -> new Tumeur(environnement, ligne, colonne);
            default -> null;
        };
    }


}
