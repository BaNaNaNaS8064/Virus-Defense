package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueProjectileExplosif;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSansMurs;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;

public class Brulhure extends Cellule {
    private static int coutBase = 1300;
    private final int tempsZone;

    public static int getCoutBase() {
        return coutBase;
    }

    public Brulhure(int ligne, int colonne) {
        super(ligne, colonne,
                LecteurStatsCellules.getStatistique("BRULHURE").frequence(),
                LecteurStatsCellules.getStatistique("BRULHURE").cout());
        tempsZone=200;
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec() {
        setReconnaissance(new ReconnaissanceSansMurs(getCoordonees(), LecteurStatsCellules.getStatistique("BRULHURE").portee(), 1));
    }

    @Override
    public void initGestionnaireAttaque() {
        setGestionnaireAttaque(new GestionnaireAttaqueProjectileExplosif(getCoordonees(), LecteurStatsCellules.getStatistique("BRULHURE").degats(),getReconnaissance().getCibles(),1.5,400,0.5 ,tempsZone));
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
