package com.dungeoncrawler.GameObjects.Weapons;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.dungeoncrawler.SimpleDirection;

public class Sword extends Weapon {

    public Sword(Vector3 pos, GameImage sprite) {
        super(10, 0.5, pos, sprite);
    }

    @Override
    protected void attack(SimpleDirection direction) {
        
    }
}
