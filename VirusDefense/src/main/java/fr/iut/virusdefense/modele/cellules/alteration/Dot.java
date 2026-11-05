package fr.iut.virusdefense.modele.cellules.alteration;

public class Dot extends Alteration {
    private final int degats;

    public Dot(int duree, int degats) {
        super(duree);
        this.degats = degats;
    }

    @Override
    public void affecter() {
        getMaladie().prendreDegats(degats);
    }

    @Override
    public Alteration copy() {
        Alteration temp = new Dot(getDuree(), degats);
        temp.setMaladie(this.getMaladie());
        return temp;
    }
}
