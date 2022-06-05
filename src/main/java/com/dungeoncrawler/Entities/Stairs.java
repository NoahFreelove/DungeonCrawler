package com.dungeoncrawler.Entities;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Utility.ImageProcessing.GenerateSolidTexture;
import com.dungeoncrawler.StairsCollider;

public class Stairs extends Pawn {
    public boolean usedStairs = false;
    public Stairs() {
        super(Transform.exSimpleTransform(500,350), new GameImage(GenerateSolidTexture.generateImage(64,64,0xFFFFFFFF)), new Identity("stairs"));
        addCollider(new StairsCollider(Vector3.emptyVector(), 64,64, this));
    }

    @Override
    public void Update(){
        super.Update();
    }
}
