package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueProjectileExplosif;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecBrulure;
import fr.iut.virusdefense.modele.utilitaires.LecteurStatistiques;

public class Brulhure extends Cellule{
    private static int coutBase = 1300;
    private final int tempsZone;

    public static int getCoutBase() {
        return coutBase;
    }

    public Brulhure(int ligne, int colonne) {
        super(ligne, colonne,
                LecteurStatistiques.getStatistique("BRULHURE").getFrequence(),
                LecteurStatistiques.getStatistique("BRULHURE").getCout());
        tempsZone=200;
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec() {
        setReconnaissance(new RecBrulure(getCoordonees(), 5, 1), LecteurStatistiques.getStatistique("BRULHURE").getPortee(), 1));
    }

    @Override
    public void initGestionnaireAttaque() {
        setGestionnaireAttaque(new GestionnaireAttaqueProjectileExplosif(getCoordonees(),25,getReconnaissance().getCibles(),1.5,400,0.5 ,tempsZone), LecteurStatistiques.getStatistique("BRULHURE").getDegats(), getReconnaissance().getCibles(),1.5,400,0.5 ,tempsZone));
    }

    @Override
    public String getNom() {
        return "Brul-hure";
    }

    @Override
    public int coutNiveau2() {
        return 1600;
    }

    @Override
    public int coutNiveau3() {
        return 2000;
    }

    @Override
    public void ameliorerAuNiveau2() {
        ((GestionnaireAttaqueProjectileExplosif)getAttaque()).setTempsZone(((GestionnaireAttaqueProjectileExplosif) getAttaque()).getTempsZone() + 100);
    }

    @Override
    public void ameliorerAuNiveau3() {
        setFrequenceAttaque(getFrequenceAttaque()-150);
    }
}
