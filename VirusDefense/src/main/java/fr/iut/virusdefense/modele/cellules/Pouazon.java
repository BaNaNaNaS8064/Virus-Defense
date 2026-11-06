package fr.iut.virusdefense.modele.cellules;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.alteration.AlterationComposite;
import fr.iut.virusdefense.modele.cellules.alteration.Dot;
import fr.iut.virusdefense.modele.cellules.alteration.Ralentissement;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueRayon;
import fr.iut.virusdefense.modele.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.cellules.reconnaissance.RecSimple;
import fr.iut.virusdefense.modele.utilitaires.LecteurStatistiques;

import java.util.List;

public class Pouazon extends Cellule{
    private static int coutBase = 850;

    public static int getCoutBase() {
        return coutBase;
    }

    public Pouazon( int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatistiques.getStatistique("POUAZON").getFrequence(),
                LecteurStatistiques.getStatistique("POUAZON").getCout());
            initRec();
            initGestionnaireAttaque();
    }

    @Override
    public void initRec(){
        setReconnaissance(new RecSimple(getCoordonees(), LecteurStatistiques.getStatistique("POUAZON").getPortee(), 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        GestionnaireAttaqueRayon temp = new GestionnaireAttaqueRayonSimple(getCoordonees(), LecteurStatistiques.getStatistique("POUAZON").getDegats(), getReconnaissance().getCibles());
        temp.setAlteration(new Dot(15,4));
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
