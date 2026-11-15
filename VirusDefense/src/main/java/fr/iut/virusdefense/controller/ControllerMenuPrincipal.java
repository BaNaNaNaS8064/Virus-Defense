package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenuPrincipal implements Initializable {
    @FXML public Pane paneMenuPrincipal;
    @FXML public ChoiceBox<String> menuChoixNiveau;
    @FXML public ChoiceBox<String> menuChoixDifficulte;

    public FXMLLoader fxmlLoaderJeu;
    public Controller controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String[] niveaux = new File(Main.class.getResource("niveaux/").toURI()).list();
            menuChoixNiveau.getItems().addAll(niveaux);
            menuChoixNiveau.getItems().sort(String::compareTo);
            menuChoixNiveau.setValue(menuChoixNiveau.getItems().get(0));

            menuChoixDifficulte.getItems().add("Normal");
            menuChoixDifficulte.getItems().add("Difficile");
            menuChoixDifficulte.setValue(menuChoixDifficulte.getItems().get(0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BorderPane creerPaneJeu() throws IOException{
        fxmlLoaderJeu = new FXMLLoader(Main.class.getResource("vd-view.fxml"));
        BorderPane paneJeu = new BorderPane(fxmlLoaderJeu.load());
        controller = fxmlLoaderJeu.getController();
        return paneJeu;
    }

    public void commencerJeu(){
        if (menuChoixNiveau.getValue() != null && menuChoixDifficulte.getValue() != null){
            Main.changerScene();
            controller.changerNiveauEtJouer(menuChoixNiveau.getValue(), menuChoixDifficulte.getValue());
        }
    }
}
