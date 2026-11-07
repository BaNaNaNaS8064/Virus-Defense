package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;

public abstract class Ciblage {
    private final ArrayList<Maladie> ciblesSelectionnees;
    private final ArrayList<Maladie> maladiesDisponibles;


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

    public void initCiblesMaladies(ArrayList<Maladie> cibles){
        toutRetirerMaladieDisponibles();
        toutRetirerCiblesSelectionnees();
        addAllMaladiesDisponibles(cibles);
    }

    public void triCiblesMaladie(Maladie maladie){
        ajouterCiblesSelectionnees(maladie);
        retirerMaladieDisponible(maladie);
    }

    public boolean cibleSelectionneesComplete(int nombreCiblesMax){
        return (!getMaladiesDisponibles().isEmpty() && getCiblesSelectionnees().size() < nombreCiblesMax);
    }

    public abstract ArrayList<Maladie> selectionnerCibles(ArrayList<Maladie> cibles, int nombreCiblesMax);
}
