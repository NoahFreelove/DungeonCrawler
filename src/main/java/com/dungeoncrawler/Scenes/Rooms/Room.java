package com.dungeoncrawler.Scenes.Rooms;

import com.JEngine.Core.Position.Vector2;
import com.JEngine.Game.Visual.Scenes.GameScene;

public class Room extends GameScene {
    public Room(int difficulty, boolean leftDoor, boolean rightDoor, boolean topDoor, boolean bottomDoor) {
        super("Room");
        CreateRoom(difficulty, leftDoor, rightDoor, topDoor, bottomDoor);
    }

    public void CreateRoom(int difficulty, boolean leftDoor, boolean rightDoor, boolean topDoor, boolean bottomDoor){
        //Create left wall
        if(!leftDoor)
        {
            add(new Wall(new Vector2(0,0), new Vector2(1, 11)));
        }
        else {
            add(new Wall(new Vector2(0,0), new Vector2(1, 4.5)));
            add(new Wall(new Vector2(0,384), new Vector2(1, 5)));

        }
        if(!rightDoor)
        {
            add(new Wall(new Vector2(1200,0), new Vector2(1, 11)));
        }
        else {
            add(new Wall(new Vector2(1200,0), new Vector2(1, 4.5)));
            add(new Wall(new Vector2(1200,384), new Vector2(1, 5)));
        }

        //Create top wall
        if(!topDoor)
        {
            add(new Wall(new Vector2(0,0), new Vector2(20, 1)));
        }
        else {
            add(new Wall(new Vector2(0,0), new Vector2(8.5, 1)));
            add(new Wall(new Vector2(660,0), new Vector2(8.5, 1)));
        }

        //Create bottom wall
        if(!bottomDoor)
        {
            add(new Wall(new Vector2(0,620), new Vector2(20, 1)));
        }
        else {
            add(new Wall(new Vector2(0,620), new Vector2(8.5, 1)));
            add(new Wall(new Vector2(660,620), new Vector2(8.5, 1)));
        }
        createEnemies(difficulty);
    }

    private void createEnemies(int difficulty) {

    }

}
