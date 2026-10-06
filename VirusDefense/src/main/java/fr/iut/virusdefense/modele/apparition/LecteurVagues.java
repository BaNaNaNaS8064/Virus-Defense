package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.utilitaires.LecteurFichier;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

import java.util.Scanner;

public class LecteurVagues extends LecteurFichier {
    private Scanner scanner;
    private Vague[] vagues;
    private int nbPointApparition;

    private int nbVague;
    private int nbPointsApparition;

    public LecteurVagues(int nbPointsApparition, String idNiveau){
        super("niveaux/" + idNiveau + "/vague.txt");
        this.nbPointsApparition = nbPointsApparition;
    }

    private double[] prochaineLigne(String s){
        String[] ligneString = s.split(" ");
        double[] ligneDouble = new double[ligneString.length];

        for (int i = 0; i < ligneString.length; i++)
            ligneDouble[i] = Double.parseDouble(ligneString[i]);

        return ligneDouble;
    }

    public int getNbVague() {
        return nbVague;
    }

    public Vague[] getVagues() {
        return vagues;
    }

    @Override
    public void lireContenu() {
        ouvrirScanner();
        double[] maladiesInfo;
        String ligne;

        nbVague = Integer.parseInt(scanner.nextLine());
        vagues = new Vague[nbVague];
        scanner.nextLine();
        for (int indVague = 0; indVague < nbVague; indVague++) {
            vagues[indVague] = new Vague();
            ligne = scanner.nextLine();
            while(!ligne.equals("#")){
                maladiesInfo = prochaineLigne(ligne);
                for (int indPointApparition = 0; indPointApparition < nbPointsApparition; indPointApparition++) {
                    vagues[indVague].ajouter(new ListeApparition());
                    for (int nombreMemeMaladie = 0; nombreMemeMaladie < (int)maladiesInfo[0]; nombreMemeMaladie++) {
                        vagues[indVague].getListeApparitions().get(indPointApparition).ajouter(CodeMaladie.values()[(int)maladiesInfo[1]], (int)(maladiesInfo[2] *60));
                    }
                }
                ligne = scanner.nextLine();
            }
        }
    }
}
