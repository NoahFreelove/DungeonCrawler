package com.dungeoncrawler.Scenes.Rooms;

import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.Misc.GameUtility;
import com.dungeoncrawler.Entities.Enemies.Follower;
import com.dungeoncrawler.Entities.Enemies.Bosses.Knight;
import com.dungeoncrawler.Entities.Enemies.Shooter;

public class Room extends GameScene {
    boolean isSpawnRoom = false;
    boolean isBossRoom = false;
    int bossX = 0;
    int bossY = 0;
    public Room(int difficulty, boolean leftDoor, boolean rightDoor, boolean topDoor, boolean bottomDoor) {
        super("Room");
        CreateRoom(difficulty, leftDoor, rightDoor, topDoor, bottomDoor);
    }

    public Room(int difficulty, boolean leftDoor, boolean rightDoor, boolean topDoor, boolean bottomDoor, boolean isSpawnRoom) {
        super("Room");
        this.isSpawnRoom = isSpawnRoom;
        CreateRoom(difficulty, leftDoor, rightDoor, topDoor, bottomDoor);
    }

    public Room(int difficulty, boolean leftDoor, boolean rightDoor, boolean topDoor, boolean bottomDoor, boolean isSpawnRoom, boolean isBossRoom, int x, int y) {
        super("Room");
        this.isSpawnRoom = isSpawnRoom;
        this.isBossRoom = isBossRoom;
        this.bossX = x;
        this.bossY = y;
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
        if(RoomManager.inTutorial)
            return;

        if(!isSpawnRoom && !isBossRoom)
            createEnemies(difficulty);

        if(isBossRoom)
        {
            createBoss();
        }
    }

    private void createEnemies(int difficulty) {
        for(int i = 0; i<difficulty; i++) {
            Vector3 pos = new Vector3(0, 300, 0);
            if (i == 0) {
                pos.x = 400;
            } else if (i == 1) {
                pos.x = 600;
            } else if (i == 2) {
                pos.x = 800;
            } else if (i == 3) {
                pos.x = 1000;
            } else if (i == 4)
            {
                pos.x = 400;
                pos.y = 450;
            }
            else if (i == 5)
            {
                pos.x = 600;
                pos.y = 450;
            }
            else if (i == 6)
            {
                pos.x = 800;
                pos.y = 450;
            }
            else if (i == 7)
            {
                pos.x = 1000;
                pos.y = 450;
            }
            int rand = GameMath.randRangeInclusive(0,1);
            if(rand == 0){
                add(new Follower(pos));
            }
            else if(rand == 1)
            {
                add(new Shooter(pos));
            }
        }
    }

    private void createBoss(){
        add(new Knight(new Vector3(1150-300,300), bossX,bossY));
    }

}
