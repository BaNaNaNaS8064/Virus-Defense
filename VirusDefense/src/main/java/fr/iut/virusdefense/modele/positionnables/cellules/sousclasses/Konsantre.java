package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonConcentre;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSimple;

public class Konsantre extends Cellule {
    private static int coutBase = 1000;

    public static int getCoutBase() {
        return coutBase;
    }

    public Konsantre(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatsCellules.getStatistique("KONSANTRE").frequence(),
                LecteurStatsCellules.getStatistique("KONSANTRE").cout());
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec(){
        setReconnaissance(new ReconnaissanceSimple(getCoordonees(), LecteurStatsCellules.getStatistique("KONSANTRE").portee(), 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonConcentre(getCoordonees(),  LecteurStatsCellules.getStatistique("KONSANTRE").degats(), 60, getReconnaissance().getCibles()));
    }

    @Override
    public String getNom() {
        return "Konsantré";
    }

    @Override
    public int coutNiveau2() {
        return 1300;
    }

    @Override
    public int coutNiveau3() {
        return 1500;
    }

    @Override
    public void ameliorerAuNiveau2() {
        getAttaque().setDegats(getAttaque().getDegats()+0.5);
    }

    @Override
    public void ameliorerAuNiveau3() {
        ((GestionnaireAttaqueRayonConcentre)getAttaque()).setDelaiAugmentation(((GestionnaireAttaqueRayonConcentre)getAttaque()).getDelaiAugmentation()-50);
    }
}
