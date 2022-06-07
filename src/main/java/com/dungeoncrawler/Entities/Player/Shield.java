package com.dungeoncrawler.Entities.Player;

import com.JEngine.Components.DontDestroyOnLoad_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.ImageProcessingEffects.GameLight;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class Shield extends Sprite {
    Light.Point projectileLight;
    Lighting lightSource;
    private GameLight gl;
    public Shield() {
        super(Transform.simpleTransform(new Vector3(0,0,0)), new GameImage("bin/shield.png"), new Identity("shield"));
        projectileLight = new Light.Point();
        projectileLight.setColor(Color.CYAN);

        projectileLight.setX(getPosition().x);
        projectileLight.setY(getPosition().y);
        projectileLight.setZ(50);
        lightSource = new Lighting();

        lightSource.setLight(projectileLight);
        // make light dim
        lightSource.setDiffuseConstant(3);
        lightSource.setSpecularConstant(0.5);
        lightSource.setSurfaceScale(0.5);
        gl = new GameLight(lightSource,true);
        SceneManager.getActiveScene().addLight(gl);
        addComponent(new DontDestroyOnLoad_Comp());
    }

    public void destroy(){
        SceneManager.getActiveScene().removeLight(gl);
        lightSource = new Lighting(null);
        setActive(false);
    }

    @Override
    public void Update(){
        if(PlayerController.instance == null)
            return;
        super.Update();
        setPosition(PlayerController.instance.getPosition().add(-16));
        /*double xOffset = PlayerController.instance.getPosition().x/256*128;
        double yOffset = PlayerController.instance.getPosition().y/256*128;*/
        projectileLight.setX(PlayerController.instance.getPosition().x+0+32);
        projectileLight.setY(PlayerController.instance.getPosition().y+0+32);
    }
}
