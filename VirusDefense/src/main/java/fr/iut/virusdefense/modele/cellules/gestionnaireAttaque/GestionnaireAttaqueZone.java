package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaques.ZoneSimple;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class GestionnaireAttaqueZone extends GestionnaireAttaque {
    private final double rayonZone;

    public GestionnaireAttaqueZone(Environnement environnement, Coordonees coordonees, double degats, ArrayList<Maladie> cibles, double rayonZone){
        super(environnement, coordonees, degats, cibles);
        this.rayonZone = rayonZone;
    }

    @Override
    public final void attaqueCibles(){
        getEnvironnement().ajouterZone(new ZoneSimple(getEnvironnement(), getCoordonees(), getCibles() , getDegats(), 10 , getAlterations(), rayonZone));
    }
}
