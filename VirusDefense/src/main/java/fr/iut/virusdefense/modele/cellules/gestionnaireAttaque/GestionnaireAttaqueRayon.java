package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaques.Rayon;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class GestionnaireAttaqueRayon extends GestionnaireAttaque {

    public GestionnaireAttaqueRayon(Coordonees coordonees, double degats, ArrayList<Maladie> cibles){
        super(coordonees, degats, cibles);
    }

    public final void attaque(Maladie m, double degats){
        Environnement.getUniqueInstance().ajouterRayon(new Rayon(getCoordonees(), m, degats, 2, getAlterations()));
    }
}
