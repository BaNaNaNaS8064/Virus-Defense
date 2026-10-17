package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class GestionnaireAttaqueRayonRicochet extends GestionnaireAttaqueRayon {

    public GestionnaireAttaqueRayonRicochet(Environnement environnement, Coordonees coordonees, double degats, ArrayList<Maladie> cibles){
        super(environnement, coordonees, degats, cibles);
    }

    @Override
    public void attaqueCibles() {
        double posLigne = getLigne(), posColonne = getColonne();
        Maladie m;

        for (int i=0; i < getCibles().size(); i++) {
            m = getCibles().get(i);
            attaque(m, getDegats() * Math.pow(0.66, i));
            setLigne(m.getLigne());
            setColonne(m.getColonne());
        }
        setLigne(posLigne);
        setColonne(posColonne);
    }
}
