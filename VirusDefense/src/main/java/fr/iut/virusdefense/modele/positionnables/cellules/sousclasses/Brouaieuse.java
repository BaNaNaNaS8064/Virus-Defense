package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueZone;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSimple;

public class Brouaieuse extends Cellule {
    private static int coutBase = 300;

    public static int getCoutBase() {
        return coutBase;
    }

    public Brouaieuse(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatsCellules.getStatistique("BROUAIEUSE").frequence(),
                LecteurStatsCellules.getStatistique("BROUAIEUSE").couts()
        );
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec(){
        setReconnaissance(new ReconnaissanceSimple(getCoordonees(), LecteurStatsCellules.getStatistique("BROUAIEUSE").portee(), 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueZone(getCoordonees(), LecteurStatsCellules.getStatistique("BROUAIEUSE").degats(), getReconnaissance().getCibles(), getReconnaissance().getPortee()));
    }

    @Override
    public String getNom() {
        return "Brouaïlleuse";
    }

    @Override
    public void ameliorerAuNiveau2() {
        setFrequenceAttaque(getFrequenceAttaque()-50);
    }

    @Override
    public void ameliorerAuNiveau3() {
        getReconnaissance().setPortee(getReconnaissance().getPortee()+1);
    }
}
