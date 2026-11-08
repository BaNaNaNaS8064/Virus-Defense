package fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.positionnables.cellules.attaques.ZoneSimple;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

import java.util.ArrayList;

public class GestionnaireAttaqueZone extends GestionnaireAttaque {
    private final double rayonZone;

    public GestionnaireAttaqueZone(Coordonees coordonees, double degats, ArrayList<Maladie> cibles, double rayonZone){
        super(coordonees, degats, cibles);
        this.rayonZone = rayonZone;
    }

    @Override
    public final void attaqueCibles(){
        Environnement.getInstance().ajouterZone(new ZoneSimple(getCoordonees(), getCibles() , getDegats(), 10 , getAlteration(), rayonZone));
    }
}
