package fr.iut.virusdefense.vue;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.controller.ControllerMenuClick;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class GestionnaireMenuClick {
    private static GestionnaireMenuClick menuActif;
    private int ligne;
    private int colonne;

    private Pane paneCentre;
    private Pane menu;

    private AfficheurDeCarte afficheurDeCarte;

    public GestionnaireMenuClick(int ligne, int colonne, Pane paneCentre, AfficheurDeCarte afficheurDeCarte){
        fermerMenuActif();
        menuActif = this;
        
        this.colonne = colonne;
        this.ligne = ligne;
        this.paneCentre = paneCentre;
        this.afficheurDeCarte = afficheurDeCarte;
        creerMenu();
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public AfficheurDeCarte getAfficheurDeCarte() {
        return afficheurDeCarte;
    }

    public void creerMenu(){
          try {
              FXMLLoader loader = new FXMLLoader(Main.class.getResource("paneMenuClick.fxml"));
              menu = loader.load();

              ControllerMenuClick controllerMenuClick = loader.getController();
              controllerMenuClick.setGestionnaireMenu(this);
              positionnerMenu();
              paneCentre.getChildren().add(menu);
          }
          catch (Exception e){
              throw new RuntimeException(e);
          }
    }

    public void retirerMenu(){
        paneCentre.getChildren().remove(menu);
    }

    public void positionnerMenu(){
        menu.setTranslateX((colonne + 0.5) * 32);

        if (menu.getTranslateX() + menu.getPrefWidth() > paneCentre.getWidth())
            menu.setTranslateX(menu.getTranslateX() - 160);

        menu.setTranslateY((ligne + 0.5) * 32);

        if (menu.getTranslateY() + menu.getPrefHeight() > paneCentre.getHeight())
            menu.setTranslateY(menu.getTranslateY() - 128);
    }
    public static void fermerMenuActif(){
        if (menuActif != null)
            menuActif.retirerMenu();
    }
}
