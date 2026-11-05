package fr.iut.virusdefense.modele.cellules.attaques;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class ZoneSimple extends Zone {
    public ZoneSimple(Coordonees coordonees, List<Maladie> cibles, double degats, int ageMaximal, List<Alteration> alterations, double rayonZone) {
        super(new Coordonees(coordonees), cibles, degats, ageMaximal, alterations, rayonZone);
        attaquer();
    }
}
