package fr.iut.virusdefense.modele.enums;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;

public enum CodeTuile {
    VIDE,
    MUR,
    OBJECTIF,
    POINTAPPARITION,

    CELLULEINCONNUE,
    SAINPLE,
    LASERE,
    BROUAIEUSE,
    MULETYPLE,
    SNAIPEUR,
    RIZCOCHER,
    KONSANTRE,
    POUAZON,
    BRULHURE;

    public boolean peutMarcher(){
        return this == CodeTuile.VIDE
                || this == CodeTuile.OBJECTIF
                || this == CodeTuile.POINTAPPARITION;
    }

    public boolean peutVoirAuTravers(boolean ignorerCellules){
        if (ignorerCellules)
            return this != CodeTuile.MUR;
        else
            return peutMarcher();
    }

    public boolean estVide(){
        return this == VIDE;
    }

    public boolean estCodeCellule(){
        return !peutMarcher() && this != MUR;
    }

    public static CodeTuile codeDe(Cellule c){
        if(c == null){
            return CodeTuile.CELLULEINCONNUE;
        }

        return c.getCodeTuile();
    }
}