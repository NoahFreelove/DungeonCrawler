package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.ImageProcessing.GenerateSolidTexture;
import com.JEngine.Utility.Misc.GameTimer;
import com.dungeoncrawler.Entities.Player.PlayerController;

public class Shooter extends Enemy{
    GameTimer shootTimer = new GameTimer(500, args -> shoot(), true);
    public Shooter(Vector3 initPos) {
        super(initPos, new GameImage(GenerateSolidTexture.generateImage(64,64,0xFF00FFFF)), 5, 15, 3, 2);
    }

    @Override
    public void activate(){
        shootTimer = new GameTimer(500, args -> shoot());

        //super.activate();
    }

    @Override
    public void Update(){
        if(!shootTimer.isRunning())
        {
            shootTimer = new GameTimer(750, args -> shoot(), true);
            shootTimer.start();
        }
        super.Update();
    }

    private void shoot(){
        if(PlayerController.instance == null)
            return;

        double rad;
        Vector3 deltaPos = PlayerController.instance.getPosition().subtract(getPosition());

        if(deltaPos.x<0)
        {
            rad = Math.atan(deltaPos.y/deltaPos.x)-(Math.PI);
        }
        else
        {
            rad = Math.atan(deltaPos.y/deltaPos.x);
        }
        SceneManager.getActiveScene().add(new EnemyProjectile(getPosition(), Math.toDegrees(rad), getDamage()));
    }

    @Override
    public void OnUnload(){
        shootTimer.stop();
    }
}
