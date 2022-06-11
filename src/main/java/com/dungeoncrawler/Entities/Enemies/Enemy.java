package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Components.Colliders.BoxCollider_Comp;
import com.JEngine.Core.Component;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GameTimer;
import com.JEngine.Utility.Misc.GameUtility;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Entities.Items.Gold;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;

import static com.dungeoncrawler.Entities.EnemyStats.DOCTOR_BASE_HEALTH;
import static com.dungeoncrawler.Entities.EnemyStats.DOCTOR_MAX_HEALTH;

public class Enemy extends Pawn {

    private final double damage;
    private double health;
    private double maxHealth;
    private final double attackDelay;
    private boolean canAttack;
    private final Vector3 startPos;
    private int difficulty;
    protected boolean canMove;
    private final GameImage sprite;
    private boolean addedHealthUI;
    protected ProgressBar healthBar = new ProgressBar();

    public Enemy(Vector3 initPos, GameImage newSprite, double damage, double health, double attackDelay, int difficulty) {
        super(Transform.simpleTransform(initPos), newSprite, new Identity("enemy"));
        this.damage = damage;
        this.health = health;
        this.maxHealth = health;
        this.attackDelay = attackDelay;
        this.difficulty = difficulty;
        canAttack = true;
        // attack range
        addCollider(new EnemyCollider(new Vector3(-newSprite.getWidth()/4f,-newSprite.getHeight()/4f),
                newSprite.getWidth()+ newSprite.getWidth()/2f, newSprite.getHeight()+ newSprite.getHeight()/2f, true,this));

        // Actual hitbox
        addCollider(new BoxCollider_Comp(Vector3.emptyVector(), newSprite.getWidth(),newSprite.getHeight(), false, this));
        this.sprite = newSprite;
        startPos = initPos;
    }

    public void attack() {
        if(damage <=0)
            return;
        canAttack = false;
        GameUtility.waitForSeconds(attackDelay, args -> canAttack = true);
    }

    public double getDamage() {
        return damage;
    }
    public void takeDamage(double damage){
        health -= damage;
        new GameTimer(150, args -> sprite.setColorAdjust(new ColorAdjust()),true, true);
        sprite.setColorAdjust(new ColorAdjust(1,1,0.5,1));

        if(health<=0)
        {
            onDeath();
        }
        //System.out.println("enemy new health: " + health);
    }

    @Override
    public void Update(){
        super.Update();
        Platform.runLater(() -> {
            healthBar.setProgress(health/maxHealth);
            healthBar.setLayoutX(getPosition().x);
            healthBar.setLayoutY(getPosition().y - 10);
            if(getHealth() >= 150)
            {
                healthBar.setStyle("-fx-accent: yellow");
            }
            else if(getHealth() >= 100)
            {
                healthBar.setStyle("-fx-accent: green");
            }
            else if(getHealth() >= 50)
            {
                healthBar.setStyle("-fx-accent: blue");
            }
            else {
                healthBar.setStyle("-fx-accent: red");
            }
        });
    }

    public boolean canAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public Vector3 getStartPos() {
        return startPos;
    }

    public int getDifficulty(){return difficulty;}
    public void setDifficulty(int difficulty){this.difficulty = difficulty;}

    public void activate(GameScene room){
        canMove = true;
        canAttack = true;
        if (!addedHealthUI)
        {
            healthBar.setStyle("-fx-accent: red");
            healthBar.setPrefHeight(15);
            healthBar.setPrefWidth(70);
            room.addUI(healthBar);
            addedHealthUI = true;
        }
    }


    public void neutralize(){
        canMove = false;
        canAttack = false;
    }

    protected void onDeath(){
        SceneManager.getActiveScene().remove(this);
        for (Component comp: getComponents()
        ) {
            comp.setActive(false);
        }
        PlayerController.instance.addExp(difficulty);
        RoomManager.rooms[RoomManager.currentRoomX][RoomManager.currentRoomY].removeEnemy();

        if(difficulty > 0)
        {
            SceneManager.getActiveScene().add(new Gold(getPosition(), (int) (difficulty*5*PlayerController.instance.getSelectedWeapon().getRewardMultiplier())));

        }
        healthBar.setVisible(false);
    }

    protected double playerPositionToRadians(){
        double rad;
        Vector3 deltaPos = PlayerController.instance.getPosition().subtract(getPosition());

        if(deltaPos.x<0)
        {
            rad = Math.atan(deltaPos.y/deltaPos.x)-(Math.PI);
        }
        else
        {
            rad = Math.atan(deltaPos.y/deltaPos.x);
        }
        return rad;
    }

    protected double facePlayer(){
        Vector3 deltaPos = PlayerController.instance.getPosition().subtract(getPosition());
        double deg;
        if(deltaPos.x<0)
        {
            deg = Math.toDegrees(Math.atan(deltaPos.y/deltaPos.x)-(Math.PI/2));
        }
        else
        {
            deg = Math.toDegrees(Math.atan(deltaPos.y/deltaPos.x)+(Math.PI/2));
        }
        return deg;
    }

    public double getHealth() {
        return health;
    }

    public double getAttackDelay() {
        return attackDelay;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}
