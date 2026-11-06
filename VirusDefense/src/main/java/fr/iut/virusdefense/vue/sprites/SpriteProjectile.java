package fr.iut.virusdefense.vue.sprites;

import fr.iut.virusdefense.modele.positionnables.cellules.attaques.Projectile;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SpriteProjectile extends Circle implements Sprite{
    private final Projectile p;

    public SpriteProjectile(Projectile p){
        super();
        this.p = p;
        creerSprite();
    }

    @Override
    public void creerSprite(){
        setTranslateX(p.getColonne()*32);
        setTranslateY(p.getLigne()*32);
        setRadius(4);

        translateXProperty().bind(p.colonneProperty().multiply(32));
        translateYProperty().bind(p.ligneProperty().multiply(32));

        setFill(Color.BLACK);
        setId(p.getId());
    }

}