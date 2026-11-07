package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonRicochet;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.Reconnaissance;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceRicochet;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage.CiblageProche;

public class RizCocher extends Cellule {
    private static int coutBase = 900;

    public static int getCoutBase() {
        return coutBase;
    }

    public RizCocher(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatsCellules.getStatistique("RIZCOCHER").frequence(),
                LecteurStatsCellules.getStatistique("RIZCOCHER").couts()
        );
        initRec();
        initGestionnaireAttaque();
    }

    @Override
    public void initRec() {
        Reconnaissance base = new ReconnaissanceSimple(getCoordonees(), LecteurStatsCellules.getStatistique("RIZCOCHER").portee(),1, new CiblageProche(getCoordonees()));
        setReconnaissance(new ReconnaissanceRicochet(base,3, new CiblageProche(getCoordonees())));
    }

    @Override
    public void initGestionnaireAttaque() {
        setGestionnaireAttaque(new GestionnaireAttaqueRayonRicochet(getCoordonees(), LecteurStatsCellules.getStatistique("RIZCOCHER").degats(), getReconnaissance().getCibles()));
    }

    @Override
    public String getNom() {
        return "Riz Co-cher";
    }

    @Override
    public void ameliorerAuNiveau2() {
        getReconnaissance().setPortee(getReconnaissance().getPortee()+0.5);
    }

    @Override
    public void ameliorerAuNiveau3() {
        ((ReconnaissanceRicochet)getReconnaissance()).setNbRicochets(((ReconnaissanceRicochet)getReconnaissance()).getNbRicochets()+2);
    }
}
