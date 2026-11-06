package fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.utilitaires.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.positionnables.cellules.attaques.Projectile;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;

import java.util.ArrayList;

public class GestionnaireAttaqueProjectile extends GestionnaireAttaque {

    public GestionnaireAttaqueProjectile(Coordonees coordonees, double degats, ArrayList<Maladie> cibles){
        super(coordonees, degats, cibles);
    }

    public void attaque(Maladie m){
        Environnement.getUniqueInstance().ajouterProjectile(new Projectile(getCoordonees(), m, getDegats(), getAlteration()));
    }

    public void attaqueCibles(){
        getCibles().forEach(this::attaque);
    }
}