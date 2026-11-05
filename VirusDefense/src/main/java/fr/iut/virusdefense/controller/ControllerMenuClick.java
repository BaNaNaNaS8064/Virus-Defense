package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.cellules.Cellule;
import fr.iut.virusdefense.vue.GestionnaireMenuClick;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenuClick implements Initializable {

    @FXML public Button boutonAmelioration;

    @FXML public Label labelNom;
    @FXML public Label labelDegats;
    @FXML public Label labelPortee;
    @FXML public Label labelFrequence;
    @FXML public Label labelNiveau;
    @FXML public Label labelCout;

    @FXML public HBox hBoxCoutAmelioration;

    private GestionnaireMenuClick gestionnaireMenu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void setGestionnaireMenu(GestionnaireMenuClick gestionnaireMenu){
        this.gestionnaireMenu = gestionnaireMenu;
        Environnement.getUniqueInstance().statutPartieProperty().addListener((observableValue, statutPartie, t1) -> this.gestionnaireMenu.retirerMenu());

        for (Cellule c : Environnement.getUniqueInstance().getCarte().getCellules()) {
            if ((int) c.getLigne() == this.gestionnaireMenu.getLigne() && (int) c.getColonne() == this.gestionnaireMenu.getColonne()) {
                initLabel(c);
                desactiverBoutonAmeliorationSiNiveauMax(c);
            }
        }
    }

    public void initLabel(Cellule cellule){
        labelNom.setText(cellule.getNom()); ;
        labelDegats.setText("" + cellule.getAttaque().getDegats());
        labelPortee.setText("" + cellule.getReconnaissance().getPortee());
        labelFrequence.setText("" + cellule.getFrequenceAttaque());
        labelNiveau.setText("" + cellule.getNiveau());
        labelCout.setText("" + cellule.getCoutAmelioration());
    }

    public void desactiverBoutonAmeliorationSiNiveauMax(Cellule cellule){
        if (!cellule.resteAmeliorations()) {
            boutonAmelioration.setDisable(true);
            boutonAmelioration.setText("MAX");
            hBoxCoutAmelioration.setVisible(false);
        }
    }

    @FXML
    public void clickBoutonQuitter(MouseEvent mouseEvent){
        gestionnaireMenu.retirerMenu();
    }

    @FXML
    public void clickBoutonAmelioration(){
        for (Cellule c : Environnement.getUniqueInstance().getCarte().getCellules()){
            if ((int) c.getLigne() == gestionnaireMenu.getLigne() && (int) c.getColonne() == gestionnaireMenu.getColonne()) {
                c.niveauSuperieur();
            }
        }
        setGestionnaireMenu(gestionnaireMenu);
    }

    @FXML
    public void clickBoutonSupprimer(){
        Environnement.getUniqueInstance().retirerCelluleALEmplacement(gestionnaireMenu.getLigne(), gestionnaireMenu.getColonne(), false);
        gestionnaireMenu.getAfficheurDeCarte().rechargerEmplacement(gestionnaireMenu.getLigne(), gestionnaireMenu.getColonne());
        gestionnaireMenu.retirerMenu();
    }

}
