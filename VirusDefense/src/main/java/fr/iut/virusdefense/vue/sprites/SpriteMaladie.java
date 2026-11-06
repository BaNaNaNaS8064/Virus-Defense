package fr.iut.virusdefense.vue.sprites;

import fr.iut.virusdefense.controller.AssociationImage;
import fr.iut.virusdefense.modele.positionnables.maladies.Maladie;
import fr.iut.virusdefense.modele.enums.CodeMaladie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteMaladie extends ImageView implements Sprite{

    private final Maladie m;

    public SpriteMaladie(Maladie m){
        super();
        this.m = m;
        creerSprite();
    }

    @Override
    public void creerSprite(){
        setImage(new Image(AssociationImage.imageDe(CodeMaladie.codeDe(m))));

        setRotate(Math.random() * 359);

        translateXProperty().bind(m.colonneProperty().multiply(32).subtract(8));
        translateYProperty().bind(m.ligneProperty().multiply(32).subtract(8));
        setId(m.getId());
    }

}
