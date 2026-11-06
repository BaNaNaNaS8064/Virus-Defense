package fr.iut.virusdefense.modele.maladies.capacites;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.VirusComposé;

public class CapaciteDivision extends CapaciteMort {
    private final int stade;

    public CapaciteDivision(int stade) {
        this.stade = stade;
    }

    public void activer(Maladie m){
        if(stade<3)
            for (int i=0; i<2; i++)
                Environnement.getUniqueInstance().getGestionnaireActeur().getMaladies().add(new VirusComposé((int) m.getLigne(), (int) m.getColonne(), stade+1));
    }
}
