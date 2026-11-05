package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueZone;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class Brouaileuse extends Cellule {
    private static int coutBase = 300;

    public static int getCoutBase() {
        return coutBase;
    }

    public Brouaileuse(int ligne, int colonne){
        super(ligne, colonne, 200, coutBase);
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getCoordonees(), 1.5, 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueZone(getCoordonees(), 90, getReconnaissance().getCibles(), getReconnaissance().getPortee()));
    }

    @Override
    public String getNom() {
        return "Brouaïlleuse";
    }

    @Override
    public int coutNiveau2() {
        return 350;
    }

    @Override
    public int coutNiveau3() {
        return 400;
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
