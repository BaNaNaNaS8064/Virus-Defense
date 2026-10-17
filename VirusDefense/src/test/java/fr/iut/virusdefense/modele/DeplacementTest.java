package fr.iut.virusdefense.modele;

import fr.iut.virusdefense.modele.apparition.PointApparition;
import fr.iut.virusdefense.modele.carte.Carte;
import fr.iut.virusdefense.modele.deplacement.Deplacement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeplacementTest {
    boolean[][] carteStatique;
    ArrayList<PointApparition> pointApp = new ArrayList<>();
    Carte carte;
    Deplacement dep;

    public void miseEnSituation(boolean[][] carteStatique, List<Integer> objectif, List<Integer> pointApparition){
        pointApp.clear();
        pointApp.add(new PointApparition(null, pointApparition.get(0), pointApparition.get(1)));
        carte = new Carte(carteStatique, objectif, pointApp);
        dep = new Deplacement(carte);
    }

    @Test
    void prochaineCase() {
        carteStatique = new boolean[][]{ //possède un grand mur avec un passage qui sépare le point d'apparition et l'objectif avec un chemin au milieu
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, false, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false}
        };
        miseEnSituation(carteStatique, List.of(2,4), List.of(2,0));
        assertArrayEquals(dep.prochaineCase(List.of(2,1)).toArray(), List.of(2,2).toArray() ,"Il suit le chemin optimal");
        assertNull(dep.prochaineCase(List.of(2,4)),"il a atteint l'objectif");

        carteStatique = new boolean[][]{ //possède un grand mur avec un passage autre part qui sépare le point d'apparition et l'objectif
                {false, false, true, false, false},
                {false, false, false, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false}
        };
        miseEnSituation(carteStatique, List.of(2,4), List.of(2,0));
        assertNotSame(dep.prochaineCase(List.of(2,1)).toArray(), List.of(2,2).toArray() ,"Il y un mur sur le chemin optimal");

        carteStatique = new boolean[][]{ //possède un grand mur qui sépare le point d'apparition et l'objectif
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false}
        };
        miseEnSituation(carteStatique, List.of(2,4), List.of(2,0));
        assertNull(dep.prochaineCase(List.of(2,1)), "Un mur sépare entièrement le chemin vers l'objectif");
        miseEnSituation(carteStatique, List.of(2,8), List.of(2,0));
        assertNull(dep.prochaineCase(List.of(2,1)), "l'objectif est hors de la carte");
        assertNull(dep.prochaineCase(List.of(6,1)), "En dehors de la carte");
        assertNull(dep.prochaineCase(List.of(2,2)), "A partir d'un mur");

        carteStatique = new boolean[][]{ //possède un grand mur qui sépare le point d'apparition et l'objectif
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false}
        };
        miseEnSituation(carteStatique, List.of(2,2), List.of(2,0));
        assertArrayEquals(dep.prochaineCase(List.of(2,1)).toArray(), List.of(2,2).toArray() ,"l'objectif est dans un mur");
    }

    @Test
    void estBloquee() {
        carteStatique = new boolean[][]{ //possède un grand mur qui sépare le point d'apparition et l'objectif
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false}
        };
        miseEnSituation(carteStatique, List.of(2,4), List.of(2,0));
        assertTrue(dep.estBloquee(List.of(2,0)), "Un mur sépare entièrement le chemin vers l'objectif depuis le spawn");
        assertTrue(dep.estBloquee(List.of(2,2)), "Un mur sépare entièrement le chemin vers l'objectif depuis le mur");
        assertFalse(dep.estBloquee(List.of(2,3)), "Aucune séparation");

        carteStatique = new boolean[][]{
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false},
                {false, false, false, false, false}
        };
        miseEnSituation(carteStatique, List.of(4,4), List.of(0,0));
        assertTrue(dep.estBloquee(List.of(-1,0)), "Hors carte (haut) donc pas d'accès");
        assertTrue(dep.estBloquee(List.of(5,0)), "hors carte (bas) donc pas d'accès");
        assertTrue(dep.estBloquee(List.of(0,-1)), "hors carte (gauche) donc pas d'accès");
        assertTrue(dep.estBloquee(List.of(0,5)), "hors carte (droite) donc pas d'accès");
        assertFalse(dep.estBloquee(List.of(0,0)), "Aucun bloquage");
        miseEnSituation(carteStatique, List.of(5,5), List.of(0,0));
        assertTrue(dep.estBloquee(List.of(2,2)), "Objectif hors carte");

        //possède un petit mur qui bloque l'accès le plus directe entre le point d'apparition et l'objectif mais laisse un chemin disponible
        carteStatique = new boolean[][]{
                {false, false, false, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, true, false, false},
                {false, false, false, false, false}
        };

        miseEnSituation(carteStatique, List.of(2,4), List.of(2,0));
        assertFalse(dep.estBloquee(List.of(2,0)), "petit mur entre la case et l'objectif avec des chemins disponibles mais pas le plus optimal.");
    }
}