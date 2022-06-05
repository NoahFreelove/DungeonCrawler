package com.dungeoncrawler.Entities.Player;

import com.JEngine.Components.Colliders.Collider_Comp;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.dungeoncrawler.Entities.Valueables.Gold;

public class PlayerCollider extends Collider_Comp {
    PlayerController player;
    public PlayerCollider(Vector3 initialOffset, float width, float height, GameObject parent) {
        super(initialOffset, width, height, false, parent);
        player = (PlayerController) parent;
    }

    /**
     * Check if the pawn can move if it has a hard collider
     * @param xDisplacement desired x displacement
     * @param yDisplacement desired y displacement
     * @return true if the pawn can move, false if it cannot
     */
    @Override
    public boolean canMove(float xDisplacement, float yDisplacement) {
        PlayerCollider tmpCollider = new PlayerCollider(getPosition(), getWidth(), getHeight(), getParent());
        tmpCollider.setPosition(new Vector3(getPosition().x + xDisplacement, getPosition().y + yDisplacement, getPosition().z));
        return !tmpCollider.isCollidingWithHard();
    }

    @Override
    public void onHit(Collider_Comp other) {
        if(player == null)
            return;
        switch (other.getParent().getIdentity().getTag()) {
            case "gold" -> {
                ((Gold)other.getParent()).pickup();
                player.addGold(((Gold)other.getParent()).getValue());
            }
        }
    }
}