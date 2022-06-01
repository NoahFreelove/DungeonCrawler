package com.dungeoncrawler.Entities.Weapons.Melee;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;

public class Sword extends Melee {
    public Sword(Vector3 pos) {
        super(pos,10, 0.5f, new GameImage("bin/sword.png"), 64, 1f, false);
    }
}
