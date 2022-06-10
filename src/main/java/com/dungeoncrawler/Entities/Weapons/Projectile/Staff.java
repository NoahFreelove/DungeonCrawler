package com.dungeoncrawler.Entities.Weapons.Projectile;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.SimpleDirection;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.GameMath;
import com.dungeoncrawler.Entities.Player.PlayerController;

import static com.dungeoncrawler.Entities.Weapons.WeaponStats.*;

public class Staff extends ProjectileWeapon{

    boolean charging = false;
    private float charge = STAFF_MIN_PROJECTILE_SIZE;

    public Staff(Vector3 pos) {
        super(pos, STAFF_OFFSET, STAFF_DAMAGE, STAFF_SHOOT_DELAY, new GameImage(STAFF_IMAGE_PATH), STAFF_REWARD_MULTIPLIER, STAFF_PROJECTILE_SPEED, new GameImage(STAFF_PROJECTILE_IMAGE_PATH));
    }

    @Override
    public void attack(SimpleDirection direction)
    {
        charging = true;
    }

    @Override
    public void Update() {
        super.Update();
        if(charging)
        {
            charge+=0.023;
            charge = GameMath.clamp(STAFF_MIN_PROJECTILE_SIZE,STAFF_MAX_PROJECTILE_SIZE, charge);
            System.out.println(charge);
        }
    }

    @Override
    public void releaseAttackButton() {
        if(charging)
            charging = false;
        else return;

        Vector2 dir = new Vector2(0,0);
        Vector3 rot = new Vector3(0,0,0);
        float xOffset = 0;
        float yOffset = 0;
        switch (PlayerController.instance.getDirectionFacing())
        {
            case UP -> {
                dir = new Vector2(0,-1);
                xOffset = 16*charge;
            }
            case DOWN -> {
                dir = new Vector2(0,1);
                rot = new Vector3(180,0,0);
                xOffset = 16*charge;
            }
            case LEFT -> {
                dir = new Vector2(-1, 0);
                rot = new Vector3(270,0,0);
                yOffset = 16*charge;
            }
            case RIGHT -> {
                dir = new Vector2(1, 0);
                rot = new Vector3(90,0,0);
                yOffset = 16*charge;
            }
        }

        Projectile projectile = new Projectile(new Vector3(getPosition().x-xOffset, getPosition().y-yOffset), rot, dir, STAFF_DAMAGE*charge, STAFF_PROJECTILE_SPEED, new GameImage(STAFF_PROJECTILE_IMAGE_PATH), true);
        projectile.setScale(new Vector3(charge,charge,charge));
        SceneManager.getActiveScene().add(projectile);
        charge = STAFF_MIN_PROJECTILE_SIZE;
    }
}
