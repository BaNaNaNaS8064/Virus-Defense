package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonConcentre;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage.CiblagePlusDePV;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage.CiblageProche;

public class Konsantre extends Cellule {
    private static int coutBase = 1000;

    public static int getCoutBase() {
        return coutBase;
    }

    public Konsantre(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatsCellules.getStatistique("KONSANTRE").frequence(),
                LecteurStatsCellules.getStatistique("KONSANTRE").couts()
                );
        initRec();
        initGestionnaireAttaque();

        ajouterAmelioration(() -> {
            getAttaque().setDegats(getAttaque().getDegats() + 1.0);
        });

        ajouterAmelioration(() -> {
            getReconnaissance().setPortee(getReconnaissance().getPortee() + 1.0);
        });
    }

    @Override
    public void initRec(){
        setReconnaissance(new ReconnaissanceSimple(getCoordonees(), LecteurStatsCellules.getStatistique("KONSANTRE").portee(), 1, new CiblageProche(getCoordonees())));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonConcentre(getCoordonees(),  LecteurStatsCellules.getStatistique("KONSANTRE").degats(), 60, getReconnaissance().getCibles()));
    }

    @Override
    public String getNom() {
        return "Konsantré";
    }

}
