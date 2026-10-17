package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.ActeurRetirable;
import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Positionnable;
import fr.iut.virusdefense.modele.deplacement.GestionnaireDeplacement;

import java.util.List;

public abstract class Maladie extends Positionnable implements ActeurRetirable {
    private double pv;
    private final double vitesse;
    private final int recompense;
    private double coefVitesse;
    private GestionnaireDeplacement gestionnaireDeplacement;

    public Maladie(Environnement environnement, double ligne, double colonne, int pv, double vitesse, int recompense){
        super(environnement, ligne+0.5, colonne+0.5);

        this.vitesse = vitesse;
        this.pv = pv;
        this.recompense = recompense;
        coefVitesse = 1;
        this.gestionnaireDeplacement = new GestionnaireDeplacement();
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

    @Override
    public boolean doitEtreRetiré(){
        return !estVivant();
    }

    public void bouger(){
        getCoordonees().copier(gestionnaireDeplacement.prochainEmplacement(this, vitesse * coefVitesse, new Coordonees(getEnvironnement().getDeplacement().prochaineCase(position()))));
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
