package fr.iut.virusdefense.modele.utilitaires;
import fr.iut.virusdefense.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class LecteurFichier {
    protected File fichier;
    protected Scanner scanner;

    public LecteurFichier(String cheminRelatif) {
        try {
            fichier = new File(Main.class.getResource(cheminRelatif).toURI());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    protected abstract void lire() throws FileNotFoundException;
}
