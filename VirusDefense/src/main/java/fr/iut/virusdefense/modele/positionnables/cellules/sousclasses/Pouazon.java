package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.alteration.AlterationComposite;
import fr.iut.virusdefense.modele.positionnables.cellules.alteration.DegatsSurDuree;
import fr.iut.virusdefense.modele.positionnables.cellules.alteration.Ralentissement;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayon;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;

import java.util.List;

public class Pouazon extends Cellule {
    private static int coutBase = 850;

    public static int getCoutBase() {
        return coutBase;
    }

    public Pouazon( int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatsCellules.getStatistique("POUAZON").frequence(),
                LecteurStatsCellules.getStatistique("POUAZON").cout());
            initRec();
            initGestionnaireAttaque();
    }

    @Override
    public void initRec(){
        setReconnaissance(new ReconnaissanceSimple(getCoordonees(), LecteurStatsCellules.getStatistique("POUAZON").portee(), 1));
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
        getAttaque().setAlteration(new AlterationComposite(List.of(new Ralentissement(5,0.9), getAttaque().getAlteration())));
    }
}
