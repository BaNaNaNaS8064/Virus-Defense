package fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.priorite;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;

public interface Ciblage {
    public ArrayList<Maladie> selectionnerCibles(ArrayList<Maladie> cibles, int nombreCiblesMax);
}
