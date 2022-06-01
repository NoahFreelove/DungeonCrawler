package com.dungeoncrawler.Scenes.Rooms;

import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.dungeoncrawler.Entities.Enemies.Follower;
import com.dungeoncrawler.Entities.Enemies.Knight;

public class Room extends GameScene {
    boolean isSpawnRoom = false;
    boolean isBossRoom = false;
    public Room(int difficulty, boolean leftDoor, boolean rightDoor, boolean topDoor, boolean bottomDoor) {
        super("Room");
        CreateRoom(difficulty, leftDoor, rightDoor, topDoor, bottomDoor);
    }

    public Room(int difficulty, boolean leftDoor, boolean rightDoor, boolean topDoor, boolean bottomDoor, boolean isSpawnRoom) {
        super("Room");
        this.isSpawnRoom = isSpawnRoom;
        CreateRoom(difficulty, leftDoor, rightDoor, topDoor, bottomDoor);
    }

    public Room(int difficulty, boolean leftDoor, boolean rightDoor, boolean topDoor, boolean bottomDoor, boolean isSpawnRoom, boolean isBossRoom) {
        super("Room");
        this.isSpawnRoom = isSpawnRoom;
        this.isBossRoom = isBossRoom;
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
        if(!isSpawnRoom && !isBossRoom)
            createEnemies(difficulty);

        if(isBossRoom)
        {
            createBoss();
        }
    }

    private void createEnemies(int difficulty) {
        Vector3 randomPosition = new Vector3(0,0,0);
        for(int i = 0; i<difficulty; i++)
        {
            randomPosition.x = (float) (Math.random()*600)+400;
            randomPosition.y = 300;

            add(new Follower(randomPosition));

        }
    }

    private void createBoss(){
        add(new Knight(new Vector3(1150-300,300)));
    }

}
