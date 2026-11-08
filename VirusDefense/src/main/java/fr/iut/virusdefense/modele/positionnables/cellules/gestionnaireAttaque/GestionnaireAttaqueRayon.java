package fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.positionnables.cellules.attaques.Rayon;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

import java.util.ArrayList;

public abstract class GestionnaireAttaqueRayon extends GestionnaireAttaque {

    public GestionnaireAttaqueRayon(Coordonees coordonees, double degats, ArrayList<Maladie> cibles){
        super(coordonees, degats, cibles);
    }

    public final void attaque(Maladie m, double degats){
        Environnement.getInstance().ajouterRayon(new Rayon(getCoordonees(), m, degats, 2, getAlteration()));
    }
}
