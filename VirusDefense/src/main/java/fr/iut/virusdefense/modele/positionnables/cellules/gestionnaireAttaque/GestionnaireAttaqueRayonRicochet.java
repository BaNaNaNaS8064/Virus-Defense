package fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.utilitaires.Coordonees;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;

public class GestionnaireAttaqueRayonRicochet extends GestionnaireAttaqueRayon {

    public GestionnaireAttaqueRayonRicochet(Coordonees coordonees, double degats, ArrayList<Maladie> cibles){
        super(coordonees, degats, cibles);
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
