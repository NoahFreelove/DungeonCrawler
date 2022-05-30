package com.dungeoncrawler.GameObjects.Weapons.Projectile;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.GameObjects.PlayerController;
import com.dungeoncrawler.GameObjects.Weapons.Weapon;
import com.JEngine.Core.Position.SimpleDirection;

public class BarrettM82 extends Weapon {

    public BarrettM82(Vector3 pos) {
        super(50, 3, pos, new GameImage("bin/barrett.png", 128,128));
    }

    @Override
    protected void attack(SimpleDirection direction) {
        Vector2 dir = new Vector2(0,0);
        Vector3 rot = new Vector3(0,0,0);
        switch (direction)
        {
            case UP -> dir = new Vector2(0,-1);
            case DOWN -> {
                dir = new Vector2(0,1);
                rot = new Vector3(180,0,0);
            }
            case LEFT -> {
                dir = new Vector2(-1, 0);
                rot = new Vector3(270,0,0);
            }
            case RIGHT -> {
                dir = new Vector2(1, 0);
                rot = new Vector3(90,0,0);
            }
        }

        Projectile projectile = new Projectile(getPosition().add(new Vector3(0,32,0)), rot, dir, 25, 35f, new GameImage("bin/50bmg.png"));
        SceneManager.getActiveScene().add(projectile);

    }

    @Override
    public void Update(){
        super.Update();
        setPosition(PlayerController.instance.getPosition().add(new Vector3(0,-32,0)));
    }
}
