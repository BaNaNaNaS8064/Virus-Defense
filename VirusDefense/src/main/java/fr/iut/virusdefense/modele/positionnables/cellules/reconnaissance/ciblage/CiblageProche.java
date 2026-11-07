package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

import java.util.ArrayList;

public class CiblageProche implements Ciblage{
    private final Coordonees coordonees;

    public CiblageProche(Coordonees coordCellule) {
        this.coordonees = coordCellule;
    }


    @Override
    public ArrayList<Maladie> selectionnerCibles(ArrayList<Maladie> cibles, int nombreCiblesMax) {
        ArrayList<Maladie> ciblesSelectionnees = new ArrayList<>();
        ArrayList<Maladie> maladiessDisponibles = new ArrayList<>(cibles);

        while (!maladiessDisponibles.isEmpty() && ciblesSelectionnees.size() < nombreCiblesMax) {
            Maladie maladiePlusProche = maladiessDisponibles.get(0);
            double distanceMinMaladie = maladiePlusProche.getCoordonees().distanceEuclidienne(coordonees.getLigne(), coordonees.getColonne());

            for (int i = 1; i < maladiessDisponibles.size(); i++) {
                Maladie maladie = maladiessDisponibles.get(i);

                double distanceMaladie = maladie.getCoordonees().distanceEuclidienne(coordonees.getLigne(), coordonees.getColonne());

                if (distanceMaladie < distanceMinMaladie) {
                    maladiePlusProche = maladie;
                    distanceMinMaladie = distanceMaladie;
                }
            }

            ciblesSelectionnees.add(maladiePlusProche);
            maladiessDisponibles.remove(maladiePlusProche);
        }

        return ciblesSelectionnees;
    }
}

