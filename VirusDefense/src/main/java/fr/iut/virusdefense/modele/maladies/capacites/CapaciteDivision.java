package fr.iut.virusdefense.modele.maladies.capacites;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.apparition.FabriqueMaladie;
import fr.iut.virusdefense.modele.maladies.Maladie;
import fr.iut.virusdefense.modele.maladies.VirusComposé;
import fr.iut.virusdefense.modele.utilitaires.CodeMaladie;

public class CapaciteDivision implements CapaciteMort {
    private final CodeMaladie codeDivise;

    public CapaciteDivision(CodeMaladie codeDivise) {
        this.codeDivise = codeDivise;
    }

    @Override
    public void activer(Maladie m){
        for (int i=0; i<2; i++)
                Environnement.getUniqueInstance().getGestionnaireActeur().getMaladies().add(FabriqueMaladie.créerMaladie(codeDivise, (int) m .getLigne(), (int) m .getColonne()));
    }
}
