package fr.iut.virusdefense.modele.carte;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.utilitaires.LecteurFichier;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class LecteurDeCarte extends LecteurFichier{

    private final ConstructeurDeCarte constructeurDeCarte;

    public LecteurDeCarte(Environnement environnement, String idNiveau){
        super("niveaux/" + idNiveau + "/carte.txt");
        constructeurDeCarte = new ConstructeurDeCarte(environnement);
        lire();
    }

    private int[] prochaineLigne(){
        String[] ligneString = getScanner().nextLine().split(" ");
        int[] ligneInt = new int[ligneString.length];

        for (int i = 0; i < ligneString.length; i++)
            ligneInt[i] = Integer.parseInt(ligneString[i]);

        return ligneInt;
    }

    @Override
    protected void lire() {
        ouvrirScanner();
        int[] ligne;
        int hauteur, largeur;
        int nbPointsApparitions;

        ligne = prochaineLigne();
        hauteur = ligne[0];
        largeur = ligne[1];
        constructeurDeCarte.setTaille(hauteur, largeur);

        constructeurDeCarte.setObjectif(prochaineLigne());

        nbPointsApparitions = Integer.parseInt(getScanner().nextLine());
        for (int i=0; i<nbPointsApparitions; i++)
            constructeurDeCarte.ajouterPointApparition(prochaineLigne());

        for (int indLigne = 0; indLigne < hauteur; indLigne++){
            ligne = prochaineLigne();

            for (int indColonne = 0; indColonne < largeur; indColonne++)
                constructeurDeCarte.changerValeur(indLigne, indColonne, ligne[indColonne] == 1);

        }
    }

    public Carte creer(){
        return constructeurDeCarte.recupCarte();
    }
}
