package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

public class ReconnaissanceSimple extends Reconnaissance{

    public ReconnaissanceSimple(Coordonees coordonees, double portee, int nombreCiblesMax){
        super(coordonees, portee, nombreCiblesMax);
    }

    @Override
    public boolean estValide(Maladie m) {
        return m.estVivant() && aPortee(m) && voit(m,true);
    }
}
