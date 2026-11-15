package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.enums.CodeTuile;
import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;
import fr.iut.virusdefense.modele.positionnables.cellules.alteration.AlterationComposite;
import fr.iut.virusdefense.modele.positionnables.cellules.alteration.DegatsSurDuree;
import fr.iut.virusdefense.modele.positionnables.cellules.alteration.Ralentissement;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayon;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ciblage.CiblageProche;

import java.util.List;

public class Pouazon extends Cellule {
    private static int coutBase = 850;

    public static int getCoutBase() {
        return coutBase;
    }

    public Pouazon( int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatsCellules.getStatistique("POUAZON").frequence(),
                LecteurStatsCellules.getStatistique("POUAZON").couts()

        );
        initRec();
        initGestionnaireAttaque();

        ajouterAmelioration(() -> {
            getAttaque().setDegats(getAttaque().getDegats() + 5);
        });

        ajouterAmelioration(() -> {
            getReconnaissance().setPortee(getReconnaissance().getPortee() + 1.5);
        });
    }

    @Override
    public void initRec(){
        setReconnaissance(new ReconnaissanceSimple(getCoordonees(), LecteurStatsCellules.getStatistique("POUAZON").portee(), 1, new CiblageProche(getCoordonees())));
    }

    @Override
    public void initGestionnaireAttaque(){
        GestionnaireAttaqueRayon temp = new GestionnaireAttaqueRayonSimple(getCoordonees(), LecteurStatsCellules.getStatistique("POUAZON").degats(), getReconnaissance().getCibles());
        temp.setAlteration(new DegatsSurDuree(15,4));
        setGestionnaireAttaque(temp);
    }

    @Override
    public String getNom() {
        return "Pouazon";
    }

    @Override
    public CodeTuile getCodeTuile() {
        return CodeTuile.POUAZON;
    }
}
