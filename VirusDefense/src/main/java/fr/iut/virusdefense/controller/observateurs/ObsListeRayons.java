package fr.iut.virusdefense.controller.observateurs;

import fr.iut.virusdefense.modele.positionnables.cellules.attaques.Rayon;
import fr.iut.virusdefense.vue.sprites.SpriteRayon;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

/**
 * Observe la liste des rayons et ajoute et supprime les {@code SpriteRayon}
 * en conséquence
 */
public class ObsListeRayons implements ListChangeListener<Rayon> {

    /// le pane dans lequel on ajoute et supprime des sprites
    private final Pane paneDessin;

    /**
     * Créé un nouvel Observateur qui gèrera les {@code SpriteRayon} dans paneDessin
     * @param paneDessin le pane dans lequel se trouveront les {@code SpriteRayon}
     */
    public ObsListeRayons(Pane paneDessin){
        this.paneDessin = paneDessin;
    }

    @Override
    public void onChanged(Change<? extends Rayon> c) {
        while (c.next()){
            for (Rayon r : c.getAddedSubList())
                paneDessin.getChildren().add(new SpriteRayon(r));
            for (Rayon r : c.getRemoved())
                paneDessin.getChildren().remove(paneDessin.lookup("#" + r.getId()));
        }
    }
}

