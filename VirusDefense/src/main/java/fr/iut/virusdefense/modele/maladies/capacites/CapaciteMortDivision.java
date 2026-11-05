package fr.iut.virusdefense.modele.maladies.capacites;

import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.VirusComposé;

public class CapaciteMortDivision extends CapaciteMort {
    private final int stade;

    public CapaciteMortDivision(int stade) {
        this.stade = stade;
    }

    public void Activer(Maladie m){
        if(stade<3)
            for (int i=0; i<2; i++)
                m.getEnvironnement().getGestionnaireActeur().getMaladies().add(new VirusComposé(m.getEnvironnement(), m.getLigne(), m.getColonne(), stade+1));
    }
}
