package fr.iut.virusdefense.vue.sprites;

import fr.iut.virusdefense.modele.positionnables.cellules.attaques.Zone;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SpriteZone extends Circle implements Sprite{
    private final Zone z;

    public SpriteZone(Zone z){
        super();
        this.z = z;
        creerSprite();
    }

    @Override
    public void creerSprite(){
        setCenterX(z.getColonne()*32);
        setCenterY(z.getLigne()*32);
        setRadius(z.getRayonZone()*32);


        setFill(Color.LIGHTGRAY);
        setOpacity(0.2);
        setId(z.getId());
    }

}
