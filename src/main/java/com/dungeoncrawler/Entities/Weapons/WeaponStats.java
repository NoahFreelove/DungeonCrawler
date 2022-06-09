package com.dungeoncrawler.Entities.Weapons;

import com.JEngine.Core.Position.Vector2;

public record WeaponStats() {
    //region Boomerang
    public static final float BOOMERANG_DAMAGE = 10;
    public static final double BOOMERANG_ATTACK_DELAY = 1;
    public static final float BOOMERANG_THROW_DISTANCE = 256;
    public static final float BOOMERANG_ANIMATION_SPEED = 0.25f;
    public static final boolean BOOMERANG_IS_THROWABLE = true;
    public static final String BOOMERANG_IMAGE_PATH = "bin/boomerang.png";
    public static final double BOOMERANG_REWARD_MULTIPLIER = 1.5;
    //endregion

    //region Sword
    public static final float SWORD_DAMAGE = 10;
    public static final double SWORD_ATTACK_DELAY = 0.5;
    public static final float SWORD_ATTACK_DISTANCE = 128;
    public static final float SWORD_ANIMATION_SPEED = 1f;
    public static final boolean SWORD_IS_THROWABLE = false;
    public static final String SWORD_IMAGE_PATH = "bin/sword.png";
    public static final double SWORD_REWARD_MULTIPLIER = 1.5;
    //endregion

    //region BarrettM82
    public static final float BARRETT_M82_DAMAGE = 50;
    public static final double BARRETT_M82_SHOOT_DELAY = 2;
    public static final float BARRETT_M82_PROJECTILE_SPEED = 35f;
    public static final String BARRETT_M82_PROJECTILE_IMAGE_PATH = "bin/50bmg.png";
    public static final String BARRETT_M82_IMAGE_PATH = "bin/barrett.png";
    public static final Vector2 BARRETT_M82_OFFSET = new Vector2(0,-32);

    public static final double BARRETT_M82_REWARD_MULTIPLIER = 0.2;
    //endregion

    //region BBGun
    public static final float BBGUN_DAMAGE = 1;
    public static final double BBGUN_SHOOT_DELAY = 0.1;
    public static final float BBGUN_PROJECTILE_SPEED = 20f;
    public static final String BBGUN_PROJECTILE_IMAGE_PATH = "bin/bbpellet.png";
    public static final String BBGUN_IMAGE_PATH = "bin/bbgun.png";
    public static final Vector2 BBGUN_OFFSET = new Vector2(64,0);

    public static final double BBGUN_REWARD_MULTIPLIER = 0.8;
    //endregion

    //region Bow
    public static final float BOW_DAMAGE = 6;
    public static final double BOW_SHOOT_DELAY = 0.5f;
    public static final float BOW_PROJECTILE_SPEED = 20;
    public static final String BOW_PROJECTILE_IMAGE_PATH = "bin/arrow.png";
    public static final String BOW_IMAGE_PATH = "bin/bow.png";
    public static final Vector2 BOW_OFFSET = new Vector2(32,0);
    public static final double BOW_REWARD_MULTIPLIER = 0.5;
    //endregion

}
