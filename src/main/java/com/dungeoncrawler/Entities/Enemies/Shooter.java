package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GameTimer;
import com.dungeoncrawler.Entities.Player.PlayerController;

import static com.dungeoncrawler.Entities.EnemyStats.*;

public class Shooter extends Enemy{
    GameTimer shootTimer = new GameTimer((long) (SHOOTER_ATTACK_DELAY*1000), args -> shoot(), true);
    public Shooter(Vector3 initPos) {
        super(initPos, new GameImage(SHOOTER_IMAGE_PATH),
                SHOOTER_DAMAGE, SHOOTER_MAX_HEALTH, SHOOTER_ATTACK_DELAY, SHOOTER_DIFFICULTY);
    }

    @Override
    public void activate(GameScene room){
        shootTimer = new GameTimer((long) (SHOOTER_ATTACK_DELAY*1000), args -> shoot());

        super.activate(room);
    }

    @Override
    public void Update(){
        if(!shootTimer.isRunning())
        {
            shootTimer = new GameTimer((long) (SHOOTER_ATTACK_DELAY*1000), args -> shoot(), true);
            shootTimer.start();
        }
        super.Update();
        setRotation(new Vector3((float) facePlayer(),0,0));
    }

    private void shoot(){
        if(PlayerController.instance == null)
            return;

        SceneManager.getActiveScene().add(new EnemyProjectile(getPosition(), Math.toDegrees(playerPositionToRadians()), SHOOTER_DAMAGE, SHOOTER_PROJECTILE_SPEED, new GameImage(SHOOTER_PROJECTILE_IMAGE_PATH)));
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
