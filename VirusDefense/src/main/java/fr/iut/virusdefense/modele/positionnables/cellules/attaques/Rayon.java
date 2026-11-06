package fr.iut.virusdefense.modele.positionnables.cellules.attaques;

import fr.iut.virusdefense.modele.positionnables.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

public class Rayon extends Attaque {
    private int age;
    private final int ageMaximal;

    public Rayon(Coordonees coordonees, Maladie cible, double degats, int ageMaximal, Alteration alteration) {
        super(new Coordonees(coordonees), degats, alteration, cible);
        age = 0;
        this.ageMaximal = ageMaximal;

        attaquer();
    }

    @Override
    public boolean doitEtreRetiré(){
        return age > ageMaximal;
    }

    @Override
    public void agir(){
        age++;
    }
}
