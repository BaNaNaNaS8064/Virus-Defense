package fr.iut.virusdefense.modele.positionnables.maladies.capacites;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.List;

public class CapaciteComposite implements Capacite{

    private List<Capacite> capacites;

    public CapaciteComposite(List<Capacite> capacites) {
        this.capacites = capacites;
    }

    @Override
    public void activer(Maladie m) {
        capacites.forEach(c -> c.activer(m));
    }
}
