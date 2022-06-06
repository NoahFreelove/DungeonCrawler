package com.dungeoncrawler.Entities.Weapons.Projectile;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Entities.Weapons.Weapon;
import com.JEngine.Core.Position.SimpleDirection;

import static com.dungeoncrawler.Entities.Weapons.WeaponStats.*;

public class Bow extends ProjectileWeapon {
    public Bow(Vector3 pos) {
        super(pos, BOW_OFFSET, BOW_DAMAGE, BOW_SHOOT_DELAY, new GameImage(BOW_IMAGE_PATH), BOW_REWARD_MULTIPLIER, BOW_PROJECTILE_SPEED, new GameImage(BOW_PROJECTILE_IMAGE_PATH));
    }
}
