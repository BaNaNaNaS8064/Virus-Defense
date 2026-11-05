package fr.iut.virusdefense.modele.maladies.Capacité;

import fr.iut.virusdefense.modele.Environnement;

public abstract class CapaciteMort {
    public CapaciteMort() {}

    public abstract void ActivationCapacite(Environnement environnement, int ligne, int colonne);
}
