package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.ActeurRetirable;
import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Positionnable;
import fr.iut.virusdefense.modele.deplacement.GestionnaireDeplacement;
import fr.iut.virusdefense.modele.maladies.capacites.CapaciteActive;
import fr.iut.virusdefense.modele.maladies.capacites.CapaciteMort;

public abstract class Maladie extends Positionnable implements ActeurRetirable {
    private double pv;
    private final double vitesse;
    private final int recompense;
    private double coefVitesse;
    private GestionnaireDeplacement gestionnaireDeplacement;
    private CapaciteActive capaciteActive;
    private CapaciteMort capaciteMort;


    public Maladie(Environnement environnement, double ligne, double colonne, int pv, double vitesse, int recompense, CapaciteActive capaciteActive, CapaciteMort capaciteMort) {
        super(environnement, ligne+0.5, colonne+0.5);

        this.vitesse = vitesse;
        this.pv = pv;
        this.recompense = recompense;
        coefVitesse = 1;
        this.gestionnaireDeplacement = new GestionnaireDeplacement();
        initCapacite(capaciteActive, capaciteMort);
    }

    public void initCapacite(CapaciteActive capaciteActive, CapaciteMort capaciteMort) {
        this.capaciteActive = capaciteActive;
        this.capaciteMort = capaciteMort;
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
            activationCapaciteActive();
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

    public void activationCapaciteActive(){
        if (capaciteActive != null)
            capaciteActive.agir(this);
    }

    public void activationCapaciteMort(){
        if (!aAtteintLObjectif() && !estVivant() && capaciteMort != null) {
            this.capaciteMort.activer(this);
        }
    }
}
