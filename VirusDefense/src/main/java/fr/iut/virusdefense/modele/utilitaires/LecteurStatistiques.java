package fr.iut.virusdefense.modele.utilitaires;

import fr.iut.virusdefense.modele.cellules.Cellule;

import java.util.HashMap;

public class LecteurStatistiques extends LecteurFichier{

    public static HashMap<String, StatsCellule> statistiques;

    public static class StatsCellule{
        public int cout;
        public double degats;
        public double portee;
        public int frequence;

        public StatsCellule(int cout, double degats, double portee, int frequence){
            this.cout = cout;
            this.degats = degats;
            this.portee = portee;
            this.frequence = frequence;
        }
    }

    public LecteurStatistiques(String cheminRelatif){
        super(cheminRelatif);
        statistiques = new HashMap();
        lire();
    }

    @Override
    protected void lireContenu(){
        while(getScanner().hasNextLine()){
            String ligne = getScanner().nextLine();

            if(ligne.startsWith("#") || ligne.trim().isEmpty()){
                continue;
            }
            String[] donnees = ligne.split(" ");
            if(donnees.length == 5){
                String nom = donnees[0];
                int cout = Integer.parseInt(donnees[1]);
                double degats = Double.parseDouble(donnees[2]);
                double portee = Double.parseDouble(donnees[3]);
                int frequence = Integer.parseInt(donnees[4]);

                statistiques.put(nom, new StatsCellule(cout, degats, portee, frequence));
            }
        }
    }
    public static StatsCellule getStatistique(String nomCellule){
        return statistiques.get(nomCellule.toUpperCase());
    }
}
