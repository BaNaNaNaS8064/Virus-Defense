package fr.iut.virusdefense.modele.cellules.alteration;

import fr.iut.virusdefense.modele.ActeurRetirable;
import fr.iut.virusdefense.modele.Identifiable;
import fr.iut.virusdefense.modele.maladies.Maladie;

public abstract class Alteration extends Identifiable implements ActeurRetirable {
    private int duree;
    private Maladie maladie;

    public Alteration(int duree){
        super();
        this.duree = duree;
    }

    public Maladie getMaladie() {
        return maladie;
    }

    public void setMaladie(Maladie m) {
        this.maladie = m;
    }

    @Override
    public boolean doitEtreRetiré(){
        return (0 >= duree || !maladie.estVivant());
    }

    @Override
    public final void agir(){
        duree--;
        affecter();
    }

    public abstract void affecter();
}
