package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.modele.positionnables.cellules.sousclasses.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEncyclopedie implements Initializable{
    @FXML public ToggleGroup toggleGrpEncyclopedie;

    @FXML public Label labelTitre;

    @FXML public Label labelDesc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < toggleGrpEncyclopedie.getToggles().size(); i++) {
            toggleGrpEncyclopedie.selectedToggleProperty().addListener((observable,  oldValue,  newValue) -> afficher());
        }
        afficher();
    }
    
    public void afficher(){
        String boutonSelectionne = ((RadioButton) toggleGrpEncyclopedie.getSelectedToggle()).getId();
        if(boutonSelectionne.equals("RbEncSainple") ||
                boutonSelectionne.equals("RbEncLasere") ||
                boutonSelectionne.equals("RbEncBrouaieuse") ||
                boutonSelectionne.equals("RbEncMuleTyple") ||
                boutonSelectionne.equals("RbEncSnaipeur") ||
                boutonSelectionne.equals("RbEncRizCoCher") ||
                boutonSelectionne.equals("RbEncKonsantre") ||
                boutonSelectionne.equals("RbEncPouazon") ||
                boutonSelectionne.equals("RbEncBrulHure"))
        {
            afficherCellule(boutonSelectionne);
        }else{
            afficherMaladie(boutonSelectionne);
        }
    }

    private void afficherCellule(String boutonSelectionne){
        String[] niveauInfo = new String[4];
        String[] cout = new String[3];
        String[] degat = new String[3];
        String[] portee = new String[3];
        String[] frequenceFrame = new String[3];
        String special = "";

        switch (boutonSelectionne) {
            case "RbEncSainple":
                labelTitre.setText("Sainple");
                cout[0] = "" + Sainple.getCoutBase();
                degat[0] = "40";
                portee[0] = "3.0";
                frequenceFrame[0] = "60";

                cout[1] = "75";
                degat[1] = "70";
                portee[1] = "3.0";
                frequenceFrame[1] = "60";

                cout[2] = "150";
                degat[2] = "70";
                portee[2] = "4.5";
                frequenceFrame[2] = "60";
                break;
            case "RbEncLasere":
                labelTitre.setText("L'asère");
                cout[0] = "" + Lasere.getCoutBase();
                degat[0] = "1";
                portee[0] = "3.0";
                frequenceFrame[0] = "1";

                cout[1] = "250";
                degat[1] = "1.5";
                portee[1] = "3.0";
                frequenceFrame[1] = "1";

                cout[2] = "275";
                degat[2] = "1.5";
                portee[2] = "4.0";
                frequenceFrame[2] = "1";
                break;
            case "RbEncBrouaieuse":
                labelTitre.setText("Brouaïeuse");
                cout[0] = "" + Brouaieuse.getCoutBase();
                degat[0] = "90";
                portee[0] = "1.5";
                frequenceFrame[0] = "200";

                cout[1] = "350";
                degat[1] = "90";
                portee[1] = "1.5";
                frequenceFrame[1] = "150";

                cout[2] = "400";
                degat[2] = "90";
                portee[2] = "2.5";
                frequenceFrame[2] = "150";
                break;
            case "RbEncMuleTyple":
                labelTitre.setText("Mule-typle");
                cout[0] = "" + MuleTyple.getCoutBase();
                degat[0] = "15";
                portee[0] = "3.0";
                frequenceFrame[0] = "50";

                cout[1] = "650";
                degat[1] = "15";
                portee[1] = "3.5";
                frequenceFrame[1] = "50";

                cout[2] = "800";
                degat[2] = "15";
                portee[2] = "3.5";
                frequenceFrame[2] = "50";

                special = "Special :\n" +
                        "\nNiveau 1 :\n\tTire sur 3 cibles simultanément" +
                        "\nNiveau 2 :\n\tTire sur 3 cibles simultanément" +
                        "\nNiveau 3 :\n\tTire sur 5 cibles simultanément";
                break;
            case "RbEncSnaipeur":
                labelTitre.setText("Snaï-peur");
                cout[0] = "" + Snaipeur.getCoutBase();
                degat[0] = "100";
                portee[0] = "12.0";
                frequenceFrame[0] = "250";

                cout[1] = "550";
                degat[1] = "100";
                portee[1] = "12.0";
                frequenceFrame[1] = "225";

                cout[2] = "600";
                degat[2] = "100";
                portee[2] = "12.0";
                frequenceFrame[2] = "175";
                break;
            case "RbEncRizCoCher":
                labelTitre.setText("Riz co-cher");
                cout[0] = "" + RizCocher.getCoutBase();
                degat[0] = "75";
                portee[0] = "3.0";
                frequenceFrame[0] = "120";

                cout[1] = "950";
                degat[1] = "75";
                portee[1] = "3.5";
                frequenceFrame[1] = "120";

                cout[2] = "1000";
                degat[2] = "75";
                portee[2] = "3.5";
                frequenceFrame[2] = "120";
                special = "Special : \n" +
                        "\nNiveau 1 :\n\t\tNombre de Ricochet : 3\n\t\tDegats réduit par ricochet : 1/3" +
                        "\nNiveau 2 :\n\t\tNombre de Ricochet : 3\n\t\tDegats réduit par ricochet : 1/3"+
                        "\nNiveau 3 :\n\t\tNombre de Ricochet : 5\n\t\tDegats réduit par ricochet : 1/3";
                break;
            case "RbEncKonsantre":
                labelTitre.setText("Konsantre");
                cout[0] = "" + Konsantre.getCoutBase();
                degat[0] = "1";
                portee[0] = "3.0";
                frequenceFrame[0] = "1";

                cout[1] = "1300";
                degat[1] = "1.5";
                portee[1] = "3.0";
                frequenceFrame[1] = "1";

                cout[2] = "1500";
                degat[2] = "1.5";
                portee[2] = "3.0";
                frequenceFrame[2] = "1";

                special = "Special :\n" +
                        "\nNiveau 1 :\n\tDelai d'augmentation de dégats : 60" +
                        "\nNiveau 2 :\n\tDelai d'augmentation de dégats : 60" +
                        "\nNiveau 3 :\n\tDelai d'augmentation de dégats : 10";
                break;
            case "RbEncPouazon":
                labelTitre.setText("Pouazon");
                cout[0] = "" + Pouazon.getCoutBase();
                degat[0] = "15";
                portee[0] = "3.0";
                frequenceFrame[0] = "50";

                cout[1] = "950";
                degat[1] = "30";
                portee[1] = "3.0";
                frequenceFrame[1] = "50";

                cout[2] = "1100";
                degat[2] = "30";
                portee[2] = "3.0";
                frequenceFrame[2] = "50";

                special = "Special :\n" +
                        "\nNiveau 1 :\n\tDegatsSurDuree : 4\n\tDurée (frame) : 15" +
                        "\nNiveau 2 :\n\tDegatsSurDuree : 4\n\tDurée (frame) : 15" +
                        "\nNiveau 3 :\n\tDegatsSurDuree : 4\n\tDurée (frame) : 15\n\t-10% de vitesse par tir\n\tDurée (frame) : 5";
                break;

            case "RbEncBrulHure":
                labelTitre.setText("Brul-hure");
                cout[0] = "" + Brulhure.getCoutBase();
                degat[0] = "400";
                portee[0] = "5.0";
                frequenceFrame[0] = "720";

                cout[1] = "1600";
                degat[1] = "400";
                portee[1] = "5.0";
                frequenceFrame[1] = "720";

                cout[2] = "2000";
                degat[2] = "400";
                portee[2] = "5.0";
                frequenceFrame[2] = "570";

                special = "Special :\n" +
                        "\nNiveau 1 :\n\tZone Persistante :\n\t\tDegats : 25\n\t\tDurée (frame) : 200" +
                        "\nNiveau 2 :\n\tZone Persistante :\n\t\tDegats : 25\n\t\tDurée (frame) : 300" +
                        "\nNiveau 3 :\n\tZone Persistante :\n\t\tDegats : 25\n\t\tDurée (frame) : 300";
                break;
        };

        niveauInfo[0] = """
                \nNiveau 1 :
                \n\tCout :\s""" + cout[0] +  """
                \n\tDegats :\s""" + degat[0] +  """
                \n\tPortee :\s""" + portee[0] +  """
                \n\tFrequence (frame) :\s""" + frequenceFrame[0] +  """
                """;

        niveauInfo[1] = """
                \nNiveau 2 :
                \n\tCout :\s""" + cout[1] +  """
                \n\tDegats :\s""" + degat[1] +  """
                \n\tPortee :\s""" + portee[1] +  """
                \n\tFrequence (frame) :\s""" + frequenceFrame[1] +  """
                """;

        niveauInfo[2] = """
                \nNiveau 3 :
                \n\tCout :\s""" + cout[2] +  """
                \n\tDegats :\s""" + degat[2] +  """
                \n\tPortee :\s""" + portee[2] +  """
                \n\tFrequence (frame) :\s""" + frequenceFrame[2] +  """
                """;

        labelDesc.setText(niveauInfo[0] + "\n" + niveauInfo[1] + "\n" + niveauInfo[2] + "\n\n" + special);
    }

    private void afficherMaladie(String boutonSelectionne){
        String desc = "";
        String pv ="";
        String vitesse ="";
        String pc ="";
        String special ="";
        switch (boutonSelectionne) {
            //maladies
            case "RbEncBactBanale":
                labelTitre.setText("Bacterie Banale");
                pv = "120";
                vitesse = "0.02";
                pc = "10";
                break;
            case "RbEncParasite":
                labelTitre.setText("Parasite");
                pv = "80";
                vitesse = "0.022";
                pc = "10";
                break;
            case "RbEncVirus":
                labelTitre.setText("Virus");
                pv = "500";
                vitesse = "0.015";
                pc = "30";
                break;
            case "RbEncPetitChamp":
                labelTitre.setText("Petit Champignon");
                pv = "75";
                vitesse = "0.02";
                pc = "7";
                break;
            case "RbEncGrosChamp":
                labelTitre.setText("Gros Champignon");
                pv = "500";
                vitesse = "0.015";
                pc = "30";
                special = "Special :\n" +
                        "\tFait apparaître entre 2 et 4 (inclus) petits champignons toutes les 3 à 5 secondes.";
                break;
            case "RbEncVirusComp":
                labelTitre.setText("Virus Compose");
                pv = "~1000 (total)";
                vitesse = "0.013";
                pc = "72 (total)";
                special = "Special :\n" +
                        "\tA chaque mort, il se divise (maximum 2 fois) avec la moitiée des stats de sa forme précédente.\n\tChaque forme se divise par 2 autres virus composés.";
                break;
            case "RbEncTumeur":
                labelTitre.setText("Tumeur");
                pv = "30000";
                vitesse = "0.005";
                pc = "500";
                special = "Special :\n\tBOSS";
                break;
        };

        labelDesc.setText(desc + "\n" + "\nPV : " + pv + "\nVitesse : " + vitesse + "\nPoint de connaissance : " + pc + "\n\n" + special);
    }
}
