package com.dungeoncrawler.Entities.Weapons.Melee;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;

import static com.dungeoncrawler.Entities.Weapons.WeaponStats.*;

public class Sword extends Melee {
    public Sword(Vector3 pos) {
        super(pos,SWORD_DAMAGE, SWORD_ATTACK_DELAY, new GameImage(SWORD_IMAGE_PATH), SWORD_ATTACK_DISTANCE, SWORD_ANIMATION_SPEED, SWORD_IS_THROWABLE, SWORD_REWARD_MULTIPLIER);
    }
}
