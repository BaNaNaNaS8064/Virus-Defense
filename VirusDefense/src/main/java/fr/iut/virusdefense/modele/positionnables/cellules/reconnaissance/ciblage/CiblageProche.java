package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

import java.util.ArrayList;

public class CiblageProche extends Ciblage{
    private Maladie maladiePlusProche;
    private double distanceMinMaladie;
    private final Coordonees coordonees;

    public CiblageProche(Coordonees coordonees) {
        super();
        this.coordonees = coordonees;
    }


    @Override
    public ArrayList<Maladie> selectionnerCibles(ArrayList<Maladie> cibles, int nombreCiblesMax) {
        initCiblesMaladies(cibles);

        while (cibleSelectionneesComplete(nombreCiblesMax)) {
            this.maladiePlusProche = getMaladiesDisponibles().get(0);
            this.distanceMinMaladie = this.maladiePlusProche.getCoordonees().distanceEuclidienne(this.coordonees.getLigne(), this.coordonees.getColonne());

            for (int i = 1; i < getMaladiesDisponibles().size(); i++) {
                Maladie maladie = getMaladiesDisponibles().get(i);
                remplacerSiConditionRempli(maladie);
            }
            triCiblesMaladie(this.maladiePlusProche);
        }
        return getCiblesSelectionnees();
    }

    @Override
    public void remplacerSiConditionRempli(Maladie maladie) {
        double distanceMaladie = maladie.getCoordonees().distanceEuclidienne(coordonees.getLigne(), coordonees.getColonne());
        if (distanceMaladie < this.distanceMinMaladie) {
            this.maladiePlusProche = maladie;
            this.distanceMinMaladie = distanceMaladie;
        }
    }
}

