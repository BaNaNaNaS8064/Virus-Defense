package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class Snaipeur extends Cellule{
    private static int coutBase = 500;

    public static int getCoutBase() {
        return coutBase;
    }

    private Snaipeur(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 250, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getEnvironnement(), getCoordonees(), 12.0, 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonSimple(getEnvironnement(), getCoordonees(), 100, getReconnaissance().getCibles()));
    }

    public static Snaipeur creer(Environnement env, int ligne, int colonne){
        Snaipeur temp = new Snaipeur(env, ligne, colonne);
        temp.initRec();
        temp.initGestionnaireAttaque();
        return temp;
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
