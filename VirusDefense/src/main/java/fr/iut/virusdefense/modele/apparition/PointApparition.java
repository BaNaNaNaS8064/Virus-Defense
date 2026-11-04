package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Acteur;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Positionnable;
import fr.iut.virusdefense.modele.maladies.*;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

import java.util.ArrayList;

public class PointApparition extends Positionnable implements Acteur {

    private int delai;
    private ArrayList<ListeApparition> listes;

    public PointApparition(Environnement environnement, int ligne, int colonne){
        super(environnement, ligne, colonne);
        delai = 0;
        listes = new ArrayList<>();
    }

    public void ajouter(ListeApparition liste) {
        listes.add(liste);
    }

    @Override
    public void agir() {
        delai--;

        if (delai<=0){
            ListeApparition liste;

            for (int i = listes.size() - 1; i >= 0; i--) {
                liste = listes.get(i);
                if (liste.resteProchain()) {
                    ajouterDansLEnvironnement(liste.prochaineMaladie());
                    delai = liste.prochainDelai();
                    liste.avancer();
                }
                else {
                    listes.remove(i);
                }
            }
        }
    }

    private void ajouterDansLEnvironnement(CodeMaladie codeMaladie){
        Maladie m = FabriqueMaladie.créerMaladie(codeMaladie, getEnvironnement(), (int) getLigne(), (int) getColonne());
        if (m != null) {getEnvironnement().ajouterMaladie(m);}
    }
}
