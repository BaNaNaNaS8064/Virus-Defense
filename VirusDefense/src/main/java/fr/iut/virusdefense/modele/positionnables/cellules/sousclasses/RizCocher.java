package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonRicochet;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.RecRicochet;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.RecSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.Reconnaissance;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatistiques;

public class RizCocher extends Cellule {
    private static int coutBase = 900;

    public static int getCoutBase() {
        return coutBase;
    }

    public RizCocher(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatistiques.getStatistique("RIZCOCHER").getFrequence(),
                LecteurStatistiques.getStatistique("RIZCOCHER").getCout());
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec() {
        Reconnaissance base = new RecSimple(getCoordonees(), LecteurStatistiques.getStatistique("RIZCOCHER").getPortee(),1);
        setReconnaissance(new RecRicochet(base,3));
    }

    @Override
    public void initGestionnaireAttaque() {
        setGestionnaireAttaque(new GestionnaireAttaqueRayonRicochet(getCoordonees(), LecteurStatistiques.getStatistique("RIZCOCHER").getDegats(), getReconnaissance().getCibles()));
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
