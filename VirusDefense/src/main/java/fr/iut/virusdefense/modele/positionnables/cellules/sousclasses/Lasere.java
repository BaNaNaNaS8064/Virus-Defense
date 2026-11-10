package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.enums.CodeTuile;
import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage.CiblageProche;

public class Lasere extends Cellule {
    private static int coutBase = 200;

    public static int getCoutBase() {
        return coutBase;
    }

    public Lasere(int ligne, int colonne){
        super(ligne, colonne
                , LecteurStatsCellules.getStatistique("LASERE").frequence()
                , LecteurStatsCellules.getStatistique("LASERE").couts()
                );
        initRec();
        initGestionnaireAttaque();

        ajouterAmelioration(() -> {
            getAttaque().setDegats(getAttaque().getDegats() + 30);
        });

        ajouterAmelioration(() -> {
            getReconnaissance().setPortee(getReconnaissance().getPortee() + 1.5);
        });
    }

    @Override
    public void initRec(){
        setReconnaissance(new ReconnaissanceSimple(getCoordonees(), LecteurStatsCellules.getStatistique("LASERE").portee(), 1, new CiblageProche(getCoordonees())));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonSimple(getCoordonees(), LecteurStatsCellules.getStatistique("LASERE").degats(), getReconnaissance().getCibles()));
    }

    @Override
    public String getNom() {
        return "L'asère";
    }


    @Override
    public CodeTuile getCodeTuile() {
        return CodeTuile.LASERE;
    }
}
