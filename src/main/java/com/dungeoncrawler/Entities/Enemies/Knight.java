package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Utility.ImageProcessing.GenerateSolidTexture;

public class Knight extends Boss {
    public Knight(Vector3 initPos) {
        super(initPos, new GameImage(GenerateSolidTexture.generateImage(96,96,0xFF6E6E6E))
                , 0, 35, 0.5, 5, "Prepare to Fight!", 0.5f);
    }
}
