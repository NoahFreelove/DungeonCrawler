package com.dungeoncrawler.Entities.Enemies.Bosses;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GameTimer;
import com.dungeoncrawler.Entities.Enemies.EnemyProjectile;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Entities.Weapons.Projectile.BBGun;
import com.dungeoncrawler.Entities.Weapons.WeaponSpawn;

import static com.dungeoncrawler.Entities.EnemyStats.*;

public class Turret extends Boss{
    private GameTimer shootDelay = new GameTimer((long) (TURRET_IDLE_DURATION*1000), null, false);
    private GameTimer shootTick = new GameTimer((long) (TURRET_SHOOT_DELAY*1000), args -> shoot());
    private boolean isAlive;
    public Turret(Vector3 initPos, int x, int y) {
        super(initPos, new GameImage(TURRET_IMAGE_PATH)
                , TURRET_DAMAGE, TURRET_MAX_HEALTH, 0.5, TURRET_DIFFICULTY, "pew pew", 1f, x,y);
        isAlive = true;
    }

    @Override
    public void battleInit(){
        shootDelay = new GameTimer((long) TURRET_IDLE_DURATION*1000, args -> {
            if(!isAlive)
                return;

            if (shootTick.isRunning()){
                shootTick.stop();
                shootDelay.setInterval((long) TURRET_IDLE_DURATION*1000);
            }
            else{
                shootDelay.setInterval((long) TURRET_SHOOT_DURATION*1000);
                shootTick.start();
            }
        });
        shootDelay.start();
    }

    @Override
    public void Update(){
        super.Update();
        if(!canMove)
            return;
        setRotation(new Vector3((float) facePlayer(),0,0));
    }

    @Override
    protected void onDeath(){
        shootDelay.stop();
        shootTick.stop();
        isAlive = false;
        super.onDeath();
        SceneManager.getActiveScene().add(new WeaponSpawn(new Vector3(getPosition().x,getPosition().y,25), new GameImage("bin/bbgun.png",64,64), new BBGun(new Vector3(getPosition().x,getPosition().y,25)), false, 0, SceneManager.getActiveScene(), new Vector2(-50,0)));
    }

    private void shoot(){
        if(PlayerController.instance == null)
            return;
        if(!isAlive)
            return;
        if(!canAttack())
            return;
        SceneManager.getActiveScene().add(new EnemyProjectile(new Vector3(getPosition().x + 48, getPosition().y + 48, getPosition().z), Math.toDegrees(playerPositionToRadians()), TURRET_DAMAGE, TURRET_PROJECTILE_SPEED, new GameImage(TURRET_PROJECTILE_IMAGE_PATH)));
    }

    @Override
    public void OnUnload(){
        isAlive = false;
        shootDelay.stop();
        shootTick.stop();
    }

}
