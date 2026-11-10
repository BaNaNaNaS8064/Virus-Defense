package fr.iut.virusdefense.modele.positionnables.maladies;

import fr.iut.virusdefense.modele.utilitaires.LecteurFichier;

import java.util.HashMap;

public class LecteurStatsMaladies extends LecteurFichier {
    public record StatsMaladie(int pv, double vitesse, int recompense){}
    public static HashMap <String, StatsMaladie> statistiques;
    public LecteurStatsMaladies(String cheminRelatif){
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
                if(donnees.length >= 4){
                        String nom =  donnees[0].toUpperCase();
                        int pv  =  Integer.parseInt(donnees[1]);
                        double vitesse =  Double.parseDouble(donnees[2]);
                        int recompense = Integer.parseInt(donnees[3]);
                        statistiques.put(nom,  new StatsMaladie(pv,vitesse,recompense));
                }
            }
        }
    }

    public static StatsMaladie getStatistique(String nomMaladie){
        if (statistiques == null) {
            new LecteurStatsMaladies("stats/stats_maladies.txt");
        }
        return statistiques.get(nomMaladie.toUpperCase());
    }


}
