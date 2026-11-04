package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueProjectile;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;
import fr.iut.virusdefense.modele.utilitaires.LecteurStatistiques;

public class Sainple extends Cellule {

    private static int coutBase = 50;

    public static int getCoutBase() {
        return coutBase;
    }

    private Sainple(Environnement env, int ligne, int colonne){
        super(env, ligne, colonne,
                LecteurStatistiques.getStatistique("SAINPLE").getFrequence(),
                LecteurStatistiques.getStatistique("SAINPLE").getCout()
        );
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getEnvironnement(), getCoordonees(), LecteurStatistiques.getStatistique("SAINPLE").getPortee(), 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueProjectile(getEnvironnement(), getCoordonees(), LecteurStatistiques.getStatistique("SAINPLE").getDegats(), getReconnaissance().getCibles()));
    }

    public static Sainple creer(Environnement env, int ligne, int colonne){
        Sainple temp = new Sainple(env, ligne, colonne);
        temp.initRec();
        temp.initGestionnaireAttaque();
        return temp;
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
