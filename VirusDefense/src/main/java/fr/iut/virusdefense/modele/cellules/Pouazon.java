package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.alteration.Dot;
import fr.iut.virusdefense.modele.cellules.alteration.Ralentissement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueRayon;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;

public class Pouazon extends Cellule{
    private static int coutBase = 850;

    public static int getCoutBase() {
        return coutBase;
    }

    private Pouazon(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne, 50, coutBase);
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getEnvironnement(), getCoordonees(), 3.0, 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        GestionnaireAttaqueRayon temp = new GestionnaireAttaqueRayonSimple(getEnvironnement(), getCoordonees(), 15, getReconnaissance().getCibles());
        temp.ajouterAlteration(new Dot(15,4));
        setGestionnaireAttaque(temp);
    }

    public static Pouazon creer(Environnement env, int ligne, int colonne){
        Pouazon temp = new Pouazon(env, ligne, colonne);
        temp.initRec();
        temp.initGestionnaireAttaque();
        return temp;
    }

    @Override
    public String getNom() {
        return "Pouazon";
    }

    @Override
    public int coutNiveau2() {
        return 950;
    }

    @Override
    public int coutNiveau3() {
        return 1100;
    }

    @Override
    public void ameliorerAuNiveau2() {
        getAttaque().setDegats(getAttaque().getDegats() + 15);
    }

    @Override
    public void ameliorerAuNiveau3() {
        getAttaque().ajouterAlteration(new Ralentissement(5,0.9));
    }
}
