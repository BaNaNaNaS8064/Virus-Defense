package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.RecSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatistiques;

public class MuleTyple extends Cellule {

    private static int coutBase = 600;

    public static int getCoutBase() {
        return coutBase;
    }

    public MuleTyple(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatistiques.getStatistique("MULETYPLE").getFrequence(),
                LecteurStatistiques.getStatistique("MULETYPLE").getCout());

        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getCoordonees(),  LecteurStatistiques.getStatistique("MULETYPLE").getPortee() , 3));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonSimple(getCoordonees(), LecteurStatistiques.getStatistique("MULETYPLE").getDegats() , getReconnaissance().getCibles()));
    }

    @Override
    public String getNom() {
        return "Mule-typle";
    }

    @Override
    public int coutNiveau2() {
        return 650;
    }

    @Override
    public int coutNiveau3() {
        return 800;
    }

    @Override
    public void ameliorerAuNiveau2() {
        getReconnaissance().setPortee(getReconnaissance().getPortee()+0.5);
    }

    @Override
    public void ameliorerAuNiveau3() {
        getReconnaissance().setNombreCiblesMax(5);
    }
}
