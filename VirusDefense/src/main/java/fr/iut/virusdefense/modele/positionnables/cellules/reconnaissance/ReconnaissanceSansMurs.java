package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance;

import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage.Ciblage;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

public class ReconnaissanceSansMurs extends Reconnaissance{

    public ReconnaissanceSansMurs(Coordonees coordonees, double portee, int nombreCiblesMax, Ciblage ciblage) {
        super(coordonees, portee, nombreCiblesMax, ciblage);
    }

    @Override
    public boolean estValide(Maladie m) {
        return m.estVivant() && aPortee(m);
    }
}
