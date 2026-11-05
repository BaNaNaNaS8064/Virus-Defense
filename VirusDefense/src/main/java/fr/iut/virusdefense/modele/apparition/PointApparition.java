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
        switch (codeMaladie){
            case BACTERIEBANALE:
                Environnement.getUniqueInstance().ajouterMaladie(new BacterieBanale((int) getLigne(), (int) getColonne()));
                break;
            case PARASITE:
                Environnement.getUniqueInstance().ajouterMaladie(new Parasite((int) getLigne(), (int) getColonne()));
                break;
            case VIRUS:
                Environnement.getUniqueInstance().ajouterMaladie(new Virus((int) getLigne(), (int) getColonne()));
                break;
            case VIRUSCOMPOSE_1:
                Environnement.getUniqueInstance().ajouterMaladie(new VirusComposé((int) getLigne(), (int) getColonne()));
                break;
            case PETITCHAMPIGNON:
                Environnement.getUniqueInstance().ajouterMaladie(new PetitChampignon((int) getLigne(), (int) getColonne()));
                break;
            case GRANDCHAMPIGNON:
                Environnement.getUniqueInstance().ajouterMaladie(new GrandChampignon((int) getLigne(), (int) getColonne()));
                break;
            case TUMEUR:
                Environnement.getUniqueInstance().ajouterMaladie(new Tumeur((int) getLigne(), (int) getColonne()));
                break;
        }
    }
}
