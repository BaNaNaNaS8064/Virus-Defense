package fr.iut.virusdefense.modele.positionnables.cellules;

import fr.iut.virusdefense.modele.cellules.sousclasses.*;
import fr.iut.virusdefense.modele.enums.CodeTuile;
import fr.iut.virusdefense.modele.positionnables.cellules.sousclasses.*;

public class FabriqueCellule {
    public static Cellule créer(CodeTuile codeCellule, int ligne, int colonne) {
        return switch (codeCellule) {
            case SAINPLE -> new Sainple(ligne, colonne);
            case LASERE -> new Lasere(ligne, colonne);
            case BROUAIEUSE -> new Brouaieuse(ligne, colonne);
            case MULETYPLE -> new MuleTyple(ligne, colonne);
            case SNAIPEUR -> new Snaipeur(ligne, colonne);
            case RIZCOCHER -> new RizCocher(ligne, colonne);
            case KONSANTRE -> new Konsantre(ligne, colonne);
            case POUAZON -> new Pouazon(ligne, colonne);
            case BRULHURE -> new Brulhure(ligne, colonne);
            default -> null;
        };
    }
}
