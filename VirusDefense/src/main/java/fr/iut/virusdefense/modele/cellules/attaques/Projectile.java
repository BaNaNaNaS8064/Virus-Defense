package fr.iut.virusdefense.modele.cellules.attaques;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.alteration.Alteration;
import fr.iut.virusdefense.modele.maladies.Maladie;

import java.util.List;

public class Projectile extends Attaque {
    private boolean cibleTouché;

    public Projectile(Environnement environnement, double ligne, double colonne, Maladie cible, double degats, List<Alteration> alterations){
        super(environnement, ligne, colonne, degats, alterations, cible);

        this.cibleTouché = false;
    }

    @Override
    public boolean doitEtreRetiré(){
        return cibleTouché;
    }

    @Override
    public void agir(){
        List<Integer> destination = getCibles().get(0).position();
        if (destination != null) {
            double distLigne = Math.abs(destination.get(0) + 0.5 - getLigne());
            double distColonne = Math.abs(destination.get(1) + 0.5 - getColonne());

            double distanceMax = Math.max(distLigne, distColonne);

            int directionLigne = Double.compare(destination.get(0) + 0.5, getLigne());
            int directionColonne = Double.compare(destination.get(1) + 0.5, getColonne());

            setLigne(getLigne() + 0.15 * directionLigne * distLigne / distanceMax);
            setColonne(getColonne() + 0.15 * directionColonne * distColonne / distanceMax);
        }

        if(Math.abs(getCibles().get(0).getLigne() - getLigne()) <= 0.5 && Math.abs(getCibles().get(0).getColonne() - getColonne()) <= 0.5){
            attaquer();
            cibleTouché = true;
        }
    }
}
