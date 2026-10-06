package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class MuleTyple extends Cellule{

    private static int coutBase = 600;

    public static int getCoutBase() {
        return coutBase;
    }

    private MuleTyple(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 50, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getEnvironnement(), getCoordonees(), 3.0 , 3));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonSimple(getEnvironnement(), getCoordonees(), 15, getReconnaissance().getCibles()));
    }

    public static MuleTyple creer(Environnement env, int ligne, int colonne){
        MuleTyple temp = new MuleTyple(env, ligne, colonne);
        temp.initRec();
        temp.initGestionnaireAttaque();
        return temp;
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
