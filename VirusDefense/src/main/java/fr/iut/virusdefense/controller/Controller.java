package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.controller.observateurs.*;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.apparition.Niveau;
import fr.iut.virusdefense.modele.enums.Difficulte;
import fr.iut.virusdefense.modele.enums.StatutPartie;
import fr.iut.virusdefense.modele.positionnables.cellules.LecteurStatsCellules;
import fr.iut.virusdefense.modele.positionnables.cellules.sousclasses.*;
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
    private String difficulte;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    private void démarrerEnv(){
        if (Environnement.getInstance() != null)
            Environnement.getInstance().getGestionnaireActeur().toutVider();
        GestionnaireMenuClick.fermerMenuActif();
        pause = false;
        imagePause.setImage(new Image(String.valueOf(Main.class.getResource("images/utilitaires/play.png"))));
        ((ImageView) boutonVague.getGraphic()).setImage(new Image(String.valueOf(Main.class.getResource("images/utilitaires/start.png"))));
        boutonVague.setStyle("-fx-background-color: #79911B");
        boutonVague.setOnMousePressed(this::démarrerVague);

        Environnement.creerInstance(idNiveau, Difficulte.valueOf(difficulte.toUpperCase()));

        afficheurDeCarte = new AfficheurDeCarte(tuiles, new AfficheurDeChemin(paneLignes));
        gestionnaireClick = new GestionnaireClick(toggleGrpCellules, afficheurDeCarte , paneCentre);

        initLabels();
        initObservateurs();

        initGameLoop();
        gameLoop.play();
    }

    private void initObservateurs(){
        Environnement.getInstance().getGestionnaireActeur().getMaladies().addListener(new ObsListeMaladies(paneDessin));
        Environnement.getInstance().getGestionnaireActeur().getRayons().addListener(new ObsListeRayons(paneDessin));
        Environnement.getInstance().getGestionnaireActeur().getZones().addListener(new ObsListeZones(paneDessin));
        Environnement.getInstance().getGestionnaireActeur().getProjectiles().addListener(new ObsListeProjectiles(paneDessin));
        Environnement.getInstance().getJoueur().pvProperty().addListener(new ObsVieJoueur(new GestionnaireBarreDeVie(barreDeVie, labelPvActuels, labelPvMax, Environnement.getInstance().getJoueur().getPv())));
        Environnement.getInstance().statutPartieProperty().addListener(new ObsStatutPartie(new GestionnaireEcranDeFin(paneDessin)));
    }

    private void initLabels(){
        labelSolde.textProperty().bind(Environnement.getInstance().getJoueur().pcProperty().asString());
        labelVagueActuelle.textProperty().bind(Niveau.getInstance().numVagueProperty().add(1).asString());
        labelVagueMax.setText("/" + Niveau.getInstance().nombreDeVagues());

        labelCoutSainple.setText("" + LecteurStatsCellules.getStatistique("SAINPLE").couts()[0]);
        labelCoutLasere.setText("" + LecteurStatsCellules.getStatistique("LASERE").couts()[0]);
        labelCoutBrouaieuse.setText("" + LecteurStatsCellules.getStatistique("BROUAIEUSE").couts()[0]);
        labelCoutMuleTyple.setText("" + LecteurStatsCellules.getStatistique("MULETYPLE").couts()[0]);
        labelCoutSnaipeur.setText("" + LecteurStatsCellules.getStatistique("SNAIPEUR").couts()[0]);
        labelCoutRizCoCher.setText("" + LecteurStatsCellules.getStatistique("RIZCOCHER").couts()[0]);
        labelCoutKonsantre.setText("" + LecteurStatsCellules.getStatistique("KONSANTRE").couts()[0]);
        labelCoutPouazon.setText("" + LecteurStatsCellules.getStatistique("POUAZON").couts()[0]);
        labelCoutBrulHure.setText("" + LecteurStatsCellules.getStatistique("BRULHURE").couts()[0]);
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
        Environnement.getInstance().unTour();
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
            Niveau.getInstance().passerProchaineVague();
            ((ImageView) boutonVague.getGraphic()).setImage(new Image(String.valueOf(Main.class.getResource("images/utilitaires/next.png"))));
            boutonVague.setStyle("-fx-background-color: #2F4D1A");
            boutonVague.setOnMousePressed(this::passerVague);
        }
    }

    public void passerVague(MouseEvent mouseEvent){
        if(Niveau.getInstance().resteVague() && !pause)
            Niveau.getInstance().passerProchaineVague();
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


    public void changerNiveauEtJouer(String id, String difficulte){
        idNiveau = id;
        this.difficulte = difficulte;
        démarrerEnv();
    }

    public void retourMenuPrincipal(){
        Main.changerScene();
        Environnement.getInstance().setStatutPartie(StatutPartie.PASTERMINEE);
        gameLoop.pause();
    }
}