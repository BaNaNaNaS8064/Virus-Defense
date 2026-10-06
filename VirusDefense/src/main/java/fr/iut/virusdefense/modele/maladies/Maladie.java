package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.Acteur;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Positionnable;

import java.util.List;

public abstract class Maladie extends Positionnable implements Acteur {
    private double pv;
    private final double vitesse;
    private final int recompense;
    private double coefVitesse;

    public Maladie(Environnement environnement, double ligne, double colonne, int pv, double vitesse, int recompense){
        super(environnement, (int) ligne, (int) colonne);

        this.vitesse = vitesse;
        this.pv = pv;
        this.recompense = recompense;
        coefVitesse = 1;
    }

    public final int getRecompense(){
        return recompense;
    }


    public boolean estVivant(){
        return pv > 0;
    }

    public void mourir(){
        this.pv = 0;
    }

    public void prendreDegats(double degats){
        if (degats > 0)
            pv -= degats;
    }

    public boolean aAtteintLObjectif(){
        return getEnvironnement().getCarte().getObjectif().equals(position());
    }

    @Override
    public final void agir(){
        if (estVivant()) {
            coefVitesse = 1;
            capaciteActive();
            bouger();

            if (aAtteintLObjectif()) {
                infligerDegatsAuJoueur();
                mourir();
            }
        }
    }

    public void bouger(){
        List<Integer> destination = getEnvironnement().getDeplacement().prochaineCase(position());
        if (destination != null) {
            double distLigne = Math.abs(destination.get(0) + 0.5 - getLigne());
            double distColonne = Math.abs(destination.get(1) + 0.5 - getColonne());

            double distanceMax = Math.max(distLigne, distColonne);

            int directionLigne = Double.compare(destination.get(0) + 0.5, getLigne());
            int directionColonne = Double.compare(destination.get(1) + 0.5, getColonne());

            setLigne(getLigne() + vitesse * coefVitesse * directionLigne * distLigne / distanceMax);
            setColonne(getColonne() + vitesse * coefVitesse * directionColonne * distColonne / distanceMax);
        }
    }

    public abstract boolean peutSubirAlteration();

    public void infligerDegatsAuJoueur(){
        getEnvironnement().getJoueur().retirerPv((int) pv);
    }

    public void ralentir(double coefRalentissement){
        coefVitesse *= coefRalentissement;
    }

    public void capaciteActive(){}

    public void capaciteALaMort(){}
}
