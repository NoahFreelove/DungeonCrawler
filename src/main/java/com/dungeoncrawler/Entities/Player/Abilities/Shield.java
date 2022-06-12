package com.dungeoncrawler.Entities.Player.Abilities;

import com.JEngine.Components.DontDestroyOnLoad_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.ImageProcessingAndEffects.GameLight;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Main;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class Shield extends Sprite {
    Light.Point projectileLight;
    Lighting lightSource;
    private GameLight gl;
    private int hits = 1;
    public Shield(int hits) {
        super(Transform.simpleTransform(new Vector3(0,0,0)), new GameImage("bin/shieldItem.png"), new Identity("shield"));
        if(PlayerController.instance !=null)
        {
            if(PlayerController.instance.shieldInstance !=null)
            {
                PlayerController.instance.removeShield();
            }
        }
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
        this.hits = hits;
        gl = new GameLight(lightSource,true);
        SceneManager.getActiveScene().addLight(gl);

        addComponent(new DontDestroyOnLoad_Comp());
    }

    public void hit(){
        hits--;
        PlayerController.instance.playHitEffect();
        if(hits>=1)
            return;
        destroy();
        PlayerController.instance.removeShield();

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
        setPosition(PlayerController.instance.getPosition().add(8));
        double xOffset = 0;
        double yOffset = 0;
        if(Main.lightingOffset){
            yOffset = PlayerController.instance.getPosition().y/256*128;
            xOffset = PlayerController.instance.getPosition().x/256*128;
        }
        projectileLight.setX(PlayerController.instance.getPosition().x+xOffset+32);
        projectileLight.setY(PlayerController.instance.getPosition().y+yOffset+32);

    }
}
