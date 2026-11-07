package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueRayonSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSimple;

public class MuleTyple extends Cellule {

    private static int coutBase = 600;

    public static int getCoutBase() {
        return coutBase;
    }

    public MuleTyple(int ligne, int colonne){
        super(ligne, colonne,
                LecteurStatsCellules.getStatistique("MULETYPLE").frequence(),
                LecteurStatsCellules.getStatistique("MULETYPLE").couts());

        initRec();
        initGestionnaireAttaque();
        this.ameliorations.add(() -> {
            getAttaque().setDegats(getAttaque().getDegats() + 5);
        });
        this.ameliorations.add(() -> {
            getReconnaissance().setPortee(getReconnaissance().getPortee() + 1.0);
        });
    }

    @Override
    public void initRec(){
        setReconnaissance(new ReconnaissanceSimple(getCoordonees(),  LecteurStatsCellules.getStatistique("MULETYPLE").portee() , 3));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueRayonSimple(getCoordonees(), LecteurStatsCellules.getStatistique("MULETYPLE").degats() , getReconnaissance().getCibles()));
    }

    @Override
    public String getNom() {
        return "Mule-typle";
    }

}
