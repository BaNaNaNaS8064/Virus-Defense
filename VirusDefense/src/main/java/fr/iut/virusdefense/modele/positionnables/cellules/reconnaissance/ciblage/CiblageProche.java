package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

import java.util.ArrayList;

public class CiblageProche extends Ciblage{
    private final Coordonees coordonees;

    public CiblageProche(Coordonees coordonees) {
        super();
        this.coordonees = coordonees;
    }


    @Override
    public ArrayList<Maladie> selectionnerCibles(ArrayList<Maladie> cibles, int nombreCiblesMax) {
        initCiblesMaladies(cibles);

        while (cibleSelectionneesComplete(nombreCiblesMax)) {
            Maladie maladiePlusProche = getMaladiesDisponibles().get(0);
            double distanceMinMaladie = maladiePlusProche.getCoordonees().distanceEuclidienne(coordonees.getLigne(), coordonees.getColonne());

            for (int i = 1; i < getMaladiesDisponibles().size(); i++) {
                Maladie maladie = getMaladiesDisponibles().get(i);
                double distanceMaladie = maladie.getCoordonees().distanceEuclidienne(coordonees.getLigne(), coordonees.getColonne());

                if (distanceMaladie < distanceMinMaladie) {
                    maladiePlusProche = maladie;
                    distanceMinMaladie = distanceMaladie;
                }
            }
            triCiblesMaladie(maladiePlusProche);
        }

        return getCiblesSelectionnees();
    }
}

