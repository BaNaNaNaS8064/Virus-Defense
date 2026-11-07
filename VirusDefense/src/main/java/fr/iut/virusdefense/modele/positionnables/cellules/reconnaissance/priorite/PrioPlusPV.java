package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.priorite;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;

public class PrioPlusPV implements PrioriteCible {

    public PrioPlusPV() {}

    @Override
    public ArrayList<Maladie> selectionnerCibles(ArrayList<Maladie> cibles, int nombreCiblesMax) {
        ArrayList<Maladie> ciblesSelectionnees = new ArrayList<>();
        ArrayList<Maladie> maladiesDisponibles = new ArrayList<>(cibles);

        while (!maladiesDisponibles.isEmpty() && ciblesSelectionnees.size() < nombreCiblesMax) {
            Maladie maladiePVMax = maladiesDisponibles.get(0);
            for (Maladie maladie : maladiesDisponibles) {
                if (maladiePVMax.getPv() < maladie.getPv()) {
                    maladiePVMax = maladie;
                }
            }
            ciblesSelectionnees.add(maladiePVMax);
            maladiesDisponibles.remove(maladiePVMax);
        }
        return ciblesSelectionnees;
    }
}
