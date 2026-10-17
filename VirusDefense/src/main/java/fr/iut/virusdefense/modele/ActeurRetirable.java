package fr.iut.virusdefense.modele;

/**
 * Représente les acteurs automatiquement retirable par l'environnement.
 */
public interface ActeurRetirable extends Acteur {
    boolean doitEtreRetiré();
}
