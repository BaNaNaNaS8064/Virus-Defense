package fr.iut.virusdefense.modele.positionnables.cellules.attaques;

import fr.iut.virusdefense.modele.positionnables.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.Reconnaissance;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

import java.util.List;

public class ZonePersistante extends Zone{
    private final Reconnaissance reconnaissance;
    private final int delai;

    public ZonePersistante(Coordonees coordonees, List<Maladie> cibles, double degats, int ageMaximal, Alteration alteration, double rayonZone, Reconnaissance reconnaissance) {
        super(new Coordonees(coordonees), cibles, degats, ageMaximal, alteration, rayonZone);
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
