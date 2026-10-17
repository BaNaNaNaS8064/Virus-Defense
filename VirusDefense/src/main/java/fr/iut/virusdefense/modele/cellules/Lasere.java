package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class Lasere extends Cellule{
    private static int coutBase = 200;

    public static int getCoutBase() {
        return coutBase;
    }

    private Lasere(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 1, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getEnvironnement(), getCoordonees(), 3.0, 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonSimple(getEnvironnement(), getCoordonees(), 1, getReconnaissance().getCibles()));
    }

    public static Lasere creer(Environnement env, int ligne, int colonne){
        Lasere temp = new Lasere(env, ligne, colonne);
        temp.initRec();
        temp.initGestionnaireAttaque();
        return temp;
    }

    @Override
    public String getNom() {
        return "L'asère";
    }

    @Override
    public int coutNiveau2() {
        return 250;
    }

    @Override
    public int coutNiveau3() {
        return 275;
    }

    @Override
    public void ameliorerAuNiveau2() {
        getAttaque().setDegats(getAttaque().getDegats()+0.5);
    }

    @Override
    public void ameliorerAuNiveau3() {
        getReconnaissance().setPortee(getReconnaissance().getPortee()+1);
    }
}
