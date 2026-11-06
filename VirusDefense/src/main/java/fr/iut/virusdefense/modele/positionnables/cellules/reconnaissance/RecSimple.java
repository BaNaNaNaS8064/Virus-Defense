package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance;

import fr.iut.virusdefense.modele.utilitaires.Coordonees;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;


public class RecSimple extends Reconnaissance{

    public RecSimple(Coordonees coordonees, double portee, int nombreCiblesMax){
        super(coordonees, portee, nombreCiblesMax);
    }

    @Override
    public boolean estValide(Maladie m) {
        return m.estVivant() && aPortee(m) && voit(m,true);
    }
}
