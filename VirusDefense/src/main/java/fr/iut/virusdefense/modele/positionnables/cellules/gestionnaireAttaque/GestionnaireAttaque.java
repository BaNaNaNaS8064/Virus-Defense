package fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.positionnables.Positionnable;
import fr.iut.virusdefense.modele.positionnables.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

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
