package com.dungeoncrawler.Entities.Enemies.Bosses;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.ImageProcessing.GenerateSolidTexture;
import com.JEngine.Utility.Misc.GameTimer;
import com.JEngine.Utility.Misc.GenericMethod;
import com.dungeoncrawler.Entities.Enemies.EnemyProjectile;
import com.dungeoncrawler.Entities.Player.PlayerController;

public class Gunner extends Boss{
    private GameTimer shootDelay = new GameTimer(2000, null, false);
    private GameTimer shootTick = new GameTimer(50, args -> shoot());
    public Gunner(Vector3 initPos, int x, int y) {
        super(initPos, new GameImage("bin/gunner.png")
                , 8, 35, 0.5, 5, "pew pew", 1f, x,y);
    }

    @Override
    public void battleInit(){
        shootDelay = new GameTimer(2000, args -> {
            if (shootTick.isRunning()){
                shootTick.stop();
                shootDelay.setInterval(2000);
            }
            else{
                shootDelay.setInterval(5000);
                shootTick.start();
            }
        });
        shootDelay.start();
    }

    @Override
    public void Update(){
        super.Update();
        Vector3 deltaPos = PlayerController.instance.getPosition().subtract(getPosition());
        double deg;
        if(deltaPos.x<0)
        {
            deg = Math.toDegrees(Math.atan(deltaPos.y/deltaPos.x)-(Math.PI/2));
        }
        else
        {
            deg = Math.toDegrees(Math.atan(deltaPos.y/deltaPos.x)+(Math.PI/2));
        }
        setRotation(new Vector3((float) deg,0,0));
    }

    @Override
    protected void onDeath(){
        shootDelay.stop();
        shootTick.stop();
        super.onDeath();
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
        SceneManager.getActiveScene().add(new EnemyProjectile(new Vector3(getPosition().x + 48, getPosition().y + 48, getPosition().z), Math.toDegrees(rad), getDamage(), 5, new GameImage("bin/bbpellet.png")));
    }

    @Override
    public void OnUnload(){
        shootDelay.stop();
        shootTick.stop();
    }

}
