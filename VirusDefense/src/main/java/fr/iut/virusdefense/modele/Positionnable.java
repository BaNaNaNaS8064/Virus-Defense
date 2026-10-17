package fr.iut.virusdefense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;

public abstract class Positionnable extends Identifiable{

    private final Environnement environnement;
    private final Coordonees coordonees;

    public Positionnable(Environnement environnement, int ligne, int colonne){
        this(environnement, ligne + 0.5, colonne + 0.5);
    }

    public Positionnable(Environnement environnement, double ligne, double colonne){
        this(environnement, new Coordonees(ligne,colonne));
    }

    public Positionnable(Environnement environnement, Coordonees coordonees){
        super();
        this.environnement = environnement;
        this.coordonees = coordonees;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public final double getLigne(){
        return coordonees.getLigne();
    }

    public final void setLigne(double ligne){
        coordonees.setLigne(ligne);
    }

    public final DoubleProperty ligneProperty(){
        return coordonees.ligneProperty();
    }

    public final double getColonne(){
        return coordonees.getColonne();
    }

    public final void setColonne(double colonne){
        coordonees.setColonne(colonne);
    }

    public final DoubleProperty colonneProperty(){
        return coordonees.colonneProperty();
    }

    public Coordonees getCoordonees() {
        return coordonees;
    }

    public List<Integer> position(){
        return List.of((int) getLigne(), (int) getColonne());
    }

    public double distanceEuclidienne(Positionnable e){
        return coordonees.distanceEuclidienne(e.getLigne(), e.getColonne());
    }

    public boolean voit(Positionnable e, boolean ignorerCellules){
        return voit(e.getLigne(), e.getColonne(), ignorerCellules);
    }

    public boolean voit(double ligne, double colonne, boolean ignorerCellules){
        int nombreDePoints = 100;

        double positionLigne, positionColonne = getColonne();

        double distColonne = colonne - getColonne();
        if (Math.abs(distColonne) < 1E-10)
            return voitVertical((int)ligne, ignorerCellules);

        double pente = (ligne - getLigne()) / (distColonne);
        double ordoneeOrigine = getLigne() - pente * getColonne();

        boolean bloque = false;
        int i=0;

        while (!bloque && i<nombreDePoints){
            positionColonne += distColonne / nombreDePoints;
            positionLigne = pente * positionColonne + ordoneeOrigine;

            if (!getEnvironnement().getCarte().peutVoirAuTravers((int)positionLigne, (int)positionColonne, ignorerCellules) && ((int)positionLigne != (int)getLigne() || (int)positionColonne != (int)getColonne()))
                bloque = true;

            i++;
        }

        return !bloque;
    }

    private boolean voitVertical(int ligne, boolean ignorerCellules){
        int positionLigne = (int)getLigne();
        int direction;
        if (ligne < getLigne())
            direction = -1;
        else if (ligne > getLigne())
            direction = 1;
        else
            return getEnvironnement().getCarte().peutVoirAuTravers((int)getLigne(), (int)getColonne(), ignorerCellules);

        boolean bloque = false;

        while (!bloque && positionLigne != ligne + direction){
            positionLigne += direction;
            if (!getEnvironnement().getCarte().peutVoirAuTravers(positionLigne, (int)getColonne(), ignorerCellules))
                bloque = true;
        }

        return !bloque;
    }

}
