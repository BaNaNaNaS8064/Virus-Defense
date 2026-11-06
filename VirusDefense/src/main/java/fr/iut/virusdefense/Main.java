package fr.iut.virusdefense;

import fr.iut.virusdefense.controller.ControllerMenuPrincipal;
import fr.iut.virusdefense.modele.utilitaires.LecteurStatistiques;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static StackPane paneEnsemble;

    @Override
    public void start(Stage stage) throws IOException {
        new LecteurStatistiques("stats/stats_cellules.txt");

        paneEnsemble = new StackPane();
        Scene scene = initScene();
        stage.setResizable(false);
        stage.setTitle("Virus Defense");
        stage.setScene(scene);
        stage.show();
    }

    public Scene initScene() throws IOException{
        FXMLLoader fxmlLoaderMenuPrincipal = new FXMLLoader(Main.class.getResource("MenuPrincipal.fxml"));
        paneEnsemble.getChildren().add(fxmlLoaderMenuPrincipal.load());
        ControllerMenuPrincipal controllerMenuPrincipal = fxmlLoaderMenuPrincipal.getController();
        paneEnsemble.getChildren().add(controllerMenuPrincipal.creerPaneJeu());
        paneEnsemble.getChildren().get(0).toFront();
        return new Scene(paneEnsemble);
    }

    public static void changerScene(){
        paneEnsemble.getChildren().get(0).toFront();
    }


    public static void main(String[] args) {
        launch();
    }
}
