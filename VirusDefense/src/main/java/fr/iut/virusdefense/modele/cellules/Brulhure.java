package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueProjectileExplosif;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecBrulure;

public class Brulhure extends Cellule{
    private static int coutBase = 1300;
    private final int tempsZone;

    public static int getCoutBase() {
        return coutBase;
    }

    public Brulhure(Environnement environnement, int ligne, int colonne) {
        super(environnement, ligne, colonne, 720, coutBase);
        tempsZone=200;
    }

    @Override
    public void initRec() {
        setReconnaissance(new RecBrulure(getEnvironnement(), getCoordonees(), 5, 1));
    }

    @Override
    public void initGestionnaireAttaque() {
        setGestionnaireAttaque(new GestionnaireAttaqueProjectileExplosif(getEnvironnement(),getCoordonees(),25,getReconnaissance().getCibles(),1.5,400,0.5 ,tempsZone));
    }

    public static Brulhure creer(Environnement env, int ligne, int colonne){
        Brulhure temp = new Brulhure(env,ligne,colonne) ;
        temp.initRec();
        temp.initGestionnaireAttaque();
        return temp;
    }

    @Override
    public String getNom() {
        return "Brul-hure";
    }

    @Override
    public int coutNiveau2() {
        return 1600;
    }

    @Override
    public int coutNiveau3() {
        return 2000;
    }

    @Override
    public void ameliorerAuNiveau2() {
        ((GestionnaireAttaqueProjectileExplosif)getAttaque()).setTempsZone(((GestionnaireAttaqueProjectileExplosif) getAttaque()).getTempsZone() + 100);
    }

    @Override
    public void ameliorerAuNiveau3() {
        setFrequenceAttaque(getFrequenceAttaque()-150);
    }
}
