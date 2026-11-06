package fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.utilitaires.Coordonees;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;

public class GestionnaireAttaqueRayonSimple extends GestionnaireAttaqueRayon {

    public GestionnaireAttaqueRayonSimple(Coordonees coordonees, double degats, ArrayList<Maladie> cibles){
        super(coordonees, degats, cibles);
    }

    @Override
    public final void attaqueCibles() {
        for (Maladie m : getCibles())
            attaque(m, getDegats());
    }
}
