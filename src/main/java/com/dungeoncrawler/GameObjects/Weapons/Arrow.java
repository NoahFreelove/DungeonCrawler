package com.dungeoncrawler.GameObjects.Weapons;

import com.JEngine.Core.Component;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;

public class Arrow extends Pawn {
    float moveSpeed = 10f;
    Vector2 direction;
    double damage;
    int life = 60;
    ArrowCollider collider;
    public Arrow(Vector3 pos, Vector3 rot, Vector2 moveDirection, double damage) {
        super(new Transform(pos, rot, Vector3.oneVector()), new GameImage("bin/arrow.png"), new Identity("arrow"));
        this.direction = moveDirection;
        this.damage = damage;
        collider = new ArrowCollider(Vector3.emptyVector(), 32,32, true, this);
        addComponent(collider);
    }

    @Override
    public void Update(){
        super.Update();
        life--;
        if(life<=0)
        {
            destroySelf();
            return;
        }
        Move(direction, moveSpeed);

    }

    public double getDamage(){ return damage;}

    public void destroySelf(){
        setActive(false);
        SceneManager.getActiveScene().remove(this);
        for (Component comp: getComponents()) {
            if(comp!=null)
            {
                comp.setActive(false);
            }
        }
    }
}
