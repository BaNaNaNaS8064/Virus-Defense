package fr.iut.virusdefense.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Le joueur gère ses PV (points de vie) et PC (points de connaissances, monnaie du jeu)
 */
public class Joueur {

    /// Les pv (points de vie) du joueur
    private final IntegerProperty pvProperty;
    /// Les pc (points de connaissance, monnaie du jeu) du joueur
    private final IntegerProperty pcProperty;

    public Joueur(){
        pvProperty = new SimpleIntegerProperty(1000);
        pcProperty = new SimpleIntegerProperty(150);
    }

    public final int getPv() {
        return pvProperty.get();
    }

    public final IntegerProperty pvProperty(){
        return pvProperty;
    }

    public final void setPv(int pv){
        pvProperty.setValue(pv);
    }

    public final int getPc(){
        return pcProperty.get();
    }

    public final IntegerProperty pcProperty(){
        return pcProperty;
    }

    public final void setPc(int pc){
        pcProperty.setValue(pc);
    }

    /**
     * Retire {@code montant} pv au joueur
     * <ul>
     * <li>{@code montant} doit être positif</li>
     * <li>Les points de vie du joueur ne peuvent descendre en dessous de 0</li>
     * </ul>
     * @param montant le montant de pv à enlever
     */
    public void retirerPv(int montant){
        if (montant>0)
            setPv(Math.max(0, getPv() - montant));
    }

    /**
     * Ajoute {@code montant} pc au joueur
     * <ul>
     * <li>{@code montant} doit être positif</li>
     * </ul>
     * @param montant le montant de pc à ajouter
     */
    public void ajouterPc(int montant){
        if (montant>0)
            setPc(getPc() + montant);
    }

    /**
     * Retire {@code montant} pc au joueur
     * <ul>
     * <li>{@code montant} doit être positif</li>
     * </ul>
     * @param montant le montant de pc à enlever
     */
    public void retirerPc(int montant){
        if (montant>0)
            setPc(getPc() - montant);
    }
}
