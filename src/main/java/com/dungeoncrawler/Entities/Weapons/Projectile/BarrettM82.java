package com.dungeoncrawler.Entities.Weapons.Projectile;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Entities.Weapons.Weapon;
import com.JEngine.Core.Position.SimpleDirection;

import static com.dungeoncrawler.Entities.Weapons.WeaponStats.*;

public class BarrettM82 extends ProjectileWeapon {

    public BarrettM82(Vector3 pos) {
        super(pos,BARRETT_M82_OFFSET, BARRETT_M82_DAMAGE, BARRETT_M82_SHOOT_DELAY, new GameImage(BARRETT_M82_IMAGE_PATH,128,128),
                BARRETT_M82_REWARD_MULTIPLIER, BARRETT_M82_PROJECTILE_SPEED,
                new GameImage(BARRETT_M82_PROJECTILE_IMAGE_PATH));
    }
}
