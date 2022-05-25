package com.dungeoncrawler.GameObjects.Weapons;

import com.JEngine.Components.Colliders.Collider_Comp;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.dungeoncrawler.GameObjects.Enemy;
import com.dungeoncrawler.GameObjects.PlayerCollider;
import com.dungeoncrawler.GameObjects.PlayerController;
import com.dungeoncrawler.GameObjects.Weapons.Sword;

public class ArrowCollider extends Collider_Comp {
    Arrow parent;
    public ArrowCollider(Vector3 initialOffset, float width, float height, boolean isTrigger, GameObject parent) {
        super(initialOffset, width, height, true, parent);
        this.parent = (Arrow) parent;
    }

    @Override
    public void onHit(Collider_Comp other) {
        if(parent == null)
            return;
        switch (other.getParent().getIdentity().getTag()) {
            case "enemy" -> {
                ((Enemy)other.getParent()).takeDamage(parent.getDamage());
                PlayerController.instance.setAttacking(false);
                parent.destroySelf();
            }
        }
    }
}