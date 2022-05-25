package com.dungeoncrawler.GameObjects.Weapons;

import com.JEngine.Components.Colliders.Collider_Comp;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.dungeoncrawler.GameObjects.Enemy;
import com.dungeoncrawler.GameObjects.PlayerCollider;
import com.dungeoncrawler.GameObjects.PlayerController;
import com.dungeoncrawler.GameObjects.Weapons.Sword;

public class SwordCollider extends Collider_Comp {
    Sword parent;
    public SwordCollider(Vector3 initialOffset, float width, float height, boolean isTrigger, GameObject parent) {
        super(initialOffset, width, height, isTrigger, parent);
        this.parent = (Sword) parent;
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

    /**
     * Check if the pawn can move if it has a hard collider
     * @param xDisplacement desired x displacement
     * @param yDisplacement desired y displacement
     * @return true if the pawn can move, false if it cannot
     */
    @Override
    public boolean canMove(float xDisplacement, float yDisplacement) {
        PlayerCollider tmpCollider = new PlayerCollider(getPosition(), getWidth(), getHeight(), false, getParent());
        tmpCollider.setPosition(new Vector3(getPosition().x + xDisplacement, getPosition().y + yDisplacement, getPosition().z));
        return !tmpCollider.isCollidingWithHard();
    }

    @Override
    public void onHit(Collider_Comp other) {
        if(parent == null)
            return;
        switch (other.getParent().getIdentity().getTag()) {
            case "enemy" -> {
                if (PlayerController.instance.isAttacking())
                {
                    ((Enemy)other.getParent()).takeDamage(parent.getDamage());
                    PlayerController.instance.setAttacking(false);
                }
            }
        }
    }
}