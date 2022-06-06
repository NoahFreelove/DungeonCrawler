package com.dungeoncrawler.Entities;

public record EnemyStats() {
    //region Follower
    public static final float FOLLOWER_SPEED = 3;
    public static final double FOLLOWER_DAMAGE = 5;
    public static final double FOLLOWER_ATTACK_DELAY = 5;
    public static final double FOLLOWER_MAX_HEALTH = 25;
    public static final String FOLLOWER_IMAGE_PATH = "bin/follower.png";
    public static final int FOLLOWER_DIFFICULTY = 1;
    //endregion

    //region Shooter
    public static final float SHOOTER_PROJECTILE_SPEED = 8;
    public static final double SHOOTER_DAMAGE = 5;
    public static final double SHOOTER_ATTACK_DELAY = 1;
    public static final double SHOOTER_MAX_HEALTH = 15;
    public static final String SHOOTER_IMAGE_PATH = "bin/shooter.png";
    public static final String SHOOTER_PROJECTILE_IMAGE_PATH = "bin/50bmg.png";
    public static final int SHOOTER_DIFFICULTY = 2;
    //endregion

    //region Frog
    public static final float FROG_SPEED = 15;
    public static final double FROG_DAMAGE = 5;
    public static final double FROG_ATTACK_DELAY = 2;
    public static final double FROG_MAX_HEALTH = 15;
    public static final float FROG_MAGNET_DISTANCE = 400f;
    public static final String FROG_IMAGE_PATH = "bin/frog.png";
    public static final int FROG_DIFFICULTY = 1;
    //endregion

    //region Knight
    public static final float KNIGHT_CHARGE_SPEED = 10;
    public static final double KNIGHT_DAMAGE = 5;
    public static final double KNIGHT_ATTACK_DELAY = 0.8;
    public static final double KNIGHT_CHARGE_DELAY = 3;

    public static final double KNIGHT_MAX_HEALTH = 50;
    public static final String KNIGHT_IMAGE_PATH = "bin/knight.png";
    public static final int KNIGHT_DIFFICULTY = 5;
    //endregion

    //region Turret
    public static final double TURRET_DAMAGE = 3;
    public static final double TURRET_SHOOT_DELAY = 0.05;
    public static final double TURRET_SHOOT_DURATION = 5;
    public static final double TURRET_IDLE_DURATION = 2;
    public static final double TURRET_MAX_HEALTH = 35;

    public static final String  TURRET_PROJECTILE_IMAGE_PATH = "bin/bbpellet.png";
    public static final float  TURRET_PROJECTILE_SPEED = 5;
    
    public static final String TURRET_IMAGE_PATH = "bin/turret.png";
    public static final int TURRET_DIFFICULTY = 5;
    //endregion
}
