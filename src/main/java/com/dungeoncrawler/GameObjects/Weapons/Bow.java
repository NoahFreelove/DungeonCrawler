package com.dungeoncrawler.GameObjects.Weapons;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.GameObjects.PlayerController;
import com.dungeoncrawler.SimpleDirection;

public class Bow extends Weapon{
    public Bow(Vector3 pos) {
        super(5, 0.5, pos, new GameImage("bin/bow.png"));
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

        Arrow arrow = new Arrow(getPosition(), rot, dir, 5);
        SceneManager.getActiveScene().add(arrow);

    }

    @Override
    public void Update(){
        super.Update();
        setPosition(PlayerController.instance.getPosition().add(new Vector3(32,0,0)));
    }


}