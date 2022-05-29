package com.dungeoncrawler.GameObjects;

import com.JEngine.Components.Colliders.BoxCollider_Comp;
import com.JEngine.Core.Component;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GameUtility;
import com.JEngine.Utility.Misc.GenericMethod;

public class Enemy extends Pawn {
    private double damage;
    private double health;
    private double attackDelay;
    private boolean canAttack;
    public Enemy(Vector3 initPos, GameImage newSprite, double damage, double health, double attackDelay) {
        super(Transform.simpleTransform(initPos), newSprite, new Identity("enemy"));
        this.damage = damage;
        this.health = health;
        this.attackDelay = attackDelay;
        canAttack = true;
        addCollider(new EnemyCollider(new Vector3(-newSprite.getWidth()/2f,-newSprite.getHeight()/2f,0), 128,128,true,this));
        addCollider(new BoxCollider_Comp(Vector3.emptyVector(), newSprite.getWidth(),newSprite.getHeight(), false, this));
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
        if(health<=0)
        {
            SceneManager.getActiveScene().remove(this);
            for (Component comp: getComponents()
                 ) {
                comp.setActive(false);
            }
        }
        System.out.println("enemy new health: " + health);
    }

    public boolean canAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }
}
