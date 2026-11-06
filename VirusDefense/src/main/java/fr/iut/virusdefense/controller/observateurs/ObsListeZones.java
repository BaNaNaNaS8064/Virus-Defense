package fr.iut.virusdefense.controller.observateurs;

import fr.iut.virusdefense.modele.positionnables.cellules.attaques.Zone;
import fr.iut.virusdefense.vue.sprites.SpriteZone;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObsListeZones implements ListChangeListener<Zone> {
    private final Pane paneDessin;

    public ObsListeZones(Pane paneDessin){
        this.paneDessin = paneDessin;
    }

    @Override
    public void onChanged(Change<? extends Zone> c) {
        while (c.next()){
            for (Zone z : c.getAddedSubList())
                paneDessin.getChildren().add(new SpriteZone(z));
            for (Zone z : c.getRemoved())
                paneDessin.getChildren().remove(paneDessin.lookup("#" + z.getId()));
        }
    }
}
