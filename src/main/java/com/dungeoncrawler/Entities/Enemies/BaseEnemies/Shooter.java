package com.dungeoncrawler.Entities.Enemies.BaseEnemies;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GameTimer;
import com.dungeoncrawler.Entities.Enemies.Enemy;
import com.dungeoncrawler.Entities.Enemies.EnemyProjectile;
import com.dungeoncrawler.Entities.Player.PlayerController;

import static com.dungeoncrawler.Entities.EnemyStats.*;

public class Shooter extends Enemy {
    GameTimer shootTimer = new GameTimer((long) (SHOOTER_ATTACK_DELAY*1000), args -> shoot(), false);
    private boolean inRoom;
    public Shooter(Vector3 initPos) {
        super(initPos, new GameImage(SHOOTER_IMAGE_PATH),
                (int)(SHOOTER_DAMAGE/2), SHOOTER_MAX_HEALTH, SHOOTER_ATTACK_DELAY, SHOOTER_DIFFICULTY);
    }

    @Override
    public void activate(GameScene room){
        if(getHealth()<=0)
        {
            shootTimer.stop();
            return;
        }
        inRoom = true;
        super.activate(room);
    }

    @Override
    public void Update(){
        if(!shootTimer.isRunning())
        {
            shootTimer.start();
        }
        super.Update();
        if(canMove)
            setRotation(new Vector3((float) facePlayer(),0,0));
    }

    private void shoot(){
        if(PlayerController.instance == null)
            return;
        if(!inRoom)
            return;
        if(!canAttack())
            return;
        SceneManager.getActiveScene().add(new EnemyProjectile(getPosition(), Math.toDegrees(playerPositionToRadians()), SHOOTER_DAMAGE, SHOOTER_PROJECTILE_SPEED, new GameImage(SHOOTER_PROJECTILE_IMAGE_PATH)));
    }

    @Override
    public void OnUnload(){
        shootTimer.stop();
        inRoom = false;
    }

    @Override
    public void OnDestroy(){
        shootTimer.stop();
    }
}
