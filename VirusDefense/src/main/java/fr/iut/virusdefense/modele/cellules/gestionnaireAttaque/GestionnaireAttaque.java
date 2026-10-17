package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Positionnable;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class GestionnaireAttaque extends Positionnable {
    private final ArrayList<Maladie> cibles;
    private final ArrayList<Alteration> alterations;
    private double degats;

    public GestionnaireAttaque(Environnement environnement, Coordonees coordonees, double degats, ArrayList<Maladie> cibles){
        super(environnement, coordonees);
        this.cibles = cibles;
        this.alterations = new ArrayList<>();
        this.degats = degats;
    }

    public ArrayList<Maladie> getCibles(){
        return cibles;
    }

    public ArrayList<Alteration> getAlterations() {
        return alterations;
    }

    public double getDegats() {
        return degats;
    }

    public void setDegats(double degats) {
        this.degats = degats;
    }

    public void ajouterAlteration(Alteration alt){
        alterations.add(alt);
    }

    abstract public void attaqueCibles();

}
