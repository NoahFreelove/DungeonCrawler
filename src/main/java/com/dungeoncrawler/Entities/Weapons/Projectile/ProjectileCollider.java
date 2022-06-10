package com.dungeoncrawler.Entities.Weapons.Projectile;

import com.JEngine.Components.Colliders.Collider_Comp;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.dungeoncrawler.Entities.Enemies.Enemy;
import com.dungeoncrawler.Entities.Player.PlayerController;

import java.util.ArrayList;

public class ProjectileCollider extends Collider_Comp {
    Projectile parent;
    ArrayList<Enemy> enemiesHit = new ArrayList<>(1);
    public ProjectileCollider(Vector3 initialOffset, float width, float height, GameObject parent) {
        super(initialOffset, width, height, true, parent);
        this.parent = (Projectile) parent;
    }

    @Override
    public void onHit(Collider_Comp other) {
        if(parent == null)
            return;
        if ("enemy".equals(other.getParent().getIdentity().getTag())) {
            if(enemiesHit.contains((Enemy)other.getParent())){
                parent.onHit();
                return;
            }
            enemiesHit.add((Enemy) other.getParent());
            ((Enemy) other.getParent()).takeDamage(parent.getDamage());
            parent.onHit();
            if (PlayerController.instance != null) {
                PlayerController.instance.setAttacking(false);
                PlayerController.instance.onHurtEnemy();
            }
        }
        if("wall".equals(other.getParent().getIdentity().getTag())){
            parent.onHit();
        }
    }
}