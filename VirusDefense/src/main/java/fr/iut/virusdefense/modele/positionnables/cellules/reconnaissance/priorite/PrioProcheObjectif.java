package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.priorite;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;
import java.util.List;

public class PrioProcheObjectif implements PrioriteCible {
    public PrioProcheObjectif() {}

    public ArrayList<Maladie> selectionnerCibles(ArrayList<Maladie> cibles, int nombreCiblesMax) {
        ArrayList<Maladie> ciblesSelectionnees = new ArrayList<>();
        ArrayList<Maladie> maladiesDisponibles = new ArrayList<>(cibles);
        List<Integer> objectif = Environnement.getUniqueInstance().getCarte().getObjectif();

        while (!maladiesDisponibles.isEmpty() && ciblesSelectionnees.size() < nombreCiblesMax) {
            Maladie maladiePlusProche = maladiesDisponibles.get(0);
            double distanceMinMaladie = maladiePlusProche.getCoordonees().distanceEuclidienne(objectif.get(0), objectif.get(1));

            for (int i = 1; i < maladiesDisponibles.size(); i++) {
                Maladie maladie = maladiesDisponibles.get(i);
                double distanceMaladie = maladie.getCoordonees().distanceEuclidienne(objectif.get(0), objectif.get(1));

                if (distanceMaladie < distanceMinMaladie) {
                    maladiePlusProche = maladie;
                    distanceMinMaladie = distanceMaladie;
                }
            }

            ciblesSelectionnees.add(maladiePlusProche);
            maladiesDisponibles.remove(maladiePlusProche);
        }
        return ciblesSelectionnees;
    }

}
