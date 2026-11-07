package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.positionnables.Positionnable;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.priorite.Ciblage;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

import java.util.ArrayList;

public abstract class Reconnaissance extends Positionnable {
    private double portee;

    private int nombreCiblesMax;
    private final ArrayList<Maladie> cibles;
    private final Ciblage ciblage;

    public Reconnaissance(Coordonees coordonees, double portee, int nombreCiblesMax, Ciblage ciblage) {
        super(coordonees);
        this.portee = portee;
        this.nombreCiblesMax = nombreCiblesMax;
        cibles = new ArrayList<>();
        this.ciblage = ciblage;
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

    public Ciblage getCiblage() {
        return ciblage;
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

    public void actualiser(){
        cibles.clear();
        cibles.addAll(Environnement.getUniqueInstance().getGestionnaireActeur().getMaladies());
        cibles.removeIf(cible -> !estValide(cible));
        ArrayList<Maladie> ciblesSelectionnees = ciblage.selectionnerCibles(cibles, nombreCiblesMax);
        cibles.clear();
        cibles.addAll(ciblesSelectionnees);
    }



}
