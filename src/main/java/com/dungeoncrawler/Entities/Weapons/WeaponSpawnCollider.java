package com.dungeoncrawler.Entities.Weapons;

import com.JEngine.Components.Colliders.Collider_Comp;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Utility.Input;
import com.dungeoncrawler.Entities.Enemies.Enemy;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Entities.Weapons.Melee.Melee;
import com.dungeoncrawler.Entities.Weapons.Melee.MeleeCollider;

public class WeaponSpawnCollider extends Collider_Comp {
    WeaponSpawn parent;
    public WeaponSpawnCollider(Vector3 initialOffset, float width, float height, GameObject parent) {
        super(initialOffset, width, height, true, parent);
        this.parent = (WeaponSpawn) parent;
    }

    @Override
    public boolean isCollidingWith(Collider_Comp collider) {
        if(collider == null)
            return false;

        // logic to check if positions are in range (checks only x and y values, z values are irrelevant)
        float x1 = collider.getPosition().x;
        float y1 = collider.getPosition().y;
        float x2 = (collider.getPosition().x + collider.getWidth());
        float y2 = (collider.getPosition().y + collider.getHeight());
        float x3 = getPosition().x;
        float y3 = getPosition().y;
        float x4 = (getPosition().x + getWidth());
        float y4 = (getPosition().y+ getHeight());

        return (x1 <= x4) && (x3 <= x2) && (y1 <= y4) && (y3 <= y2);
    }

    @Override
    public void onHit(Collider_Comp other) {
        if(parent == null)
            return;

        if ("player".equals(other.getParent().getIdentity().getTag())) {
            if(Input.Control_Pressed)
            {
                parent.Buy();
            }
        }
    }
}