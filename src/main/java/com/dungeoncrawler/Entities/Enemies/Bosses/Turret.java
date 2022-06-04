package com.dungeoncrawler.Entities.Enemies.Bosses;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GameTimer;
import com.dungeoncrawler.Entities.Enemies.EnemyProjectile;
import com.dungeoncrawler.Entities.Player.PlayerController;

public class Turret extends Boss{
    private GameTimer shootDelay = new GameTimer(2000, null, false);
    private GameTimer shootTick = new GameTimer(50, args -> shoot());
    private boolean isAlive;
    public Turret(Vector3 initPos, int x, int y) {
        super(initPos, new GameImage("bin/turret.png")
                , 2, 35, 0.5, 5, "pew pew", 1f, x,y);
        isAlive = true;
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
        setRotation(new Vector3((float) facePlayer(),0,0));
    }

    @Override
    protected void onDeath(){
        shootDelay.stop();
        shootTick.stop();
        isAlive = false;
        super.onDeath();
    }

    private void shoot(){
        if(PlayerController.instance == null)
            return;
        if(!isAlive)
            return;

        SceneManager.getActiveScene().add(new EnemyProjectile(new Vector3(getPosition().x + 48, getPosition().y + 48, getPosition().z), Math.toDegrees(playerPositionToRadians()), 3, 5, new GameImage("bin/bbpellet.png")));
    }

    @Override
    public void OnUnload(){
        isAlive = false;
        shootDelay.stop();
        shootTick.stop();
    }

}
