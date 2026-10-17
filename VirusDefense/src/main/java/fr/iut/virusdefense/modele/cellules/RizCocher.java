package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonRicochet;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecRicochet;

public class RizCocher extends Cellule{
    private static int coutBase = 900;

    public static int getCoutBase() {
        return coutBase;
    }

    private RizCocher(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 120, coutBase);
    }

    @Override
    public void initRec() {
        setReconnaissance(new RecRicochet(getEnvironnement(), getCoordonees(), 3.0, 3));
    }

    @Override
    public void initGestionnaireAttaque() {
        setGestionnaireAttaque(new GestionnaireAttaqueRayonRicochet(getEnvironnement(), getCoordonees(), 75, getReconnaissance().getCibles()));
    }

    public static RizCocher creer(Environnement env, int ligne, int colonne){
        RizCocher temp = new RizCocher(env, ligne, colonne);
        temp.initRec();
        temp.initGestionnaireAttaque();
        return temp;
    }

    @Override
    public String getNom() {
        return "Riz Co-cher";
    }

    @Override
    public int coutNiveau2() {
        return 950;
    }

    @Override
    public int coutNiveau3() {
        return 1000;
    }

    @Override
    public void ameliorerAuNiveau2() {
        getReconnaissance().setPortee(getReconnaissance().getPortee()+0.5);
    }

    @Override
    public void ameliorerAuNiveau3() {
        ((RecRicochet)getReconnaissance()).setNbRicochets(((RecRicochet)getReconnaissance()).getNbRicochets()+2);
    }
}
