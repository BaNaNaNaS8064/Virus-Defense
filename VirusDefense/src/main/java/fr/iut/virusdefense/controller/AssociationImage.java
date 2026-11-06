package fr.iut.virusdefense.controller;

import fr.iut.virusdefense.Main;
import fr.iut.virusdefense.modele.enums.CodeMaladie;
import fr.iut.virusdefense.modele.enums.CodeTuile;

import java.util.HashMap;

/**
 * La classe AssociationImage gère l'association entre les codes de Tuiles et Maladies et les images
 */
public class AssociationImage {

    /**
     * Associe à un code tuile le chemin de son image (à partir de images/tuiles/ dans les ressources)
     */
    public static HashMap<CodeTuile, String> correspondanceTuile = creerMapTuile();

    /**
     * @return une HashMap de correspondance code tuile - image
     */
    private static HashMap<CodeTuile, String> creerMapTuile(){
        HashMap<CodeTuile, String> temp = new HashMap<>();

        temp.put(CodeTuile.VIDE, "vide.png");
        temp.put(CodeTuile.MUR, "mur.png");
        temp.put(CodeTuile.POINTAPPARITION, "pointApparition.png");
        temp.put(CodeTuile.OBJECTIF, "objectif.gif");

        temp.put(CodeTuile.SAINPLE, "cellules/sainple.png");
        temp.put(CodeTuile.LASERE, "cellules/lasere.png");
        temp.put(CodeTuile.BROUAIEUSE, "cellules/brouaieuse.png");
        temp.put(CodeTuile.MULETYPLE, "cellules/muletyple.png");
        temp.put(CodeTuile.SNAIPEUR, "cellules/snaipeur.png");
        temp.put(CodeTuile.RIZCOCHER, "cellules/rizcocher.png");
        temp.put(CodeTuile.KONSANTRE, "cellules/konsantre.png");
        temp.put(CodeTuile.POUAZON, "cellules/pouazon.png");
        temp.put(CodeTuile.BRULHURE, "cellules/brulhure.png");

        return temp;
    }

    /**
     * Fait le lien entre un code de tuile et son image.
     * @param codeTuile le code de la tuile dont on cherche l'image (par exemple MUR)
     * @return le chemin vers l'image qui correspond à CodeTuile
     */
    public static String imageDe(CodeTuile codeTuile){
        return String.valueOf(Main.class.getResource("images/tuiles/" + correspondanceTuile.get(codeTuile)));
    }

    public static HashMap<CodeMaladie, String> correspondanceMaladie = creerMapMaladie();

    private static HashMap<CodeMaladie, String> creerMapMaladie(){
        HashMap<CodeMaladie, String> temp = new HashMap<>();

        temp.put(CodeMaladie.BACTERIEBANALE, "bacterie_banale.gif");
        temp.put(CodeMaladie.PARASITE, "parasite.gif");
        temp.put(CodeMaladie.VIRUS, "virus.gif");
        temp.put(CodeMaladie.VIRUSCOMPOSE_1, "virus_compose_1.gif");
        temp.put(CodeMaladie.VIRUSCOMPOSE_2, "virus_compose_2.gif");
        temp.put(CodeMaladie.VIRUSCOMPOSE_3, "virus_compose_3.gif");
        temp.put(CodeMaladie.PETITCHAMPIGNON, "petit_champignon.gif");
        temp.put(CodeMaladie.GRANDCHAMPIGNON, "grand_champignon.gif");
        temp.put(CodeMaladie.TUMEUR, "tumeur.gif");

        return temp;
    }

    public static String imageDe(CodeMaladie codeMaladie){
        return String.valueOf(Main.class.getResource("images/maladies/" + correspondanceMaladie.get(codeMaladie)));
    }

}
