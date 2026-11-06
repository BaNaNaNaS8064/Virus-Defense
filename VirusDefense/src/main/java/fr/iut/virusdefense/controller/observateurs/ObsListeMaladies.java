package fr.iut.virusdefense.controller.observateurs;

import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.vue.sprites.SpriteMaladie;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

/**
 * Observe la liste des maladies et ajoute et supprime les {@code SpriteMaladie}
 * en conséquence
 */
public class ObsListeMaladies implements ListChangeListener<Maladie> {

    /// le pane dans lequel on ajoute et supprime des sprites
    private final Pane paneDessin;

    /**
     * Créé un nouvel Observateur qui gèrera les {@code SpriteMaladie} dans paneDessin
     * @param paneDessin le pane dans lequel se trouveront les {@code SpriteMaladie}
     */
    public ObsListeMaladies(Pane paneDessin){
        this.paneDessin = paneDessin;
    }

    @Override
    public void onChanged(Change<? extends Maladie> c) {
        while (c.next()){
            for (Maladie m : c.getAddedSubList())
                paneDessin.getChildren().add(new SpriteMaladie(m));
            for (Maladie m : c.getRemoved())
                paneDessin.getChildren().remove(paneDessin.lookup("#" + m.getId()));
        }
    }
}
