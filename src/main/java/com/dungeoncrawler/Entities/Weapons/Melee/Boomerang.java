package com.dungeoncrawler.Entities.Weapons.Melee;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;

import static com.dungeoncrawler.Entities.Weapons.WeaponStats.*;

public class Boomerang extends Melee{
    public Boomerang(Vector3 pos) {
        super(pos, BOOMERANG_DAMAGE, BOOMERANG_ATTACK_DELAY, new GameImage(BOOMERANG_IMAGE_PATH, 32, 32), BOOMERANG_THROW_DISTANCE, BOOMERANG_ANIMATION_SPEED, BOOMERANG_IS_THROWABLE, BOOMERANG_REWARD_MULTIPLIER);
        alwaysAttacking = true;
    }
}
