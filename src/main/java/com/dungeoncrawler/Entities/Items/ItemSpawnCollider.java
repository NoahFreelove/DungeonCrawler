package com.dungeoncrawler.Entities.Items;

import com.JEngine.Components.Colliders.Collider_Comp;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Utility.Input;

public class ItemSpawnCollider extends Collider_Comp {
    ItemSpawn parent;
    public ItemSpawnCollider(Vector3 initialOffset, float width, float height, GameObject parent) {
        super(initialOffset, width, height, true, parent);
        this.parent = (ItemSpawn) parent;
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