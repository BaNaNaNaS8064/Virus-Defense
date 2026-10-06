package fr.iut.virusdefense.modele.cellules.gestionnaireAttaque;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.attaques.Projectile;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.ArrayList;

public class GestionnaireAttaqueProjectile extends GestionnaireAttaque {

    public GestionnaireAttaqueProjectile(Environnement environnement, Coordonees coordonees, double degats, ArrayList<Maladie> cibles){
        super(environnement, coordonees, degats, cibles);
    }

    public void attaque(Maladie m){
        getEnvironnement().ajouterProjectile(new Projectile(getEnvironnement(), getCoordonees(), m, getDegats(), getAlterations()));
    }

    public void attaqueCibles(){
        getCibles().forEach(this::attaque);
    }
}