package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Positionnable;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class GestionnaireAttaque extends Positionnable {
    private final ArrayList<Maladie> cibles;
    private Alteration alteration;
    private double degats;

    public GestionnaireAttaque(Coordonees coordonees, double degats, ArrayList<Maladie> cibles){
        super(coordonees);
        this.cibles = cibles;
        this.alteration = null;
        this.degats = degats;
    }

    public ArrayList<Maladie> getCibles(){
        return cibles;
    }

    public Alteration getAlteration() {
        return alteration;
    }

    public double getDegats() {
        return degats;
    }

    public void setDegats(double degats) {
        this.degats = degats;
    }

    public void setAlteration(Alteration alt){
        alteration = alt;
    }

    abstract public void attaqueCibles();

}
