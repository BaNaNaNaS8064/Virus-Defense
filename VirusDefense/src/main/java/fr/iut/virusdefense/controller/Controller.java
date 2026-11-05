package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.controller.observateurs.*;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.apparition.Niveau;
import fr.iut.virusdefense.modele.cellules.*;
import fr.iut.virusdefense.modele.utilitaires.StatutPartie;
import fr.iut.virusdefense.vue.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Timeline gameLoop;
    private boolean pause = false;
    private boolean pauseAvantEncyclopédie = false;

    // centre
    @FXML public Pane paneDessin;
    @FXML public Pane paneLignes;
    @FXML public Pane paneCentre;
    @FXML public Pane paneEncyclopedie;
    @FXML public TilePane tuiles;

    // haut -> vagues
    @FXML public Label labelVagueActuelle;
    @FXML public Label labelVagueMax;

    // haut -> vie
    @FXML public ProgressBar barreDeVie;
    @FXML public Label labelPvActuels;
    @FXML public Label labelPvMax;

    //haut button
    @FXML public Button boutonVague;
    @FXML public ImageView imagePause;
    @FXML public Button boutonEncyclopedie;

    // droite
    @FXML public Label labelSolde;
    @FXML public ToggleGroup toggleGrpCellules;

    @FXML public Label labelCoutSainple;
    @FXML public Label labelCoutLasere;
    @FXML public Label labelCoutBrouaieuse;
    @FXML public Label labelCoutMuleTyple;
    @FXML public Label labelCoutSnaipeur;
    @FXML public Label labelCoutRizCoCher;
    @FXML public Label labelCoutKonsantre;
    @FXML public Label labelCoutPouazon;
    @FXML public Label labelCoutBrulHure;

    // vue
    private AfficheurDeCarte afficheurDeCarte;

    //controllerPackage
    private GestionnaireClick gestionnaireClick;

    private String idNiveau;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    private void démarrerEnv(){
        if (Environnement.getUniqueInstance() != null)
            Environnement.getUniqueInstance().getGestionnaireActeur().toutVider();
        GestionnaireMenuClick.fermerMenuActif();
        pause = false;
        imagePause.setImage(new Image(String.valueOf(Main.class.getResource("images/utilitaires/play.png"))));
        ((ImageView) boutonVague.getGraphic()).setImage(new Image(String.valueOf(Main.class.getResource("images/utilitaires/start.png"))));
        boutonVague.setStyle("-fx-background-color: #79911B");
        boutonVague.setOnMousePressed(this::démarrerVague);

        Environnement.creerInstance(idNiveau);

        afficheurDeCarte = new AfficheurDeCarte(tuiles, new AfficheurDeChemin(paneLignes));
        gestionnaireClick = new GestionnaireClick(toggleGrpCellules, afficheurDeCarte , paneCentre);

        initLabels();
        initObservateurs();

        initGameLoop();
        gameLoop.play();
    }

    private void initObservateurs(){
        Environnement.getUniqueInstance().getGestionnaireActeur().getMaladies().addListener(new ObsListeMaladies(paneDessin));
        Environnement.getUniqueInstance().getGestionnaireActeur().getRayons().addListener(new ObsListeRayons(paneDessin));
        Environnement.getUniqueInstance().getGestionnaireActeur().getZones().addListener(new ObsListeZones(paneDessin));
        Environnement.getUniqueInstance().getGestionnaireActeur().getProjectiles().addListener(new ObsListeProjectiles(paneDessin));
        Environnement.getUniqueInstance().getJoueur().pvProperty().addListener(new ObsVieJoueur(new GestionnaireBarreDeVie(barreDeVie, labelPvActuels, labelPvMax, Environnement.getUniqueInstance().getJoueur().getPv())));
        Environnement.getUniqueInstance().statutPartieProperty().addListener(new ObsStatutPartie(new GestionnaireEcranDeFin(paneDessin)));
    }

    private void initLabels(){
        labelSolde.textProperty().bind(Environnement.getUniqueInstance().getJoueur().pcProperty().asString());
        labelVagueActuelle.textProperty().bind(Niveau.getUniqueInstance().numVagueProperty().add(1).asString());
        labelVagueMax.setText("/" + Niveau.getUniqueInstance().nombreDeVagues());

        labelCoutSainple.setText("" + Sainple.getCoutBase());
        labelCoutLasere.setText("" + Lasere.getCoutBase());
        labelCoutBrouaieuse.setText("" + Brouaileuse.getCoutBase());
        labelCoutMuleTyple.setText("" + MuleTyple.getCoutBase());
        labelCoutSnaipeur.setText("" + Snaipeur.getCoutBase());
        labelCoutRizCoCher.setText("" + RizCocher.getCoutBase());
        labelCoutKonsantre.setText("" + Konsantre.getCoutBase());
        labelCoutPouazon.setText("" + Pouazon.getCoutBase());
        labelCoutBrulHure.setText("" + Brulhure.getCoutBase());
    }

    /**
     * Créé la gameLoop
     */
    private void initGameLoop(){
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(new KeyFrame(Duration.seconds(0.017), e -> uneFrame()));
    }

    /**
     * Méthode exécutée à chaque tour
     */
    private void uneFrame(){
        Environnement.getUniqueInstance().unTour();
    }

    @FXML
    public void clickTuiles(MouseEvent mouseEvent) {
        if (!pause)
            gestionnaireClick.gererClick(mouseEvent);
    }

    @FXML
    public void toggleEncyclopedie() throws IOException {
        if(paneEncyclopedie.isDisable())
            pauseAvantEncyclopédie = pause;
        if((!pause || !pauseAvantEncyclopédie))
            pauseJeu();
        paneEncyclopedie.setDisable(!paneEncyclopedie.isDisable());
        paneEncyclopedie.setVisible(!paneEncyclopedie.isVisible());
        FXMLLoader encyclopédieLoader = new FXMLLoader(Main.class.getResource("encyclopedie.fxml"));
        BorderPane borderPaneEnc = new BorderPane(encyclopédieLoader.load());
        paneEncyclopedie.getChildren().add(borderPaneEnc);
    }

    @FXML
    public void démarrerVague(MouseEvent mouseEvent) {
        if (!pause){
            Niveau.getUniqueInstance().passerProchaineVague();
            ((ImageView) boutonVague.getGraphic()).setImage(new Image(String.valueOf(Main.class.getResource("images/utilitaires/next.png"))));
            boutonVague.setStyle("-fx-background-color: #2F4D1A");
            boutonVague.setOnMousePressed(this::passerVague);
        }
    }

    public void passerVague(MouseEvent mouseEvent){
        if(Niveau.getUniqueInstance().resteVague() && !pause)
            Niveau.getUniqueInstance().passerProchaineVague();
    }

    @FXML
    public void pauseJeu() {
        if (pause) {
            gameLoop.play();
            imagePause.setImage(new Image(String.valueOf(Main.class.getResource("images/utilitaires/play.png"))));
        }else{
            gameLoop.pause();
            imagePause.setImage(new Image(String.valueOf(Main.class.getResource("images/utilitaires/pause.png"))));
        }
        pause = !pause;
    }


    public void changerNiveauEtJouer(String id){
        idNiveau = id;
        démarrerEnv();
    }

    public void retourMenuPrincipal(){
        Main.changerScene();
        Environnement.getUniqueInstance().setStatutPartie(StatutPartie.PASTERMINEE);
        gameLoop.pause();
    }
}