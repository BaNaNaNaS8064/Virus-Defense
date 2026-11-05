package fr.iut.virusdefense.modele.apparition;

import fr.iut.virusdefense.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Le Niveau se charge de ce qui touche aux vagues et aux apparitions <br>
 * Ce n'est pas son rôle de stocker les points d'apparition (rôle de {@code Carte})
 * ni de faire apparaître les maladies (rôle de {@code PointApparition})
 */
public class Niveau {

    /// L'indice de la vague actuelle
    private final IntegerProperty numVagueProperty;
    /// La liste des vagues
    private List<Vague> vagues;

    /// Le délai entre chaque vague
    private int delaiEntreVagues;

    /// le nombre restant de tours avant la prochain vague
    int delai;

    private static Niveau uniqueInstance = null;

    private Niveau(String idNiveau){
        delaiEntreVagues = 600;
        delai = Integer.MAX_VALUE;
        numVagueProperty = new SimpleIntegerProperty(-1);
        initVagues(idNiveau);
    }

    public static Niveau getUniqueInstance(){
        return uniqueInstance;
    }

    public static void creerUniqueInstance(String idNiveau){
        uniqueInstance = new Niveau(idNiveau);
    }

    public final int getNumVague(){
        return numVagueProperty.getValue();
    }

    public final IntegerProperty numVagueProperty(){
        return numVagueProperty;
    }

    public final void setNumVague(int numVague){
        numVagueProperty.setValue(numVague);
    }

    /**
     * Créé toutes les vagues du niveau {@code idNiveau} <br>
     * Chaque vague aura autant de listes d'apparition
     * que le nombre de points d'apparition dans la carte de {@code environnnement}
     */
    private void initVagues(String idNiveau){
        vagues = Arrays.asList(new LecteurVagues(Environnement.getUniqueInstance().getCarte().getPointsApparitions().size(), idNiveau).getVagues());
    }

    /**
     * Démmare la prochaine vague,
     * c'est à dire met à jour les listes d'apparitions des points d'apparition
     * de la carte de {@code environnement}
     */
    public void passerProchaineVague(){
        numVagueProperty.setValue(getNumVague() + 1);
        for (int i = 0; i<Environnement.getUniqueInstance().getCarte().getPointsApparitions().size(); i++)
            Environnement.getUniqueInstance().getCarte().getPointsApparitions().get(i).ajouter(vagues.get(getNumVague()).getListeApparitions().get(i));
         delai = Integer.MAX_VALUE;
    }

    /**
     * Retourne vrai s'il reste des vagues, faux sinon
     * @return vrai s'il reste des vagues, faux sinon
     */
    public boolean resteVague(){
        return getNumVague() < vagues.size() - 1;
    }

    public void update(){
        if (!estTermine()) {
            if (--delai <= 0)
                passerProchaineVague();

            if (getNumVague() >= 0 && vagues.get(getNumVague()).estTerminee() && delai > delaiEntreVagues)
                delai = delaiEntreVagues;
        }
    }

    /**
     * Retourne vrai si le niveau est terminé, faux sinon <br>
     * Le niveau est terminé si il ne reste plus de vague
     * et que la dernière vague est terminée
     * @return Retourne vrai si le niveau est terminé, faux sinon
     */
    public boolean estTermine(){
        return !resteVague() && vagues.get(vagues.size() - 1).estTerminee();
    }

    /**
     * Retourne le nombre de vagues du niveau
     * @return le nombre de vagues du niveau
     */
    public int nombreDeVagues(){
        return vagues.size();
    }
}
