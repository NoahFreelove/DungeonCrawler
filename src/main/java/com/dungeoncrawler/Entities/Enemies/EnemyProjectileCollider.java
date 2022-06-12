package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Components.Colliders.Collider_Comp;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.dungeoncrawler.Entities.Player.PlayerController;

public class EnemyProjectileCollider extends Collider_Comp {
    EnemyProjectile parent;
    public EnemyProjectileCollider(Vector3 initialOffset, float width, float height, boolean isTrigger, GameObject parent) {
        super(initialOffset, width, height, isTrigger, parent);
        this.parent = (EnemyProjectile) parent;
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
        switch (other.getParent().getIdentity().getTag()) {
            case "player"->{
                if(!parent.canMove)
                    return;
                parent.onHit();
                ((PlayerController)other.getParent()).takeDamage(parent.getDamage());
            }
            case "wall"->{
                parent.onHit();
            }
        }
    }
}
