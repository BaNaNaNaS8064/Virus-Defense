package fr.iut.virusdefense.modele.cellules.attaques;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.deplacement.GestionnaireDeplacement;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class Projectile extends Attaque {
    private boolean cibleTouché;
    private GestionnaireDeplacement gestionnaireDeplacement;

    public Projectile(Environnement environnement, Coordonees coordonees, Maladie cible, double degats, List<Alteration> alterations){
        super(environnement, new Coordonees(coordonees), degats, alterations, cible);

        this.cibleTouché = false;
        this.gestionnaireDeplacement = new GestionnaireDeplacement();
    }

    public boolean getCibleTouché(){
        return cibleTouché;
    }

    @Override
    public void agir(){
        getCoordonees().copier(gestionnaireDeplacement.prochainEmplacement(this, 0.15, getCibles().get(0).getCoordonees()));

        if(Math.abs(getCibles().get(0).getLigne() - getLigne()) <= 0.5 && Math.abs(getCibles().get(0).getColonne() - getColonne()) <= 0.5){
            attaquer();
            cibleTouché = true;
        }
    }
}
