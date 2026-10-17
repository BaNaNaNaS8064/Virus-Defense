package fr.iut.virusdefense.modele.cellules.attaques;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.cellules.reconnaissance.Reconnaissance;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class ZonePersistante extends Zone{
    private final Reconnaissance reconnaissance;
    private final int delai;
    public ZonePersistante(Environnement environnement, Coordonees coordonees, List<Maladie> cibles, double degats, int ageMaximal, List<Alteration> alterations, double rayonZone, Reconnaissance reconnaissance) {
        super(environnement, new Coordonees(coordonees), cibles, degats, ageMaximal, alterations, rayonZone);
        this.reconnaissance = reconnaissance;
        this.delai = 50;
    }

    public void actualiser(){
        if (getAge()%delai == 0) {
            reconnaissance.actualiser();
            setCibles(reconnaissance.getCibles());
            attaquer();
        }
    }

    @Override
    public void effetSpecial() {
        actualiser();
    }
}
