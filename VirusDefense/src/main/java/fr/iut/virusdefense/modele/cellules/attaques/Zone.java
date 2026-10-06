package fr.iut.virusdefense.modele.cellules.attaques;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public abstract class Zone extends Attaque {
    private int age;
    private final int ageMaximal;
    private final double rayonZone;

    public Zone(Environnement environnement, double ligne, double colonne, List<Maladie> cibles, double degats, int ageMaximal, List<Alteration> alterations, double rayonZone) {
        super(environnement, ligne, colonne, degats, alterations, cibles);
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
