package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.*;
import fr.iut.virusdefense.modele.utilitaires.CodeTuile;
import fr.iut.virusdefense.vue.AfficheurDeCarte;
import fr.iut.virusdefense.vue.GestionnaireMenuClick;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GestionnaireClick {
    private final Environnement environnement;

    private final AfficheurDeCarte afficheurDeCarte;

    private final Pane paneCentre;
    private final ToggleGroup toggleGrpCellules;

    public GestionnaireClick(Environnement environnement, ToggleGroup toggleGrpCellules, AfficheurDeCarte afficheurDeCarte, Pane paneCentre){
        this.environnement = environnement;
        this.toggleGrpCellules = toggleGrpCellules;
        this.afficheurDeCarte = afficheurDeCarte;
        this.paneCentre = paneCentre;
    }

    public void gererClick(MouseEvent mouseEvent){
        int ligne = (int)(mouseEvent.getY()/32);
        int colonne = (int)(mouseEvent.getX()/32);

        if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && environnement.getCarte().emplacementVide(ligne, colonne))
            poser(ligne, colonne);

        else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && environnement.getCarte().estCellule(ligne, colonne))
            creerMenu(ligne,colonne);

        afficheurDeCarte.rechargerEmplacement(ligne, colonne);
    }

    public void poser(int ligne, int colonne) {
        String boutonSelectionne = ((RadioButton) toggleGrpCellules.getSelectedToggle()).getId();
        Cellule c = FabriqueCellule.créer(CodeTuile.valueOf(boutonSelectionne.substring(2).toUpperCase()), environnement, ligne, colonne);
        if (c != null) {environnement.ajouterSiConforme(c);}
    }

    public void creerMenu(int ligne, int colonne){
        for (Cellule c : environnement.getCarte().getCellules()){
            if ((int) c.getLigne() == ligne && (int) c.getColonne() == colonne) {
                new GestionnaireMenuClick(ligne, colonne, paneCentre, environnement, afficheurDeCarte);
            }
        }
    }
}
