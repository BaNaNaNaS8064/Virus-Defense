package fr.iut.virusdefense.modele.cellules.attaques;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class Rayon extends Attaque {
    private int age;
    private final int ageMaximal;

    public Rayon(Environnement environnement, Coordonees coordonees, Maladie cible, double degats, int ageMaximal, List<Alteration> alterations) {
        super(environnement, new Coordonees(coordonees), degats, alterations, cible);
        age = 0;
        this.ageMaximal = ageMaximal;

        attaquer();
    }

    public boolean aDepasseAgeMaximal(){
        return age > ageMaximal;
    }

    @Override
    public void agir(){
        age++;
    }
}
