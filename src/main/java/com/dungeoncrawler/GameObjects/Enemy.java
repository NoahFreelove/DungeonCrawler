package com.dungeoncrawler.GameObjects;

import com.JEngine.Components.Colliders.BoxCollider_Comp;
import com.JEngine.Core.Component;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GameTimer;
import com.JEngine.Utility.Misc.GameUtility;
import com.JEngine.Utility.Misc.GenericMethod;
import com.dungeoncrawler.GameObjects.Valueables.Gold;
import javafx.scene.effect.ColorAdjust;

public class Enemy extends Pawn {
    private double damage;
    private double health;
    private double attackDelay;
    private boolean canAttack;
    private Vector3 startPos;
    private int difficulty;
    protected boolean canMove;
    private GameImage sprite;
    public Enemy(Vector3 initPos, GameImage newSprite, double damage, double health, double attackDelay, int difficulty) {
        super(Transform.simpleTransform(initPos), newSprite, new Identity("enemy"));
        this.damage = damage;
        this.health = health;
        this.attackDelay = attackDelay;
        this.difficulty = difficulty;
        canAttack = true;
        addCollider(new EnemyCollider(new Vector3(-newSprite.getWidth()/2f,-newSprite.getHeight()/2f,0), 128,128,true,this));
        addCollider(new BoxCollider_Comp(Vector3.emptyVector(), newSprite.getWidth(),newSprite.getHeight(), false, this));
        this.sprite = newSprite;
        startPos = initPos;
    }
    public void attack(){
        canAttack = false;
        GameUtility.waitForSeconds(attackDelay, args -> canAttack = true);
    }

    public double getDamage() {
        return damage;
    }
    public void takeDamage(double damage){
        health -= damage;
        GameTimer hurtEffect = new GameTimer(300, args -> {});
        hurtEffect.setRunEvents(new GenericMethod[]{args -> {
            sprite.setColorAdjust(new ColorAdjust());
            hurtEffect.stop();
        }});
        sprite.setColorAdjust(new ColorAdjust(1,1,0.5,1));
        hurtEffect.start();

        if(health<=0)
        {
            onDeath();
        }
        System.out.println("enemy new health: " + health);
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

    public void activate(){
        canMove = true;
        canAttack = true;
    }


    public void neutralize(){
        canMove = false;
        canAttack = false;
    }

    private void onDeath(){
        SceneManager.getActiveScene().remove(this);
        for (Component comp: getComponents()
        ) {
            comp.setActive(false);
        }
        SceneManager.getActiveScene().add(new Gold(getPosition(), difficulty*10));
    }
}
