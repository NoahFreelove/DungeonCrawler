package com.dungeoncrawler.Entities.Weapons.Projectile;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.SimpleDirection;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Entities.Weapons.Weapon;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;

public class ProjectileWeapon extends Weapon {

    private float projectileSpeed;
    private GameImage projectileImage;
    private Vector2 offset;

    public ProjectileWeapon(Vector3 pos, Vector2 offset, float damage, double shootDelay, GameImage image, double rewardMultiplier, float projectileSpeed, GameImage projectileImage) {
        super(damage, shootDelay, pos, image, rewardMultiplier);
        this.projectileImage = projectileImage;
        this.projectileSpeed = projectileSpeed;
        this.offset = offset;
    }

    @Override
    protected void attack(SimpleDirection direction) {
        Vector2 dir = new Vector2(0,0);
        Vector3 rot = new Vector3(0,0,0);
        switch (direction)
        {
            case UP -> dir = new Vector2(0,-1);
            case DOWN -> {
                dir = new Vector2(0,1);
                rot = new Vector3(180,0,0);
            }
            case LEFT -> {
                dir = new Vector2(-1, 0);
                rot = new Vector3(270,0,0);
            }
            case RIGHT -> {
                dir = new Vector2(1, 0);
                rot = new Vector3(90,0,0);
            }
        }

        Projectile projectile = new Projectile(getPosition(), rot, dir, damage, projectileSpeed, projectileImage);
        SceneManager.getActiveScene().add(projectile);

    }

    @Override
    public void Update(){
        super.Update();
        if(PlayerController.instance == null)
            return;
        setPosition(PlayerController.instance.getPosition().add(offset));
    }
}
