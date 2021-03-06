package com.dungeoncrawler.Scenes.Rooms;

import com.JEngine.Components.Colliders.BoxCollider_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Sprite;

public class Wall extends Sprite {
    public Wall(Vector2 pos, Vector2 scale) {
        super(new Transform(new Vector3(pos.x,pos.y,0), Vector3.emptyVector(), new Vector3(1,1,1)), null, new Identity("wall"));
        GameImage image = new GameImage("bin/images/wall.png", (int) (64 * scale.x), (int) (64 * scale.y));
        image.setTiled(true);
        image.setTileSizeX(128);
        image.setTileSizeY(128);
        setSprite(image);
        addCollider(new BoxCollider_Comp(Vector3.emptyVector(), scale.x*64, scale.y*64, false, this));
    }

    @Override
    public void Update() {
        super.Update();
    }
}

