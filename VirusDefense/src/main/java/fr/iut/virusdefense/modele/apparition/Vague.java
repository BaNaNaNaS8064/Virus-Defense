package fr.iut.virusdefense.modele.apparition;

import java.util.ArrayList;

/**
 * Une vague est une liste de listes d'apparitions
 */
public class Vague {

    /**
     * Décrit la liste des listes d'apparitions de la vague
     */
    private final ArrayList<ListeApparition> listeApparitions;

    /**
     * Créé une nouvelle vague vide (sans listes d'apparitions)
     */
    public Vague(){
        listeApparitions = new ArrayList<>();
    }

    /**
     * @return la liste de listes d'apparitions de la vagues
     */
    public ArrayList<ListeApparition> getListeApparitions() {
        return listeApparitions;
    }

    /**
     * Ajoute {@code listeApparition} dans la vague
     * @param listeApparition la liste d'apparition à ajouter à cette vague
     */
    public void ajouter(ListeApparition listeApparition){
        listeApparitions.add(listeApparition);
    }

    /**
     * @return true si la vague est terminée, false sinon
     */
    public boolean estTerminee(){
        return listeApparitions.stream().noneMatch(ListeApparition::resteProchain);
    }

}
