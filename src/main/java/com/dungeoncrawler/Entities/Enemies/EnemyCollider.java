package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Components.Colliders.Collider_Comp;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.dungeoncrawler.Entities.Player.PlayerController;

public class EnemyCollider extends Collider_Comp {
    Enemy parent;
    public EnemyCollider(Vector3 initialOffset, float width, float height, boolean isTrigger, GameObject parent) {
        super(initialOffset, width, height, isTrigger, parent);
        this.parent = (Enemy) parent;
    }
    
    /**
     * Check if the pawn can move if it has a hard collider
     * @param xDisplacement desired x displacement
     * @param yDisplacement desired y displacement
     * @return true if the pawn can move, false if it cannot
     */
    @Override
    public boolean canMove(float xDisplacement, float yDisplacement) {
        EnemyCollider tmpCollider = new EnemyCollider(getPosition(), getWidth(), getHeight(), false, getParent());
        tmpCollider.setPosition(new Vector3(getPosition().x + xDisplacement, getPosition().y + yDisplacement, getPosition().z));
        return !tmpCollider.isCollidingWithHard();
    }

    @Override
    public void onHit(Collider_Comp other) {
        if ("player".equals(other.getParent().getIdentity().getTag())) {
            if (parent.canAttack()) {
                parent.attack();
                ((PlayerController) other.getParent()).takeDamage(parent.getDamage());
            }
        }
    }
}