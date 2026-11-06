package fr.iut.virusdefense.modele.maladies;

import fr.iut.virusdefense.modele.ActeurRetirable;
import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.Positionnable;
import fr.iut.virusdefense.modele.deplacement.GestionnaireDeplacement;
import fr.iut.virusdefense.modele.maladies.capacites.CapacitePeriodique;
import fr.iut.virusdefense.modele.maladies.capacites.CapaciteMort;

public abstract class Maladie extends Positionnable implements ActeurRetirable {
    private double pv;
    private final double vitesse;
    private final int recompense;
    private double coefVitesse;
    private GestionnaireDeplacement gestionnaireDeplacement;
    private CapacitePeriodique capacitePeriodique;
    private CapaciteMort capaciteMort;

    public Maladie(double ligne, double colonne, int pv, double vitesse, int recompense){
        super(ligne+0.5, colonne+0.5);

        this.vitesse = vitesse;
        this.pv = pv;
        this.recompense = recompense;
        coefVitesse = 1;
        this.gestionnaireDeplacement = new GestionnaireDeplacement();
    }

    public final int getRecompense(){
        return recompense;
    }

    public void setCapacitePeriodique(CapacitePeriodique capacitePeriodique) {
        this.capacitePeriodique = capacitePeriodique;
    }

    public void setCapaciteMort(CapaciteMort capaciteMort) {
        this.capaciteMort = capaciteMort;
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
        return Environnement.getUniqueInstance().getCarte().getObjectif().equals(position());
    }

    @Override
    public final void agir(){
        if (estVivant()) {
            activationCapaciteActive();
            bouger();

            if (aAtteintLObjectif()) {
                infligerDegatsAuJoueur();
                mourir();
            }
            coefVitesse = 1;
        }
    }

    @Override
    public boolean doitEtreRetiré(){
        return !estVivant();
    }

    public void bouger(){
        getCoordonees().copier(gestionnaireDeplacement.prochainEmplacement(this, vitesse * coefVitesse, new Coordonees(Environnement.getUniqueInstance().getDeplacement().prochaineCase(position()))));
    }

    public abstract boolean peutSubirAlteration();

    public void infligerDegatsAuJoueur(){
        Environnement.getUniqueInstance().getJoueur().retirerPv((int) pv);
    }

    public void ralentir(double coefRalentissement){
        coefVitesse *= coefRalentissement;
    }

    public void activationCapaciteActive(){
        if (capacitePeriodique != null)
            capacitePeriodique.agir(this);
    }

    public void activationCapaciteMort(){
        if (capaciteMort != null && !estVivant() && !aAtteintLObjectif()) {
            this.capaciteMort.activer(this);
        }
    }
}
