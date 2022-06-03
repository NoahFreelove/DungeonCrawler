package com.dungeoncrawler.Entities;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;

public class Shop extends Pawn {
    public Shop() {
        super(Transform.simpleTransform(new Vector3(500,300)), null, new Identity("Shop"));
    }
}
