package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;

public class CiblagePlusDePV extends Ciblage {

    public CiblagePlusDePV() {
        super();
    }

    @Override
    public ArrayList<Maladie> selectionnerCibles(ArrayList<Maladie> cibles, int nombreCiblesMax) {
        toutRetirerMaladieDisponibles();
        toutRetirerCiblesSelectionnees();
        addAllMaladiesDisponibles(cibles);

        while (!getMaladiesDisponibles().isEmpty() && getCiblesSelectionnees().size() < nombreCiblesMax) {
            Maladie maladiePVMax = getMaladiesDisponibles().get(0);
            for (Maladie maladie : getMaladiesDisponibles()) {
                if (maladiePVMax.getPv() < maladie.getPv()) {
                    maladiePVMax = maladie;
                }
            }
            ajouterCiblesSelectionnees(maladiePVMax);
            retirerMaladieDisponible(maladiePVMax);
        }
        return getCiblesSelectionnees();
    }


}
