package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.GameScene;
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
    public void activate(GameScene room){
        shootTimer = new GameTimer(500, args -> shoot());

        super.activate(room);
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

        SceneManager.getActiveScene().add(new EnemyProjectile(getPosition(), Math.toDegrees(playerPositionToRadians()), getDamage(), 8, new GameImage("bin/50bmg.png")));
    }

    @Override
    public void OnUnload(){
        shootTimer.stop();
    }

    @Override
    public void OnDestroy(){
        shootTimer.stop();
    }
}
