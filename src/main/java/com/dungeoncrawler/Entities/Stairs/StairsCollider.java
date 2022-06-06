package com.dungeoncrawler.Entities.Stairs;

import com.JEngine.Components.Colliders.Collider_Comp;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Utility.Input;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;

public class StairsCollider extends Collider_Comp {
    Stairs parent;
    public StairsCollider(Vector3 initialOffset, float width, float height, GameObject parent) {
        super(initialOffset, width, height, true, parent);
        this.parent = (Stairs) parent;
    }
    @Override
    public void onHit(Collider_Comp other) {
        if(parent == null)
            return;
        if ("player".equals(other.getParent().getIdentity().getTag())) {
            if(Input.Control_Pressed)
            {
                if(!parent.usedStairs)
                {
                    parent.usedStairs = true;
                    if(RoomManager.inTutorial)
                    {
                        RoomManager.endDungeon(false);
                    }
                    else {
                        RoomManager.endDungeon(true);
                    }
                }
            }
        }
    }
}