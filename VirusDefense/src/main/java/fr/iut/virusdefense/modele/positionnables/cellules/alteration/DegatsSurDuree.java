package fr.iut.virusdefense.modele.positionnables.cellules.alteration;

public class DegatsSurDuree extends Alteration {
    private final int degats;

    public DegatsSurDuree(int duree, int degats) {
        super(duree);
        this.degats = degats;
    }

    @Override
    public void affecter() {
        getMaladie().prendreDegats(degats);
    }

    @Override
    public Alteration copy() {
        Alteration temp = new DegatsSurDuree(getDuree(), degats);
        temp.setMaladie(this.getMaladie());
        return temp;
    }
}
