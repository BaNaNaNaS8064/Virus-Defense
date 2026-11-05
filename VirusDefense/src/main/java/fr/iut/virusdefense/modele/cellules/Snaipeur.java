package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class Snaipeur extends Cellule{
    private static int coutBase = 500;

    public static int getCoutBase() {
        return coutBase;
    }

    public Snaipeur(int ligne, int colonne){
        super(ligne, colonne, 250, coutBase);
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getCoordonees(), 12.0, 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonSimple(getCoordonees(), 100, getReconnaissance().getCibles()));
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
