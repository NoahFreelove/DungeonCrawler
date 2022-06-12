package com.dungeoncrawler.Entities.Items;

import com.JEngine.Components.Colliders.BoxCollider_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.ImageProcessingAndEffects.GameLight;
import com.dungeoncrawler.Main;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class Gold extends Pawn {
    BoxCollider_Comp collider_comp;
    private final int value;
    private GameLight gl;

    public Gold(Vector3 pos, int value) {
        super(Transform.simpleTransform(pos.x+16,pos.y+16,pos.z), null, new Identity("Gold", "gold"));
        GameImage newSprite = new GameImage("bin/images/gold.png",32,32);
        setSprite(newSprite);

        collider_comp = new BoxCollider_Comp(Vector3.emptyVector(), 32, 32, true, this);
        addCollider(collider_comp);
        this.value = value;

        Light.Point goldLight = new Light.Point();
        goldLight.setColor(Color.YELLOW);
        Lighting lightSource;

        double xOffset = 0;
        double yOffset =0;
        if(Main.lightingOffset)
        {
            yOffset = getPosition().y/256*128;
            xOffset = getPosition().x/256*128;
        }

        goldLight.setX(getPosition().x+16 + xOffset);
        goldLight.setY(getPosition().y+16 + yOffset);
        goldLight.setZ(50);
        lightSource = new Lighting();

        lightSource.setLight(goldLight);
        // make light dim
        lightSource.setDiffuseConstant(3);
        lightSource.setSpecularConstant(0.5);
        lightSource.setSurfaceScale(0.5);
        gl = new GameLight(lightSource,false);
        SceneManager.getActiveScene().addLight(gl);
    }

    @Override
    public void Update(){
        super.Update();
    }

    public void pickup(){
        collider_comp.setActive(false);
        setActive(false);
        SceneManager.getActiveScene().remove(this);
        SceneManager.getActiveScene().removeLight(gl);
    }

    public int getValue() {
        return value;
    }
}
