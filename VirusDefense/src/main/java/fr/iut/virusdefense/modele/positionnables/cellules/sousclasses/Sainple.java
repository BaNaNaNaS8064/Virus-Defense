package fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;

import fr.iut.virusdefense.modele.positionnables.cellules.Cellule;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;
import fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque.GestionnaireAttaqueProjectile;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSimple;

public class Sainple extends Cellule {

    private static int coutBase = 50;

    public static int getCoutBase() {
        return coutBase;
    }

    public Sainple(int ligne, int colonne){
        super(ligne, colonne,              LecteurStatsCellules.getStatistique("SAINPLE").frequence(),
                LecteurStatsCellules.getStatistique("SAINPLE").couts()
        );
        initRec();
        initGestionnaireAttaque();
        this.ameliorations.add(() -> {
            getAttaque().setDegats(getAttaque().getDegats() + 30);
        });
        this.ameliorations.add(() -> {
            getReconnaissance().setPortee(getReconnaissance().getPortee() + 1.5);
        });
    }

    @Override
    public void initRec(){
        setReconnaissance(new ReconnaissanceSimple(getCoordonees(), LecteurStatsCellules.getStatistique("SAINPLE").portee(), 1));
    }

    @Override
    public void initGestionnaireAttaque(){
        setGestionnaireAttaque(new GestionnaireAttaqueProjectile(getCoordonees(), LecteurStatsCellules.getStatistique("SAINPLE").degats(), getReconnaissance().getCibles()));
    }

    @Override
    public String getNom() {
        return "Sainple";
    }

}
