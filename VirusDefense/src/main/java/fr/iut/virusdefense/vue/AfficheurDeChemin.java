package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.apparition.PointApparition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.List;

public class AfficheurDeChemin {

    private Pane paneLignes;

    public AfficheurDeChemin(Pane paneLignes){
        this.paneLignes = paneLignes;
    }

    public void dessinerChemin(){
        paneLignes.getChildren().clear();
        List<Integer> caseActuelle;
        List<Integer> prochaineCase;
        List<Integer> objectif = Environnement.getUniqueInstance().getCarte().getObjectif();
        Line ligne;

        for (PointApparition p : Environnement.getUniqueInstance().getCarte().getPointsApparitions()) {
            caseActuelle = p.position();
            prochaineCase = Environnement.getUniqueInstance().getDeplacement().prochaineCase(caseActuelle);
            while (!caseActuelle.equals(objectif)){
                ligne = new Line((caseActuelle.get(1)+0.5) * 32, (caseActuelle.get(0)+0.5) * 32, (prochaineCase.get(1)+0.5) * 32, (prochaineCase.get(0)+0.5) * 32);
                ligne.setStroke(Color.GREEN);
                paneLignes.getChildren().add(ligne);
                caseActuelle = prochaineCase;
                prochaineCase = Environnement.getUniqueInstance().getDeplacement().prochaineCase(caseActuelle);
            }
        }

    }

}
