package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;

public abstract class Ciblage {
    private ArrayList<Maladie> ciblesSelectionnees;
    private ArrayList<Maladie> maladiesDisponibles;


    public Ciblage() {
        ciblesSelectionnees = new ArrayList<>();
        maladiesDisponibles = new ArrayList<>();
    }

    public ArrayList<Maladie> getCiblesSelectionnees() {
        return ciblesSelectionnees;
    }

    public ArrayList<Maladie> getMaladiesDisponibles() {
        return maladiesDisponibles;
    }

    public void addAllMaladiesDisponibles(ArrayList<Maladie> maladiesDisponibles) {
        this.maladiesDisponibles.addAll(maladiesDisponibles);
    }

    public void retirerMaladieDisponible(Maladie maladie){
        this.maladiesDisponibles.remove(maladie);
    }

    public void ajouterCiblesSelectionnees(Maladie cible){
        this.ciblesSelectionnees.add(cible);
    }

    public void toutRetirerCiblesSelectionnees(){
        this.ciblesSelectionnees.clear();
    }

    public void toutRetirerMaladieDisponibles(){
        this.maladiesDisponibles.clear();
    }

    public abstract ArrayList<Maladie> selectionnerCibles(ArrayList<Maladie> cibles, int nombreCiblesMax);
}
