package com.dungeoncrawler.Entities.Player.Abilities;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.dungeoncrawler.Entities.Weapons.Projectile.Projectile;

import static com.dungeoncrawler.Entities.Player.Abilities.AbilityStats.FIRE_DAMAGE;
import static com.dungeoncrawler.Entities.Player.Abilities.AbilityStats.FIRE_MOVE_SPEED;

public class FireballEffect extends Projectile {
    public FireballEffect(float yPos) {
        super(new Vector3(1280,yPos), new Vector3(270,0,0), new Vector2(-1,0), FIRE_DAMAGE, FIRE_MOVE_SPEED, new GameImage("bin/images/fireball.png", 96,96), true);
        setEnableLighting(false);
    }
}
