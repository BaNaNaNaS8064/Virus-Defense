module fr.iut.virusdefense {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires jdk.xml.dom;
    requires java.desktop;

    opens fr.iut.virusdefense to javafx.fxml;

    exports fr.iut.virusdefense;
    exports fr.iut.virusdefense.controller;
    exports fr.iut.virusdefense.controller.observateurs;
    exports fr.iut.virusdefense.modele;
    exports fr.iut.virusdefense.modele.apparition;
    exports fr.iut.virusdefense.modele.carte;
    exports fr.iut.virusdefense.modele.positionnables.cellules;
    exports fr.iut.virusdefense.modele.positionnables.cellules.alteration;
    exports fr.iut.virusdefense.modele.positionnables.cellules.attaques;
    exports fr.iut.virusdefense.modele.positionnables.cellules.gestionnaireAttaque;
    exports fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance;
    exports fr.iut.virusdefense.modele.deplacement;
    exports fr.iut.virusdefense.modele.positionnables.maladies;
    exports fr.iut.virusdefense.modele.positionnables.maladies.capacites;
    exports fr.iut.virusdefense.modele.utilitaires;
    exports fr.iut.virusdefense.vue;
    exports fr.iut.virusdefense.vue.sprites;
    exports fr.iut.virusdefense.modele.positionnables.cellules.sousclasses;
    exports fr.iut.virusdefense.modele.positionnables.maladies.sousclasses;
    exports fr.iut.virusdefense.modele.enums;
    exports fr.iut.virusdefense.modele.acteurs;
    exports fr.iut.virusdefense.modele.positionnables;
}