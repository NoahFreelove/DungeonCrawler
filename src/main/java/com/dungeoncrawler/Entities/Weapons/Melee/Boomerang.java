package com.dungeoncrawler.Entities.Weapons.Melee;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;

public class Boomerang extends Melee{
    public Boomerang(Vector3 pos) {
        super(pos, 5, 1, new GameImage("bin/boomerang.png", 32, 32), 256, 0.25f, true);
        alwaysAttacking = true;
    }
}
