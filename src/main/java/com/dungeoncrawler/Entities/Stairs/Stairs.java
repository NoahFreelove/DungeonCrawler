package com.dungeoncrawler.Entities.Stairs;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;

public class Stairs extends Pawn {
    public boolean usedStairs = false;
    public Stairs() {
        super(Transform.simpleTransform(500,350,-500), new GameImage("bin/images/stairs.png"), new Identity("stairs"));
        addCollider(new StairsCollider(Vector3.emptyVector(), 64,64, this));
    }

    @Override
    public void Update(){
        super.Update();
    }
}
