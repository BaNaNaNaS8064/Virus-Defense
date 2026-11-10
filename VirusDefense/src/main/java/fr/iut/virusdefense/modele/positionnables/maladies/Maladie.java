package fr.iut.virusdefense.modele.positionnables.maladies;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.acteurs.ActeurRetirable;
import fr.iut.virusdefense.modele.deplacement.GestionnaireDeplacement;
import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.positionnables.Positionnable;
import fr.iut.virusdefense.modele.positionnables.maladies.capacites.Capacite;
import fr.iut.virusdefense.modele.positionnables.maladies.capacites.CapacitePeriodique;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;

public abstract class Maladie extends Positionnable implements ActeurRetirable {
    private double pv;
    private final double vitesse;
    private final int recompense;
    private double coefVitesse;
    private GestionnaireDeplacement gestionnaireDeplacement;
    private CapacitePeriodique capacitePeriodique;
    private Capacite capaciteMort;

    public Maladie(double ligne, double colonne, double pv, double vitesse, int recompense){
        super(ligne, colonne);

        this.vitesse = vitesse;
        this.pv = pv;
        this.recompense = recompense;
        coefVitesse = 1;
        this.gestionnaireDeplacement = new GestionnaireDeplacement();
    }

    public final int getRecompense(){
        return recompense;
    }

    public double getPv() {
        return pv;
    }

    public void setPv(double pv) {
        this.pv = pv;
    }

    public double getVitesse() {
        return vitesse;
    }

    public CapacitePeriodique getCapacitePeriodique() {
        return capacitePeriodique;
    }

    public void setCapacitePeriodique(CapacitePeriodique capacitePeriodique) {
        this.capacitePeriodique = capacitePeriodique;
    }

    public Capacite getCapaciteMort() {
        return capaciteMort;
    }

    public void setCapaciteMort(Capacite capaciteMort) {
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
        return Environnement.getInstance().getCarte().getObjectif().equals(position());
    }

    @Override
    public final void agir(){
        if (estVivant()) {
            activerCapacitePeriodique();
            bouger();

            if (aAtteintLObjectif())
                infligerDegatsAuJoueur();

            coefVitesse = 1;
        }
    }

    @Override
    public boolean doitEtreRetiré(){
        return !estVivant() || aAtteintLObjectif();
    }

    public void bouger(){
        getCoordonees().copier(gestionnaireDeplacement.prochainEmplacement(this, vitesse * coefVitesse, new Coordonees(Environnement.getInstance().getDeplacement().prochaineCase(position()))));
    }

    public abstract boolean peutSubirAlteration();

    public void infligerDegatsAuJoueur(){
        Environnement.getInstance().getJoueur().retirerPv((int) pv);
    }

    public void ralentir(double coefRalentissement){
        coefVitesse *= coefRalentissement;
    }

    public void activerCapacitePeriodique(){
        if (capacitePeriodique != null)
            capacitePeriodique.activer(this);
    }

    public void activerCapaciteMort(){
        if (capaciteMort != null && !estVivant()) {
            this.capaciteMort.activer(this);
        }
    }

    public abstract CodeMaladie getCodeMaladie();
}
