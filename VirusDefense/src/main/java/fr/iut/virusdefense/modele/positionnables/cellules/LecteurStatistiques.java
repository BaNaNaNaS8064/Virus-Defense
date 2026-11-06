package fr.iut.virusdefense.modele.positionnables.cellules;

import fr.iut.virusdefense.modele.utilitaires.LecteurFichier;

import java.util.HashMap;

public class LecteurStatistiques extends LecteurFichier {

    public static HashMap<String, StatsCellule> statistiques;

    public static class StatsCellule{
        private int cout;
        private double degats;
        private double portee;
        private int frequence;

        public StatsCellule(int cout, double degats, double portee, int frequence){
            this.cout = cout;
            this.degats = degats;
            this.portee = portee;
            this.frequence = frequence;
        }
        public int getCout() {
            return cout;
        }

        public double getDegats() {
            return degats;
        }

        public double getPortee() {
            return portee;
        }

        public int getFrequence() {
            return frequence;
        }
    }

    public LecteurStatistiques(String cheminRelatif){
        super(cheminRelatif);
        statistiques = new HashMap<>();
        lire();
    }

    @Override
    protected void lireContenu(){
        while(getScanner().hasNextLine()){
            String ligne = getScanner().nextLine();
            if(!ligne.startsWith("#") && !ligne.trim().isEmpty()){
                String[] donnees = ligne.trim().split(" ");
                if(donnees.length == 5){
                    String nom = donnees[0].trim();
                    int cout = Integer.parseInt(donnees[1]);
                    double degats = Double.parseDouble(donnees[2]);
                    double portee = Double.parseDouble(donnees[3]);
                    int frequence = Integer.parseInt(donnees[4]);
                    statistiques.put(nom, new StatsCellule(cout, degats, portee, frequence));
                }
            }

        }
    }
    public static StatsCellule getStatistique(String nomCellule){
        return statistiques.get(nomCellule.toUpperCase());
    }
}
