package com.dungeoncrawler.Entities;

public record EnemyStats() {
    //region Follower
    public static final float FOLLOWER_SPEED = 3;
    public static final double FOLLOWER_DAMAGE = 5;
    public static final double FOLLOWER_ATTACK_DELAY = 3;
    public static final double FOLLOWER_MAX_HEALTH = 25;
    public static final String FOLLOWER_IMAGE_PATH = "bin/follower.png";
    public static final int FOLLOWER_DIFFICULTY = 2;
    //endregion

    //region Shooter
    public static final float SHOOTER_PROJECTILE_SPEED = 8;
    public static final double SHOOTER_DAMAGE = 5;
    public static final double SHOOTER_ATTACK_DELAY = 1.5f;
    public static final double SHOOTER_MAX_HEALTH = 15;
    public static final String SHOOTER_IMAGE_PATH = "bin/shooter.png";
    public static final String SHOOTER_PROJECTILE_IMAGE_PATH = "bin/50bmg.png";
    public static final int SHOOTER_DIFFICULTY = 3;
    //endregion

    //region Frog
    public static final float FROG_SPEED = 15;
    public static final double FROG_DAMAGE = 5;
    public static final double FROG_ATTACK_DELAY = 2;
    public static final double FROG_MAX_HEALTH = 15;
    public static final float FROG_MAGNET_DISTANCE = 400f;
    public static final String FROG_IMAGE_PATH = "bin/frog.png";
    public static final int FROG_DIFFICULTY = 2;
    //endregion

    //region Knight
    public static final float KNIGHT_CHARGE_SPEED = 10;
    public static final double KNIGHT_DAMAGE = 8;
    public static final double KNIGHT_ATTACK_DELAY = 0.8;
    public static final double KNIGHT_CHARGE_DELAY = 3;

    public static final double KNIGHT_MAX_HEALTH = 100;
    public static final String KNIGHT_IMAGE_PATH = "bin/knight.png";
    public static final int KNIGHT_DIFFICULTY = 5;
    //endregion

    //region Turret
    public static final double TURRET_DAMAGE = 0;
    public static final double TURRET_PROJECTILE_DAMAGE = 3;
    public static final double TURRET_SHOOT_DELAY = 0.05;
    public static final double TURRET_SHOOT_DURATION = 5;
    public static final double TURRET_IDLE_DURATION = 2;
    public static final double TURRET_MAX_HEALTH = 30;

    public static final String  TURRET_PROJECTILE_IMAGE_PATH = "bin/bbpellet.png";
    public static final float  TURRET_PROJECTILE_SPEED = 6;
    
    public static final String TURRET_IMAGE_PATH = "bin/turret.png";
    public static final int TURRET_DIFFICULTY = 5;
    //endregion

    //region Graveyard
    public static final double GRAVEYARD_DAMAGE = 8;
    public static final double GRAVEYARD_ATTACK_DELAY = 1;
    public static final double GRAVEYARD_MAX_HEALTH = 50;
    public static final double GRAVEYARD_SPAWN_DELAY = 6;
    public static final float GRAVEYARD_SPEED = 3;

    public static final String GRAVEYARD_IMAGE_PATH = "bin/skeleton.png";
    public static final int GRAVEYARD_DIFFICULTY = 7;
    //endregion

    //region Skeleton
    public static final double SKELETON_DAMAGE = 2;
    public static final double SKELETON_ATTACK_DELAY = 0.3;
    public static final double SKELETON_MAX_HEALTH = 6;
    public static final float SKELETON_MOVE_SPEED = 3f;
    public static final double SKELETON_RISE_TIME = 1.5;

    public static final String SKELETON_IMAGE_PATH = "bin/gravestone.png";
    public static final int SKELETON_DIFFICULTY = 1;
    //endregion

    //region Doctor
    public static final double DOCTOR_DAMAGE = 12;
    public static final float DOCTOR_MOVE_SPEED = 4;
    public static final float DOCTOR_RUN_SPEED = 6;
    public static final double DOCTOR_ATTACK_DELAY = 0.5;
    public static final double DOCTOR_BASE_HEALTH = 50;
    public static final double DOCTOR_MAX_HEALTH = 150;
    public static final double DOCTOR_HEAL_THRESHOLD = 15;
    public static final double DOCTOR_HEAL_RATE = 5;

    public static final String DOCTOR_IMAGE_PATH = "bin/doctor.png";
    public static final int DOCTOR_DIFFICULTY = 8;
    //endregion

    //region Wizard
    public static final double WIZARD_DAMAGE = 0;
    public static final float WIZARD_MAX_HEALTH = 40;
    public static final double WIZARD_TELEPORT_DELAY = 1.7;
    public static final double WIZARD_ATTACK_DELAY = 1;
    public static final String WIZARD_IMAGE_PATH = "bin/wizard.png";
    public static final int WIZARD_DIFFICULTY = 4;

    public static final float WIZARD_MAGIC_MOVE_SPEED = 6;
    public static final double WIZARD_MAGIC_DAMAGE = 12;
    public static final String WIZARD_MAGIC_IMAGE_PATH = "bin/wizardMagic.png";

    //endregion


}
