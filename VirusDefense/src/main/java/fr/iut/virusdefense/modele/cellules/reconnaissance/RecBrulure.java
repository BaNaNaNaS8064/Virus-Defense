package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;

public class RecBrulure extends Reconnaissance{

    public RecBrulure(Coordonees coordonees, double portee, int nombreCiblesMax) {
        super(coordonees, portee, nombreCiblesMax);
    }

    @Override
    public boolean estValide(Maladie m) {
        return m.estVivant() && aPortee(m);
    }
}
