package fr.iut.virusdefense.controller.observateurs;

import fr.iut.virusdefense.modele.positionnables.cellules.attaques.Projectile;
import fr.iut.virusdefense.vue.sprites.SpriteProjectile;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObsListeProjectiles implements ListChangeListener<Projectile> {
    private final Pane paneDessin;

    public ObsListeProjectiles(Pane paneDessin){
        this.paneDessin = paneDessin;
    }

    @Override
    public void onChanged(Change<? extends Projectile> c) {
        while (c.next()){
            for (Projectile p : c.getAddedSubList())
                paneDessin.getChildren().add(new SpriteProjectile(p));
            for (Projectile p : c.getRemoved())
                paneDessin.getChildren().remove(paneDessin.lookup("#" + p.getId()));
        }
    }
}
