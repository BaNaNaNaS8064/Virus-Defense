package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.acteurs.Acteur;
import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.enums.Difficulte;
import fr.iut.virusdefense.modele.positionnables.Positionnable;
import fr.iut.virusdefense.modele.positionnables.maladies.FabriqueMaladie;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;

public class PointApparition extends Positionnable implements Acteur {

    private int delai;
    private ArrayList<ListeApparition> listes;

    public PointApparition(int ligne, int colonne){
        super(ligne, colonne);
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
        Maladie m;
        m = FabriqueMaladie.creerMaladie(codeMaladie, (int) getLigne(), (int) getColonne());

        if (m != null)
            Environnement.getInstance().ajouterMaladie(m);
    }
}
