package fr.iut.virusdefense.modele.positionnables.maladies.decorateurs;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

public class Blindee extends DecorateurMaladie {
    public Blindee(Maladie maladie) {
        super(maladie);
        setPv(super.getPv() * 1.5);
    }
}
