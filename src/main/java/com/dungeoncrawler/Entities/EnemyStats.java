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
    public static float FROG_MAGNET_DISTANCE = 400f;
    public static final String FROG_IMAGE_PATH = "bin/frog.png";
    public static final int FROG_DIFFICULTY = 1;
    //endregion
}
