package fr.iut.virusdefense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Coordonees {
    private final DoubleProperty ligneProperty;
    private final DoubleProperty colonneProperty;

    public Coordonees(double ligne,double colonne){
        this.ligneProperty = new SimpleDoubleProperty(ligne);
        this.colonneProperty = new SimpleDoubleProperty(colonne);
    }

    public double getLigne() {
        return ligneProperty.getValue();
    }
    public final void setLigne(double y){
        this.ligneProperty.setValue(y);
    }

    public final DoubleProperty ligneProperty(){
        return ligneProperty;
    }


    public double getColonne() {
        return colonneProperty.getValue();
    }

    public final void setColonne(double colonne){
        this.colonneProperty.setValue(colonne);
    }

    public final DoubleProperty colonneProperty(){
        return colonneProperty;
    }

    public double distanceEuclidienne(double ligne, double colonne){
        return Math.sqrt(Math.pow((getLigne() - ligne), 2) + Math.pow((getColonne() - colonne), 2));
    }

}
