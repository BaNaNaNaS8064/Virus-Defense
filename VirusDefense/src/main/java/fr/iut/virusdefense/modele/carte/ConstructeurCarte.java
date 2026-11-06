package fr.iut.virusdefense.modele.carte;

import fr.iut.virusdefense.modele.apparition.PointApparition;

import java.util.ArrayList;
import java.util.List;

public class ConstructeurCarte {

    private boolean[][] carteStatique;
    private List<Integer> objectif;
    private ArrayList<PointApparition> pointApparitions;

    public ConstructeurCarte(){
        pointApparitions = new ArrayList<>();
    }

    public void setTaille(int hauteur, int largeur){
        carteStatique = new boolean[hauteur][largeur];
    }

    public void setObjectif(int[] coordonees){
        setObjectif(coordonees[0], coordonees[1]);
    }

    public void setObjectif(int ligne, int colonne){
        objectif = List.of(ligne, colonne);
    }

    public void ajouterPointApparition(int[] coordones){
        ajouterPointApparition(coordones[0], coordones[1]);
    }

    public void ajouterPointApparition(int ligne, int colonne){
        pointApparitions.add(new PointApparition(ligne, colonne));
    }

    public void changerValeur(int ligne, int colonne, boolean valeur){
        carteStatique[ligne][colonne] = valeur;
    }

    public Carte recupCarte(){
        return new Carte(carteStatique, objectif, pointApparitions);
    }

}
