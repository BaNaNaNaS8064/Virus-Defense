package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueProjectileExplosif;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSansMurs;

public class Brulhure extends Cellule {
    private static int coutBase = 1300;
    private final int tempsZone;

    public static int getCoutBase() {
        return coutBase;
    }

    public Brulhure(int ligne, int colonne) {
        super(ligne, colonne,
                LecteurStatsCellules.getStatistique("BRULHURE").frequence(),
                LecteurStatsCellules.getStatistique("BRULHURE").couts()
                );
        tempsZone=200;
        initRec();
        initGestionnaireAttaque();

        ajouterAmelioration(() -> {
            getAttaque().setDegats(getAttaque().getDegats() + 100);
        });

        ajouterAmelioration(() -> {
            getReconnaissance().setPortee(getReconnaissance().getPortee() + 2.0);
        });
    }

    @Override
    public void initRec() {
        setReconnaissance(new ReconnaissanceSansMurs(getCoordonees(), LecteurStatsCellules.getStatistique("BRULHURE").portee(), 1));
    }

    @Override
    public void initGestionnaireAttaque() {
        setGestionnaireAttaque(new GestionnaireAttaqueProjectileExplosif(getCoordonees(), LecteurStatsCellules.getStatistique("BRULHURE").degats(),getReconnaissance().getCibles(),1.5,400,0.5 ,tempsZone));
    }

    @Override
    public String getNom() {
        return "Brul-hure";
    }

}
