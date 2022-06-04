package com.dungeoncrawler.Entities.Weapons.Projectile;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.SimpleDirection;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Entities.Weapons.Weapon;

public class BBGun extends Weapon {
    public BBGun(Vector3 pos) {
        super(1, 0.1f, pos, new GameImage("bin/bbgun.png", 64,64),0.8);
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

        Projectile projectile = new Projectile(getPosition(), rot, dir, getDamage(), 20f, new GameImage("bin/bbpellet.png", 16,16));
        SceneManager.getActiveScene().add(projectile);

    }

    @Override
    public void Update(){
        super.Update();
        setPosition(PlayerController.instance.getPosition().add(new Vector3(32,0,0)));
    }
}
