package fr.iut.virusdefense.modele.deplacement;

import fr.iut.virusdefense.modele.carte.Carte;

import java.util.*;

/**
 * Se charge d'effectuer tout ce qui est lié au déplacement sur la carte
 * (par exemple calcul de chemin optimal)
 */
public class Deplacement {
    /**
     * Associe à une case les coordonées de la prochaine
     * dans le chemin optimal vers l'objectif
     */
    private final Map<List<Integer>, List<Integer>> prochaineCase;

    /**
     * La carte sur laquels les calculs seront effectués
     */
    private final Carte carte;

    public Deplacement(Carte carte){
        prochaineCase = new HashMap<>();
        this.carte = carte;
        faireAlgo();
    }

    /**
     * Remplit la Map {@code prochaineCase}
     * à l'aide de l'algorithme du BFS
     * à partir de l'objectif de la carte
     */
    public void faireAlgo() {
        prochaineCase.clear();

        LinkedList<List<Integer>> fifo = new LinkedList<>();
        List<Integer> caseActuelle = carte.getObjectif();

        fifo.add(caseActuelle);
        prochaineCase.put(caseActuelle, null);

        while (!fifo.isEmpty()){
            caseActuelle = fifo.poll();
            for (List<Integer> voisin : voisins(caseActuelle)){
                if (!prochaineCase.containsKey(voisin)){
                    fifo.add(voisin);
                    prochaineCase.put(voisin, caseActuelle);
                }
            }
        }
    }

    /**
     * Retourne tous les voisins sur lequels on peut marcher
     * de la case de coordonnées {@code coordsCase}
     *
     * @param coordsCase Une liste sous forme [ligne, colonne]
     *                qui représente les coordonnées d'un case
     *
     * @return une liste de listes (sous forme [[ligne, colonne], ...])
     * qui représente une liste des voisins sur lequels on peut marcher
     * de la case de coordonnées {@code coordsCase}
     */
    private ArrayList<List<Integer>> voisins(List<Integer> coordsCase){
        ArrayList<List<Integer>> voisins = new ArrayList<>();
        int ligne, col;
        int[][] decalages = new int[][]{
                {-1, 0},
                {0, -1},
                {+1, 0},
                {0, +1}
        };

        for (int[] decalage : decalages){
            ligne = coordsCase.get(0) + decalage[0];
            col = coordsCase.get(1) + decalage[1];
            if (carte.dansBornes(ligne, col) && carte.peutMarcher(ligne, col))
                voisins.add(List.of(ligne, col));
        }

        return voisins;
    }

    /**
     * Retourne la prochaine case après {@code coordsCase}
     * dans le chemin optimal vers l'objectif
     *
     * @param coordsCase Une liste sous forme [ligne, colonne]
     *               qui représente les coordonnées d'une case
     *
     * @return Une liste sous forme [ligne, colonne]
     * qui représente les coordonnées de la prochaine case
     * après la case de coordonnées {@code coordsCase}
     * dans le chemin optimal vers l'objectif
     */
    public List<Integer> prochaineCase(List<Integer> coordsCase){
        return prochaineCase.get(coordsCase);
    }

    /**
     * Retourne vrai si la case de coordonnées {@code coordsCase}
     * peut atteindre l'objectif de la carte
     *
     * @param coordsCase Une liste sous forme [ligne, colonne]
     *               qui représente les coordonnées d'un case
     *
     * @return true si la case case de coordonnées {@code coordsCase}
     * peut atteindre l'objectif de la carte, false sinon
     */
    public boolean estBloquee(List<Integer> coordsCase){
        return !prochaineCase.containsKey(coordsCase);
    }
}
