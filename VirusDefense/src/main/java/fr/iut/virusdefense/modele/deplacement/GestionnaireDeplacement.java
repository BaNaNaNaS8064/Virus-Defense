package fr.iut.virusdefense.modele.deplacement;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Positionnable;

public class GestionnaireDeplacement {
    public Coordonees prochainEmplacement(Positionnable positionnable, double vitesse, Coordonees destination) {
        if (destination == null || positionnable == null)
            return null;

        double distLigne = Math.abs(destination.getLigne() - positionnable.getLigne());
        double distColonne = Math.abs(destination.getColonne() - positionnable.getColonne());

        double distanceMax = Math.max(distLigne, distColonne);

        int directionLigne = Double.compare(destination.getLigne(), positionnable.getLigne());
        int directionColonne = Double.compare(destination.getColonne(), positionnable.getColonne());

        double prochaineLigne = positionnable.getLigne() + vitesse * directionLigne * distLigne / distanceMax;
        double prochaineColonne = positionnable.getColonne() + vitesse * directionColonne * distColonne / distanceMax;

        return new Coordonees(prochaineLigne, prochaineColonne);
    }

}
