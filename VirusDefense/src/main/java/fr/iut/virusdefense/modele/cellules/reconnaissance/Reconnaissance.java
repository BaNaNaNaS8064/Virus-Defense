package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Positionnable;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class Reconnaissance extends Positionnable {
    private double portee;

    private int nombreCiblesMax;
    private ArrayList<Maladie> cibles;
    private PrioriteCible prioriteCible;

    public Reconnaissance(Coordonees coordonees, double portee, int nombreCiblesMax) {
        super(coordonees);
        this.portee = portee;
        this.nombreCiblesMax = nombreCiblesMax;
        cibles = new ArrayList<>();
        this.prioriteCible = new PrioPlusPV();
    }

    public ArrayList<Maladie> getCibles() {
        return cibles;
    }

    public void setNombreCiblesMax(int nombreCiblesMax){
        this.nombreCiblesMax = nombreCiblesMax;
    }

    public double getPortee() {
        return portee;
    }

    public void setPortee(double portee) {
        this.portee = portee;
    }

    public final boolean aPortee(Maladie m){
        return distanceEuclidienne(m) <= portee;
    }

    public final boolean aAuMoinsUneCible(){
        return !cibles.isEmpty();
    }

    public final boolean aAssezDeCibles(){
        return cibles.size() >= nombreCiblesMax;
    }

    public final boolean ciblesValides(){
         return cibles.stream().allMatch(this::estValide);
    }

    public boolean valide(){
        return ciblesValides() && aAssezDeCibles() ;
    }

    public abstract boolean estValide(Maladie m);

    private void changerCibles() {
        cibles.clear();
        cibles.addAll(Environnement.getUniqueInstance().getGestionnaireActeur().getMaladies());
        cibles.removeIf(cible -> !estValide(cible));
        cibles = prioriteCible.selectionnerCibles(cibles, nombreCiblesMax);
    }

    public void reconnaissanceSecondaire(){}

    public final void actualiser() {
        changerCibles();
        reconnaissanceSecondaire();
    }

}
