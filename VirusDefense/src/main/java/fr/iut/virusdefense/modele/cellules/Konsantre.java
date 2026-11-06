package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonConcentre;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;
import fr.iut.virusdefense.modele.utilitaires.LecteurStatistiques;

public class Konsantre extends Cellule{
    private static int coutBase = 1000;

    public static int getCoutBase() {
        return coutBase;
    }

    public Konsantre(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatistiques.getStatistique("KONSANTRE").getFrequence(),
                LecteurStatistiques.getStatistique("KONSANTRE").getCout());
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getCoordonees(), 3.0, 1), LecteurStatistiques.getStatistique("KONSANTE").getPortee(), 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonConcentre(getCoordonees(), 1, 60, getReconnaissance().getCibles(), LecteurStatistiques.getStatistique("KONSANTE").getDegats(), 60, getReconnaissance().getCibles()));
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
