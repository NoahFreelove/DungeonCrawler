package com.dungeoncrawler.Entities.Valueables;

import com.JEngine.Components.Colliders.BoxCollider_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;

public class Gold extends Pawn {
    BoxCollider_Comp collider_comp;
    private final int value;
    public Gold(Vector3 pos, int value) {
        super(Transform.simpleTransform(pos.x+16,pos.y+16,pos.z), null, new Identity("Gold", "gold"));
        GameImage newSprite = new GameImage("bin/gold.png",32,32);
        setSprite(newSprite);

        collider_comp = new BoxCollider_Comp(Vector3.emptyVector(), 32, 32, true, this);
        addCollider(collider_comp);
        this.value = value;
    }

    @Override
    public void Update(){
        super.Update();
    }

    public void pickup(){
        collider_comp.setActive(false);
        setActive(false);
        SceneManager.getActiveScene().remove(this);
    }

    public int getValue() {
        return value;
    }
}
