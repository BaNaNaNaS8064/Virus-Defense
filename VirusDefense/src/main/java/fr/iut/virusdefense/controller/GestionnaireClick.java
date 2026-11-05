package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.*;
import fr.iut.virusdefense.vue.AfficheurDeCarte;
import fr.iut.virusdefense.vue.GestionnaireMenuClick;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GestionnaireClick {
    private final AfficheurDeCarte afficheurDeCarte;

    private final Pane paneCentre;
    private final ToggleGroup toggleGrpCellules;

    public GestionnaireClick(ToggleGroup toggleGrpCellules, AfficheurDeCarte afficheurDeCarte, Pane paneCentre){
        this.toggleGrpCellules = toggleGrpCellules;
        this.afficheurDeCarte = afficheurDeCarte;
        this.paneCentre = paneCentre;
    }

    public void gererClick(MouseEvent mouseEvent){
        int ligne = (int)(mouseEvent.getY()/32);
        int colonne = (int)(mouseEvent.getX()/32);

        if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && Environnement.getUniqueInstance().getCarte().emplacementVide(ligne, colonne))
            poser(ligne, colonne);

        else if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && Environnement.getUniqueInstance().getCarte().estCellule(ligne, colonne))
            creerMenu(ligne,colonne);

        afficheurDeCarte.rechargerEmplacement(ligne, colonne);
    }

    public void poser(int ligne, int colonne) {
        String boutonSelectionne = ((RadioButton) toggleGrpCellules.getSelectedToggle()).getId();

        Cellule c = switch (boutonSelectionne) {
            case "RbSainple" -> Sainple.creer(ligne, colonne);
            case "RbLasere" -> Lasere.creer(ligne, colonne);
            case "RbBrouaieuse" -> Brouaileuse.creer(ligne, colonne);
            case "RbMuleTyple" -> MuleTyple.creer(ligne, colonne);
            case "RbSnaipeur" -> Snaipeur.creer(ligne, colonne);
            case "RbRizCoCher" -> RizCocher.creer(ligne, colonne);
            case "RbKonsantre" -> Konsantre.creer(ligne, colonne);
            case "RbPouazon" -> Pouazon.creer(ligne, colonne);
            case "RbBrulHure" -> Brulhure.creer(ligne,colonne);
            default -> null;
        };

        Environnement.getUniqueInstance().ajouterSiConforme(c);
    }

    public void creerMenu(int ligne, int colonne){
        for (Cellule c : Environnement.getUniqueInstance().getCarte().getCellules()){
            if ((int) c.getLigne() == ligne && (int) c.getColonne() == colonne) {
                new GestionnaireMenuClick(ligne, colonne, paneCentre, afficheurDeCarte);
            }
        }
    }
}
