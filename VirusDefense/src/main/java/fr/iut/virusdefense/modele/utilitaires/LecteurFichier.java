package fr.iut.virusdefense.modele.utilitaires;
import fr.iut.virusdefense.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class LecteurFichier {
    private File fichier;
    private Scanner scanner;

    public LecteurFichier(String cheminRelatif) {
        try {
            fichier = new File(Main.class.getResource(cheminRelatif).toURI());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        lire();
    }
    protected abstract void lireContenu();

    public void ouvrirScanner(){
        try {
            scanner = new Scanner(fichier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Scanner getScanner() { return scanner; }

    public void lire(){
        ouvrirScanner();
        lireContenu();
        scanner.close();
    }
}
