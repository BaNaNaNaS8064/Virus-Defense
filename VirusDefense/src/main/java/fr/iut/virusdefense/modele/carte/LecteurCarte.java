package fr.iut.virusdefense.modele.carte;

import fr.iut.virusdefense.modele.utilitaires.LecteurFichier;

public class LecteurCarte extends LecteurFichier{

    private final ConstructeurCarte constructeurCarte;

    public LecteurCarte(String idNiveau){
        super("niveaux/" + idNiveau + "/carte.txt");
        constructeurCarte = new ConstructeurCarte();
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
    protected void lireContenu() {
        int[] ligne;
        int hauteur, largeur;
        int nbPointsApparitions;

        ligne = prochaineLigne();
        hauteur = ligne[0];
        largeur = ligne[1];
        constructeurCarte.setTaille(hauteur, largeur);

        constructeurCarte.setObjectif(prochaineLigne());

        nbPointsApparitions = Integer.parseInt(getScanner().nextLine());
        for (int i=0; i<nbPointsApparitions; i++)
            constructeurCarte.ajouterPointApparition(prochaineLigne());

        for (int indLigne = 0; indLigne < hauteur; indLigne++){
            ligne = prochaineLigne();

            for (int indColonne = 0; indColonne < largeur; indColonne++)
                constructeurCarte.changerValeur(indLigne, indColonne, ligne[indColonne] == 1);
        }
    }

    public Carte creer(){
        return constructeurCarte.recupCarte();
    }
}