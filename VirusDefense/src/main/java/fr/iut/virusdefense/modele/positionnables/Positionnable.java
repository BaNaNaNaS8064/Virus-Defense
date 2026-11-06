package fr.iut.virusdefense.modele.positionnables;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;
import javafx.beans.property.DoubleProperty;

import java.util.List;

public abstract class Positionnable extends Identifiable {

    private final Coordonees coordonees;

    public Positionnable(int ligne, int colonne){
        this(new Coordonees(ligne+0.5, colonne+0.5));
    }

    public Positionnable(double ligne, double colonne){
        this(new Coordonees(ligne,colonne));
    }

    public Positionnable(Coordonees coordonees){
        super();
        this.coordonees = coordonees;
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

            if (!Environnement.getUniqueInstance().getCarte().peutVoirAuTravers((int)positionLigne, (int)positionColonne, ignorerCellules) && ((int)positionLigne != (int)getLigne() || (int)positionColonne != (int)getColonne()))
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
            return Environnement.getUniqueInstance().getCarte().peutVoirAuTravers((int)getLigne(), (int)getColonne(), ignorerCellules);

        boolean bloque = false;

        while (!bloque && positionLigne != ligne){
            positionLigne += direction;
            if (!Environnement.getUniqueInstance().getCarte().peutVoirAuTravers(positionLigne, (int)getColonne(), ignorerCellules))
                bloque = true;
        }

        return !bloque;
    }

}
