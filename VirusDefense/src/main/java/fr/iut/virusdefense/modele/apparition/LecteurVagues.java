package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.utilitaires.LecteurFichier;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

public class LecteurVagues extends LecteurFichier {
    private Vague[] vagues;
    private int nbVague;
    private int nbPointsApparition;

    public LecteurVagues(int nbPointsApparition, String idNiveau){
        super("niveaux/" + idNiveau + "/vagues.txt"); // Correction : vagues au pluriel
        this.nbPointsApparition = nbPointsApparition;
        lire();
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
        double[] maladiesInfo;
        String ligne;

        nbVague = Integer.parseInt(getScanner().nextLine());
        vagues = new Vague[nbVague];
        getScanner().nextLine();

        for (int indVague = 0; indVague < nbVague; indVague++) {
            vagues[indVague] = new Vague();
            ligne = getScanner().nextLine();
            while(!ligne.equals("#")){
                maladiesInfo = prochaineLigne(ligne);
                for (int indPointApparition = 0; indPointApparition < nbPointsApparition; indPointApparition++) {
                    vagues[indVague].ajouter(new ListeApparition());
                    for (int nombreMemeMaladie = 0; nombreMemeMaladie < (int)maladiesInfo[0]; nombreMemeMaladie++) {
                        vagues[indVague].getListeApparitions().get(indPointApparition).ajouter(CodeMaladie.values()[(int)maladiesInfo[1]], (int)(maladiesInfo[2] *60));
                    }
                }
                ligne = getScanner().nextLine();
            }
        }
    }
}