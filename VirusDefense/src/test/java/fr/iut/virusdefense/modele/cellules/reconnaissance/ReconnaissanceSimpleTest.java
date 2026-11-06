package fr.iut.virusdefense.modele.cellules.reconnaissance;

import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.ReconnaissanceSimple;
import fr.iut.virusdefense.modele.positionnables.cellules.reconnaissance.Reconnaissance;
import fr.iut.virusdefense.modele.utilitaires.Coordonees;
import fr.iut.virusdefense.modele.Environnement;
import fr.iut.virusdefense.modele.positionnables.maladies.sousclasses.Virus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReconnaissanceSimpleTest {
    /* Voici la carte utilise pour essayer la Reconnaissance les 1 represente un mur.
    1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
    1 0 1 0 0 0 0 0 1 0 0 0 0 1 0 0 0 1 0 1 1 1 0 0 0 0 0 0 0 1
    0 0 0 0 0 1 1 0 0 0 1 0 0 1 0 0 0 1 0 0 0 0 0 0 1 1 0 0 0 1
    1 1 0 1 0 0 1 1 0 0 0 1 0 0 0 0 0 1 0 1 0 0 1 0 0 1 1 0 0 1
    1 0 0 1 1 0 1 0 0 1 0 1 0 0 0 1 0 0 0 1 1 0 0 0 0 0 0 0 0 1
    1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 1 1 0 1 0 0 1
    1 0 0 1 1 0 0 0 1 0 0 0 1 0 0 0 0 0 0 0 0 0 1 0 0 0 0 1 0 1
    1 0 0 0 0 0 0 1 0 0 1 0 1 0 0 1 0 0 1 0 1 0 0 0 0 0 0 0 0 0
    1 1 0 1 0 1 0 0 0 0 0 0 1 0 1 0 0 0 0 0 0 0 0 0 1 0 0 1 0 1
    1 0 0 0 0 0 0 1 0 1 0 0 0 0 1 0 0 1 1 0 0 0 1 1 0 0 1 0 0 1
    1 0 0 1 0 0 1 0 0 0 1 0 0 0 0 0 0 0 0 0 1 0 0 0 0 1 0 0 0 1
    1 0 1 0 1 0 0 0 0 0 0 0 1 0 1 0 0 0 0 0 0 0 1 0 0 1 0 1 0 1
    1 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 1 0 1 0 1 0 0 1 0 1 0 0 0 1
    1 1 0 0 0 0 0 0 0 1 0 0 0 1 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 1
    1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
     */

    @BeforeAll
    static void init(){
        Environnement.creerInstance("Niveau 1");
    }

    @Test
    void aPortee(){
        Reconnaissance recSimple = new ReconnaissanceSimple(new Coordonees(2,2), 3,1);
        assertTrue(recSimple.aPortee(new Virus(2,3)),"La cible est a portée");
        assertFalse(recSimple.aPortee(new Virus(2,9)), "La maladie est hors de portée");

        assertTrue(recSimple.aPortee(new Virus(1,2)), "La maladie est dans un Mur mais a quand la portée");
    }

    @Test
    void estValide(){
        Reconnaissance recSimple = new ReconnaissanceSimple(new Coordonees(2,2),3,1);
        assertTrue(recSimple.estValide(new Virus(2,3)),"La Cellule voit la maladie");
        assertFalse(recSimple.estValide(new Virus(10,10)),"Maladie hors de portée");

        Virus virusMort = new Virus(2,3);
        virusMort.mourir();
        assertFalse(recSimple.estValide(virusMort), "La maladie meurt");

    }

    @Test
    void aAuMoinsUneCible(){
        Reconnaissance recSimple = new ReconnaissanceSimple(new Coordonees(2,2),3,1);
        Environnement.getUniqueInstance().ajouterMaladie(new Virus(2,3));
        recSimple.actualiser();
        assertTrue(recSimple.aAuMoinsUneCible(), "Il y a exactement une cible valide");

        Environnement.getUniqueInstance().getGestionnaireActeur().getMaladies().clear();
        recSimple.actualiser();
        assertFalse(recSimple.aAuMoinsUneCible(),"Il y a pas de cibles");

        Environnement.getUniqueInstance().ajouterMaladie(new Virus(2,3));
        Environnement.getUniqueInstance().ajouterMaladie(new Virus(2,1));
        recSimple.actualiser();
        assertTrue(recSimple.aAuMoinsUneCible(),"Plusieurs cibles valides");

        recSimple = new ReconnaissanceSimple(new Coordonees(-1,1),3,1);
        recSimple.actualiser();
        assertFalse(recSimple.aAuMoinsUneCible(),"La reconnaissance est en dehors de la carte");
    }

    @Test
    void aAssezDeCibles(){
        Reconnaissance recSimple = new ReconnaissanceSimple(new Coordonees(2,2),3,1);
        Environnement.getUniqueInstance().ajouterMaladie(new Virus(2,3));
        recSimple.actualiser();
        assertTrue(recSimple.aAssezDeCibles(), "Il y a exactement une cible valide");

        Environnement.getUniqueInstance().getGestionnaireActeur().getMaladies().clear();
        recSimple.actualiser();
        assertFalse(recSimple.aAssezDeCibles(),"Il y a pas de cibles");

        Environnement.getUniqueInstance().ajouterMaladie(new Virus(2,3));
        Environnement.getUniqueInstance().ajouterMaladie(new Virus(2,1));
        recSimple.actualiser();
        assertTrue(recSimple.aAssezDeCibles(),"Plusieurs cibles valides");


        recSimple = new ReconnaissanceSimple(new Coordonees(-1,1),3,1);
        recSimple.actualiser();
        assertFalse(recSimple.aAssezDeCibles(),"La reconnaissance est en dehors de la carte");
    }


    @Test
    void valide(){
        Reconnaissance recSimple = new ReconnaissanceSimple(new Coordonees(2,2),3,1);
        Environnement.getUniqueInstance().ajouterMaladie(new Virus(2,3));
        recSimple.actualiser();
        assertTrue(recSimple.valide(), "Il y a exactement une cible valide");

        Environnement.getUniqueInstance().getGestionnaireActeur().getMaladies().clear();
        recSimple.actualiser();
        assertFalse(recSimple.valide(),"Il y a pas de cibles");

        Environnement.getUniqueInstance().ajouterMaladie(new Virus(2,3));
        Environnement.getUniqueInstance().ajouterMaladie(new Virus(2,1));
        recSimple.actualiser();
        assertTrue(recSimple.valide(),"Plusieurs cibles valides");
        
        recSimple = new ReconnaissanceSimple(new Coordonees(-1,1),3,1);
        recSimple.actualiser();
        assertFalse(recSimple.valide(),"La reconnaissance est en dehors de la carte");
    }


}