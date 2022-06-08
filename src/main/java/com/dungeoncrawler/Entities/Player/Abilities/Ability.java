package com.dungeoncrawler.Entities.Player.Abilities;

import com.JEngine.Core.GameObject;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.ImageProcessingEffects.GameLight;
import com.JEngine.Utility.Misc.GameTimer;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class Ability extends GameObject {
    protected Light.Distant abilityLight;
    protected Lighting lightSource;
    protected GameLight gl;
    protected GameScene sceneStartedIn;

    public Ability(Vector3 pos, double duration, Color lightColor) {
        super(Transform.simpleTransform(pos), new Identity("ability"));
        GameTimer effectTimer = new GameTimer((long) duration * 1000, args -> EndEffect(), true);
        effectTimer.start();

        abilityLight = new Light.Distant();
        abilityLight.setColor(lightColor);

        lightSource = new Lighting();
        lightSource.setLight(abilityLight);
        lightSource.setDiffuseConstant(3);
        lightSource.setSpecularConstant(5);
        lightSource.setSurfaceScale(5);
        gl = new GameLight(lightSource, false);
        sceneStartedIn = SceneManager.getActiveScene();
        sceneStartedIn.addLight(gl);

        StartEffect();
    }

    protected void StartEffect(){

    }
    protected void EndEffect(){
        sceneStartedIn.removeLight(gl);
    }
}
