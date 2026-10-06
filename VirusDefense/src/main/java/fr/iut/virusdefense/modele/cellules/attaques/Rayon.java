package fr.iut.virusdefense.modele.cellules.attaques;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class Rayon extends Attaque {
    private int age;
    private final int ageMaximal;

    public Rayon(Environnement environnement, double ligne, double colonne, Maladie cible, double degats, int ageMaximal, List<Alteration> alterations) {
        super(environnement, ligne, colonne, degats, alterations, cible);
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
