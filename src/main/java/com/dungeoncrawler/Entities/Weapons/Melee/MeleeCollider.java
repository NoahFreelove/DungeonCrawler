package com.dungeoncrawler.Entities.Weapons.Melee;

import com.JEngine.Components.Colliders.Collider_Comp;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.dungeoncrawler.Entities.Enemies.Enemy;
import com.dungeoncrawler.Entities.Player.PlayerController;

public class MeleeCollider extends Collider_Comp {
    Melee parent;
    public MeleeCollider(Vector3 initialOffset, float width, float height, GameObject parent) {
        super(initialOffset, width, height, true, parent);
        this.parent = (Melee) parent;
    }

    /**
     * Check if the pawn can move if it has a hard collider
     * @param xDisplacement desired x displacement
     * @param yDisplacement desired y displacement
     * @return true if the pawn can move, false if it cannot
     */
    @Override
    public boolean canMove(float xDisplacement, float yDisplacement) {
        MeleeCollider tmpCollider = new MeleeCollider(getPosition(), getWidth(), getHeight(), getParent());
        tmpCollider.setPosition(new Vector3(getPosition().x + xDisplacement, getPosition().y + yDisplacement, getPosition().z));
        return !tmpCollider.isCollidingWithHard();
    }

    @Override
    public void onHit(Collider_Comp other) {
        if(parent == null)
            return;
        if(parent.isAbleToAttack())
            return;
        switch (other.getParent().getIdentity().getTag()) {
            case "enemy" -> {
                if (PlayerController.instance.isAttacking())
                {
                    ((Enemy)other.getParent()).takeDamage(parent.getDamage());
                    if(PlayerController.instance !=null)
                    {
                        PlayerController.instance.setAttacking(false);
                        PlayerController.instance.onHurtEnemy();
                    }
                }
            }
        }
    }
}