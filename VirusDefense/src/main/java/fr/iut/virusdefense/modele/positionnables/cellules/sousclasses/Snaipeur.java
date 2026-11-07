package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage.CiblagePlusDePV;

public class Snaipeur extends Cellule {
    private static int coutBase = 500;

    public static int getCoutBase() {
        return coutBase;
    }

    public Snaipeur(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatsCellules.getStatistique("SNAIPEUR").frequence(),
                LecteurStatsCellules.getStatistique("SNAIPEUR").couts());
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec(){
        setReconnaissance(new ReconnaissanceSimple(getCoordonees(), LecteurStatsCellules.getStatistique("SNAIPEUR").portee(), 1, new CiblagePlusDePV()));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonSimple(getCoordonees(), LecteurStatsCellules.getStatistique("SNAIPEUR").degats(), getReconnaissance().getCibles()));
    }

    @Override
    public String getNom() {
        return "Snaï-peur";
    }

    @Override
    public void ameliorerAuNiveau2() {
        setFrequenceAttaque(getFrequenceAttaque()-25);
    }

    @Override
    public void ameliorerAuNiveau3() {
        setFrequenceAttaque(getFrequenceAttaque()-50);
    }
}
