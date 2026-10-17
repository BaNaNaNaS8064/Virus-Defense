package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class GestionnaireAttaqueRayonSimple extends GestionnaireAttaqueRayon {

    public GestionnaireAttaqueRayonSimple(Environnement environnement, Coordonees coordonees, double degats, ArrayList<Maladie> cibles){
        super(environnement, coordonees, degats, cibles);
    }

    @Override
    public final void attaqueCibles() {
        for (Maladie m : getCibles())
            attaque(m, getDegats());
    }
}
