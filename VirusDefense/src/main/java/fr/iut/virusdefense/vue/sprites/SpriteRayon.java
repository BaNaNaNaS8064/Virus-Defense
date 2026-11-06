package fr.iut.virusdefense.vue.sprites;

import fr.iut.virusdefense.modele.positionnables.cellules.attaques.Rayon;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class SpriteRayon extends Line implements Sprite{

    private final Rayon r;

    public SpriteRayon(Rayon r){
        super();
        this.r = r;
        creerSprite();
    }

    @Override
    public void creerSprite(){
        setStartX(r.getColonne()*32);
        setStartY(r.getLigne()*32);
        setEndX(r.getCibles().get(0).getColonne()*32);
        setEndY(r.getCibles().get(0).getLigne()*32);

        setStroke(Color.WHITE);
        setId(r.getId());
    }

}
