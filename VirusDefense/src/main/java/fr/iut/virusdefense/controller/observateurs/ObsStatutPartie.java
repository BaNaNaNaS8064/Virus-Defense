package fr.iut.virusdefense.controller.observateurs;

import fr.iut.virusdefense.modele.enums.StatutPartie;
import fr.iut.virusdefense.vue.GestionnaireEcranDeFin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Observe le statut de la partie et démarre les animations
 * de l'écran de fin en conséquence
 */
public class ObsStatutPartie implements ChangeListener<StatutPartie> {

    /// L'objet qui gere l'ecran de fin
    private final GestionnaireEcranDeFin gestionnaireEcranDeFin;

    /**
     * Créé un nouvel Observateur qui démarrera les animations de gestionnaireEcranDeFin
     * @param gestionnaireEcranDeFin l'objet qui gere l'ecran de fin
     */
    public ObsStatutPartie(GestionnaireEcranDeFin gestionnaireEcranDeFin){
        this.gestionnaireEcranDeFin = gestionnaireEcranDeFin;
    }

    @Override
    public void changed(ObservableValue<? extends StatutPartie> observable, StatutPartie oldValue, StatutPartie newValue) {
        switch (newValue){
            case PASTERMINEE:
                gestionnaireEcranDeFin.retirerFond();
                break;
            case GAGNEE :
                gestionnaireEcranDeFin.demarrerAnimationVictoire();
                break;
            case PERDUE :
                gestionnaireEcranDeFin.demarrerAnimationDefaite();
                break;
        }
    }
}
