package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.enums.CodeMaladie;

import java.util.ArrayList;

/**
 * Décrit une liste d'apparition d'ennemis constituée de :
 * <ul>
 *     <li> Une liste de codeMaladie à faire apparaître </li>
 *     <li> Une liste de délais entre les maladies</li>
 * </ul>
 *
 * Ne se charge pas de faire apparaître les maladies
 */
public class ListeApparition {

    /// Le rang actuel dans la liste d'apparition
    private int curseur; // positif

    /**
     * Une liste de codeMaladie <br>
     * Devrait faire la même taille que {@code listeDelais}
     */
    private ArrayList<CodeMaladie> listeMaladies;

    /**
     * Une liste de délais <br>
     * Devrait faire la même taille que {@code listeMaladies}
     */
    private ArrayList<Integer> listeDelais;

    /**
     * Créé une nouvelle liste d'apparitions vide
     */
    public ListeApparition(){
        curseur = 0;
        listeMaladies = new ArrayList<>();
        listeDelais = new ArrayList<>();
    }

    /**
     * Ajoute une apparition à la liste
     * @param codeMaladie le code de la maladie à faire apparaître
     * @param delai le délai d'apparition après avoir fait apparaître la maladie
     */
    public void ajouter(CodeMaladie codeMaladie, int delai){
        listeMaladies.add(codeMaladie);
        listeDelais.add(delai);
    }

    /**
     * Retourne vrai s'il reste une/des maladie(s) à faire apparaître, faux sinon
     * @return true s'il reste une/des maladie(s) à faire apparaître, false sinon
     */
    public boolean resteProchain(){
        return curseur < Math.min(listeMaladies.size(), listeDelais.size());
    }

    /**
     * Retourne le {@code CodeMaladie} de la prochaine maladie
     * SANS AVANCER D'UN RANG <br>
     * Ne se soucie pas de s'il y a une prochaine maladie
     * @return le CodeMaladie de la prochaine maladie
     */
    public CodeMaladie prochaineMaladie(){
        return listeMaladies.get(curseur);
    }

    /**
     * Retourne le délai d'apparition de la prochaine maladie
     * SANS AVANCER D'UN RANG <br>
     * Ne se soucie pas de s'il y a un prochain délai
     * @return le délai d'apparition de la prochaine maladie
     */
    public int prochainDelai(){
        return listeDelais.get(curseur);
    }

    /**
     * Avance d'un rang dans la liste d'apparition <br>
     *
     * Ne se soucie pas de s'il reste une/des apparition(s)
     */
    public void avancer(){
        curseur++;
    }

}
