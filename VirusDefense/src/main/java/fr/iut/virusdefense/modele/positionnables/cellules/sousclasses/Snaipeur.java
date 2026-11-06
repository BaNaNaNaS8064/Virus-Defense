package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.RecSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatistiques;

public class Snaipeur extends Cellule {
    private static int coutBase = 500;

    public static int getCoutBase() {
        return coutBase;
    }

    public Snaipeur(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatistiques.getStatistique("SNAIPEUR").getFrequence(),
                LecteurStatistiques.getStatistique("SNAIPEUR").getCout());
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getCoordonees(), LecteurStatistiques.getStatistique("SNAIPEUR").getPortee(), 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonSimple(getCoordonees(), LecteurStatistiques.getStatistique("SNAIPEUR").getDegats(), getReconnaissance().getCibles()));
    }

    @Override
    public String getNom() {
        return "Snaï-peur";
    }

    @Override
    public int coutNiveau2() {
        return 550;
    }

    @Override
    public int coutNiveau3() {
        return 600;
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
