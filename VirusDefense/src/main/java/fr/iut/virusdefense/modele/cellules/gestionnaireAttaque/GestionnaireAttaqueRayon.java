package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaques.Rayon;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public abstract class GestionnaireAttaqueRayon extends GestionnaireAttaque {

    public GestionnaireAttaqueRayon(Environnement environnement, Coordonees coordonees, double degats, ArrayList<Maladie> cibles){
        super(environnement, coordonees, degats, cibles);
    }

    public final void attaque(Maladie m, double degats){
        getEnvironnement().ajouterRayon(new Rayon(getEnvironnement(), getCoordonees(), m, degats, 2, getAlterations()));
    }
}
