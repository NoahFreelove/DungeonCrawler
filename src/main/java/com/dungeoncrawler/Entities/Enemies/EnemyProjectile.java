package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;

public class EnemyProjectile extends Pawn {
    Vector2 direction;
    int life = 180;
    float moveSpeed = 5;
    double damage;

    // Make collider a bit smaller than the bullet because it's hard to dodge them all
    EnemyProjectileCollider ec = new EnemyProjectileCollider(Vector3.emptyVector(), 24,24,true,this);
    public EnemyProjectile(Vector3 initPos, double angle, double damage) {
        super(new Transform(initPos,new Vector3((float) angle+90,0,0), new Vector3(0.5f,0.5f,0)), new GameImage("bin/50bmg.png"), new Identity("EnemyProjectile"));
        this.direction = angleToVector(Math.toRadians(angle));
        addCollider(ec);
        this.damage = damage;
    }

    private Vector2 angleToVector(double radian){
        return new Vector2(Math.cos(radian), Math.sin(radian));
    }

    @Override
    public void Update() {
        life--;
        if (life <= 0)
        {
            onHit();
        }else {
            super.Update();
            Move(direction, moveSpeed);
        }
    }

    public void onHit(){
        SceneManager.getActiveScene().remove(this);
        setActive(false);
        ec.setActive(false);
    }

    public double getDamage() {
        return damage;
    }
}