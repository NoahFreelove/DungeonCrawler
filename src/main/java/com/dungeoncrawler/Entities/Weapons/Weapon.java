package com.dungeoncrawler.Entities.Weapons;

import com.JEngine.Components.DontDestroyOnLoad_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.Utility.Misc.GameUtility;
import com.JEngine.Core.Position.SimpleDirection;

public abstract class Weapon extends Sprite {

    protected float damage;
    protected double attackDelay;
    protected boolean ableToAttack;

    public Weapon(float damage, double attackDelay, Vector3 pos, GameImage sprite) {
        super(Transform.simpleTransform(pos), sprite, new Identity("Weapon", "weapon"));
        addComponent(new DontDestroyOnLoad_Comp());

        this.damage = damage;
        this.attackDelay = attackDelay;
        this.ableToAttack = true;
    }

    public boolean requestAttack(SimpleDirection direction) {
        if(ableToAttack){
            ableToAttack = false;
            attack(direction);
            GameUtility.waitForSeconds(attackDelay, args -> ableToAttack = true);
            return true;
        }
        return false;
    }

    protected abstract void attack(SimpleDirection direction);

    public float getDamage() {
        return damage;
    }

    public void updateRotation(Vector2 newRot)
    {
        getTransform().setRotation(new Vector3(newRot.x,0,newRot.y));
    }

    public boolean isAbleToAttack() {
        return ableToAttack;
    }
}
