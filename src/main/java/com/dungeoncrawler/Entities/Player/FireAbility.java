package com.dungeoncrawler.Entities.Player;

import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.ImageProcessingEffects.GameLight;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class FireAbility extends Ability {

    Light.Distant abilityLight;
    Lighting lightSource;
    GameLight gl;
    GameScene sceneStartedIn;
    public FireAbility(){
        super(PlayerController.instance.getPosition(), 3);
        System.out.println("fire");
        abilityLight = new Light.Distant();
        abilityLight.setColor(Color.RED);

        lightSource = new Lighting();

        lightSource.setLight(abilityLight);
        // make light dim
        lightSource.setDiffuseConstant(3);
        lightSource.setSpecularConstant(5);
        lightSource.setSurfaceScale(5);
        gl = new GameLight(lightSource, false);
        sceneStartedIn = SceneManager.getActiveScene();
        sceneStartedIn.addLight(gl);

    }
    @Override
    protected void EndEffect(){
        sceneStartedIn.removeLight(gl);
    }

}
