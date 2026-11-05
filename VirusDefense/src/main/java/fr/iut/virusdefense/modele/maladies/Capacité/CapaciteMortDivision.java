package fr.iut.virusdefense.modele.maladies.Capacité;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.VirusComposé;

public class CapaciteMortDivision extends CapaciteMort {
    private final int stade;

    public CapaciteMortDivision(int stade) {
        this.stade = stade;
    }

    public void ActivationCapacite(Environnement environnement, int ligne, int colonne){
        if(stade<3)
            for (int i=0; i<2; i++)
                environnement.getGestionnaireActeur().getMaladies().add(new VirusComposé(environnement, ligne, colonne, stade+1));
    }
}
