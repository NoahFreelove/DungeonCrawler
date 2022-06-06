package com.dungeoncrawler.Entities.Weapons.Projectile;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.SimpleDirection;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Entities.Weapons.Weapon;

import static com.dungeoncrawler.Entities.Weapons.WeaponStats.*;

public class BBGun extends ProjectileWeapon {
    public BBGun(Vector3 pos) {
        super(pos, BBGUN_OFFSET, BBGUN_DAMAGE, BBGUN_SHOOT_DELAY, new GameImage(BBGUN_IMAGE_PATH,64,64),
                BBGUN_REWARD_MULTIPLIER, BBGUN_PROJECTILE_SPEED,
                new GameImage(BBGUN_PROJECTILE_IMAGE_PATH,16,16));
    }
}
