package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;
import fr.iut.virusdefense.modele.utilitaires.LecteurStatistiques;

public class Lasere extends Cellule{
    private static int coutBase = 200;

    public static int getCoutBase() {
        return coutBase;
    }

    public Lasere(int ligne, int colonne){
        super(ligne, colonne
                , LecteurStatistiques.getStatistique("LASERE").getFrequence()
                , LecteurStatistiques.getStatistique("LASERE").getCout());
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getCoordonees(), LecteurStatistiques.getStatistique("LASERE").getPortee(), 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonSimple(getCoordonees(), LecteurStatistiques.getStatistique("LASERE").getDegats(), getReconnaissance().getCibles()));
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
