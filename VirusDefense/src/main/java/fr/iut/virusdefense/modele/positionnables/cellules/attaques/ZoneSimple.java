package fr.iut.virusdefense.modele.positionnables.cellules.attaques;

import fr.iut.virusdefense.modele.positionnables.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

import java.util.List;

public class ZoneSimple extends Zone {

    public ZoneSimple(Coordonees coordonees, List<Maladie> cibles, double degats, int ageMaximal, Alteration alteration, double rayonZone) {
        super(new Coordonees(coordonees), cibles, degats, ageMaximal, alteration, rayonZone);
        attaquer();
    }
}
