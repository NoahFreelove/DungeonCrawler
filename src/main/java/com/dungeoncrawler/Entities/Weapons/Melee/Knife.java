package com.dungeoncrawler.Entities.Weapons.Melee;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;

import static com.dungeoncrawler.Entities.Weapons.WeaponStats.*;

public class Knife extends Melee {
    public Knife(Vector3 pos) {
        super(pos,KNIFE_DAMAGE, KNIFE_ATTACK_DELAY, new GameImage(KNIFE_IMAGE_PATH,32,32), KNIFE_ATTACK_DISTANCE, KNIFE_ANIMATION_SPEED, KNIFE_IS_THROWABLE, KNIFE_REWARD_MULTIPLIER);
    }
}
