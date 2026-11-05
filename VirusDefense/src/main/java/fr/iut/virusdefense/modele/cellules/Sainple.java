package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueProjectile;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class Sainple extends Cellule {

    private static int coutBase = 50;

    public static int getCoutBase() {
        return coutBase;
    }

    public Sainple(int ligne, int colonne){
        super(ligne, colonne, 60, coutBase);
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getCoordonees(), 3.0, 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueProjectile(getCoordonees(), 40, getReconnaissance().getCibles()));
    }

    @Override
    public String getNom() {
        return "Sainple";
    }

    @Override
    public int coutNiveau2() {
        return 75;
    }

    @Override
    public int coutNiveau3() {
        return 150;
    }

    @Override
    public void ameliorerAuNiveau2(){
        getAttaque().setDegats(getAttaque().getDegats() + 30);
    }

    @Override
    public void ameliorerAuNiveau3(){
        getReconnaissance().setPortee(getReconnaissance().getPortee()+1.5);
    }
}
