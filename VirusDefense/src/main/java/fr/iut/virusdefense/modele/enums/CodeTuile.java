package fr.iut.virusdefense.modele.enums;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.sousclasses.*;

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
        if (c instanceof Sainple)
            return SAINPLE;

        if (c instanceof Lasere)
            return LASERE;

        if (c instanceof Brouaieuse)
            return BROUAIEUSE;

        if (c instanceof MuleTyple)
            return MULETYPLE;

        if (c instanceof Snaipeur)
            return SNAIPEUR;

        if (c instanceof RizCocher)
            return RIZCOCHER;

        if (c instanceof Konsantre)
            return KONSANTRE;

        if (c instanceof Pouazon)
            return POUAZON;

        if (c instanceof Brulhure)
            return BRULHURE;

        return CELLULEINCONNUE;
    }
}