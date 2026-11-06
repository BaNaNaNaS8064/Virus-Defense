package fr.iut.virusdefense.modele.carte;

import fr.iut.virusdefense.modele.apparition.PointApparition;
import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.enums.CodeTuile;

import java.util.ArrayList;
import java.util.List;

public class Carte {
    private final boolean[][] carteStatique;
    private final List<Integer> objectif;
    private final ArrayList<PointApparition> pointApparitions;
    private final ArrayList<Cellule> cellules;

    public Carte(boolean[][] carteStatique, List<Integer> objectif, ArrayList<PointApparition> pointApparitions) {
        this.carteStatique = carteStatique;
        this.objectif = objectif;
        this.pointApparitions = pointApparitions;
        cellules = new ArrayList<>();
    }

    public int getHauteur(){
        return carteStatique.length;
    }

    public int getLargeur(){
        return carteStatique[0].length;
    }

    public List<Integer> getObjectif(){
        return objectif;
    }

    public ArrayList<Cellule> getCellules() {
        return cellules;
    }

    public ArrayList<PointApparition> getPointsApparitions() {
        return pointApparitions;
    }

    public CodeTuile getCode(int ligne, int colonne) {
        if (List.of(ligne, colonne).equals(objectif))
            return CodeTuile.OBJECTIF;

        for (PointApparition p : pointApparitions)
            if ((int)p.getLigne() == ligne && (int)p.getColonne() == colonne)
                return CodeTuile.POINTAPPARITION;

        for (Cellule c : cellules)
            if ((int)c.getLigne() == ligne && (int)c.getColonne() == colonne)
                return CodeTuile.codeDe(c);

        if (carteStatique[ligne][colonne])
            return CodeTuile.MUR;
        else
            return CodeTuile.VIDE;
    }

    public boolean emplacementVide(int ligne, int colonne){
        return getCode(ligne, colonne).estVide();
    }

    public boolean peutMarcher(int ligne, int colonne) {
        return getCode(ligne, colonne).peutMarcher();
    }

    public boolean estCellule(int ligne, int colonne) {
        return getCode(ligne, colonne).estCodeCellule();
    }

    public boolean peutVoirAuTravers(int ligne, int colonne, boolean ignorerCellules){
        return getCode(ligne, colonne).peutVoirAuTravers(ignorerCellules);
    }

    /**
     * Retourne vrai si (ligne;colonne) se trouve dans les bornes de la carte
     * @param ligne une ligne
     * @param colonne une colonne
     * @return si (i;y) se trouve dans les bornes de la carte
     */
    public boolean dansBornes(double ligne, double colonne) {
        return (0 <= ligne && ligne < getHauteur()) && (0 <= colonne && colonne < getLargeur());
    }
}
