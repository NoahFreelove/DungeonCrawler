package com.dungeoncrawler.Scenes.Rooms;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.IO.FileOperations;
import com.JEngine.Utility.Misc.Do;
import com.dungeoncrawler.Entities.Enemies.Bosses.Doctor;
import com.dungeoncrawler.Entities.Enemies.Bosses.Graveyard;
import com.dungeoncrawler.Entities.Enemies.Bosses.Knight;
import com.dungeoncrawler.Entities.Enemies.Bosses.Turret;
import com.dungeoncrawler.Entities.Enemies.Follower;
import com.dungeoncrawler.Entities.Enemies.Frog;
import com.dungeoncrawler.Entities.Enemies.Shooter;
import com.dungeoncrawler.Entities.Enemies.Skeleton;
import com.dungeoncrawler.Entities.Shop;

import java.io.File;

public class Room extends GameScene {
    boolean isSpawnRoom;
    boolean isBossRoom;
    boolean isShopRoom;
    int xPos;
    int yPos;
    int enemyCount = 0;
    public Room(int difficulty, boolean leftDoor, boolean rightDoor, boolean topDoor, boolean bottomDoor, RoomType roomType, int x, int y) {
        super(200,"Room");
        this.isSpawnRoom = (roomType == RoomType.SPAWN);
        this.isBossRoom = (roomType == RoomType.BOSS);
        this.isShopRoom = (roomType == RoomType.SHOP);
        this.xPos = x;
        this.yPos = y;
        GameImage floor = new GameImage("bin/floor.png", 1280,720);
        floor.setTiled(true);
        floor.setTileSizeX(128);
        floor.setTileSizeY(128);
        add(new Sprite(Transform.simpleTransform(0,0,-500),floor, new Identity("floor")));
        CreateRoom(difficulty, leftDoor, rightDoor, topDoor, bottomDoor);
        setEnableLighting(true);
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

        if(!isSpawnRoom && !isBossRoom && !isShopRoom)
            createEnemies(difficulty);

        if(isBossRoom)
        {
            createBoss();
            enemyCount++;
        }
        if(isShopRoom)
        {
            createShop();
        }
    }

    private void createEnemies(int difficulty) {
        int i = 0;
        while(i<(difficulty/4 +1)){
            enemyCount++;
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
            int rand = GameMath.randRangeInclusive(0,3);
            if(rand == 0){
                add(new Follower(pos));
            }
            else if(rand == 1)
            {
                add(new Shooter(pos));
            }
            else if(rand == 2)
            {
                add(new Frog(pos));
            }
            else if (rand == 3)
            {
                // Skeletons are weak, so spawn them in pairs
                add(new Skeleton(pos, false));
                add(new Skeleton(pos.add(50),false));
                enemyCount++;
            }
            i++;
        }
    }

    private void createBoss(){
        int rand = GameMath.randRangeInclusive(0,0);
        //System.out.println(rand);
        Vector3 bossPos = new Vector3(1150-300,300);
        switch (rand)
        {
            case 0 -> add(new Doctor(bossPos, xPos, yPos));
            case 1 -> add(new Graveyard(bossPos, xPos, yPos));
            case 2 -> add(new Knight(bossPos, xPos, yPos));
            case 3 -> add(new Turret(bossPos, xPos, yPos));
        }
    }

    private void createShop(){
        String[] saveData = FileOperations.fileToStringArr(new File("bin/save.dat").getAbsolutePath());
        int level = GameMath.clamp(1,100, Integer.parseInt(saveData[1]));
        add(new Shop(this, level));
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void removeEnemy(){
        enemyCount--;
        if(RoomManager.inTutorial)
            return;
        RoomManager.checkIfDungeonClear();
    }
}
