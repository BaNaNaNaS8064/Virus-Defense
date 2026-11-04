package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.utilitaires.CodeTuile;

public class FabriqueCellule {
    public static Cellule créer(CodeTuile codeCellule, Environnement environnement, int ligne, int colonne) {
        return switch (codeCellule) {
            case SAINPLE -> new Sainple(environnement, ligne, colonne);
            case LASERE -> new Lasere(environnement, ligne, colonne);
            case BROUAIEUSE -> new Brouaileuse(environnement, ligne, colonne);
            case MULETYPLE -> new MuleTyple(environnement, ligne, colonne);
            case SNAIPEUR -> new Snaipeur(environnement, ligne, colonne);
            case RIZCOCHER -> new RizCocher(environnement, ligne, colonne);
            case KONSANTRE -> new Konsantre(environnement, ligne, colonne);
            case POUAZON -> new Pouazon(environnement, ligne, colonne);
            case BRULHURE -> new Brulhure(environnement, ligne, colonne);
            default -> null;
        };
    }
}
