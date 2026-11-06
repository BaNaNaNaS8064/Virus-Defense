package fr.iut.virusdefense.modele.positionnables.cellules.attaques;

import fr.iut.virusdefense.modele.utilitaires.Coordonees;
import fr.iut.virusdefense.modele.positionnables.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.List;

public abstract class Zone extends Attaque {
    private int age;
    private final int ageMaximal;
    private final double rayonZone;

    public Zone(Coordonees coordonees, List<Maladie> cibles, double degats, int ageMaximal, Alteration alteration, double rayonZone) {
        super(new Coordonees(coordonees), degats, alteration, cibles);
        age = 0;
        this.ageMaximal = ageMaximal;

        this.rayonZone = rayonZone;
    }

    public double getRayonZone() {
        return rayonZone;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean doitEtreRetiré(){
        return age > ageMaximal;
    }

    public void effetSpecial(){}

    @Override
    public final void agir() {
        effetSpecial();
        age++;
    }
}
