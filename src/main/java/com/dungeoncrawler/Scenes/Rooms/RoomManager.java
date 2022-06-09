package com.dungeoncrawler.Scenes.Rooms;

import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.About.GameInfo;
import com.JEngine.Utility.IO.FileOperations;
import com.JEngine.Utility.Misc.GameTimer;
import com.dungeoncrawler.Entities.Enemies.*;
import com.dungeoncrawler.Entities.Enemies.Bosses.Boss;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Entities.Stairs.Stairs;
import com.dungeoncrawler.Main;
import com.dungeoncrawler.SaveManager;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.Scenes.MainMenu;
import com.dungeoncrawler.Speech.SpeechManager;
import com.dungeoncrawler.Speech.SpeechStruct;
import com.dungeoncrawler.Speech.SpeechType;

import java.io.File;

public class RoomManager {
    public static Room[][] rooms;
    public static int currentRoomX = 0;
    public static int currentRoomY = 0;
    public static int width;
    public static int height;
    public static SpeechManager speechManager;
    public static boolean inTutorial = false;
    private static Enemy tutorialEnemy = new Follower(new Vector3(0,0,0));
    public static Room currentRoom;

    public static void CreateRooms(int width, int height, int overallDifficulty) {
        currentRoomY = 0;
        currentRoomX = 0;
        inTutorial = false;
        if (overallDifficulty <= 0)
            overallDifficulty = 1;

        RoomManager.speechManager = new SpeechManager();
        RoomManager.width = width;
        RoomManager.height = height;

        rooms = new Room[width][height];

        // generate one random boss room
        int bossX = (int) (Math.random() * (width));
        int bossY = (int) (Math.random() * (height));

        if (bossX == 0 && bossY == 0)
            bossX = 1;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                int randomNum = (int) (Math.random() * overallDifficulty) + 1;

                boolean leftDoor = true;
                boolean rightDoor = true;
                boolean topDoor = true;
                boolean bottomDoor = true;
                if(i == 0) {
                    leftDoor = false;
                }
                if(i == width - 1) {
                    rightDoor = false;
                }
                if(j == 0) {
                    bottomDoor = false;
                }
                if(j == height - 1) {
                    topDoor = false;
                }
                if(i == width/2 && j == height/2)
                {
                    rooms[i][j] = new Room(randomNum, leftDoor, rightDoor, topDoor, bottomDoor, RoomType.SHOP, i,j);
                    System.out.println("Shop Room: " + i + ", " + j);
                }
                else if(i == 0 && j == 0) {
                    rooms[0][0] = new Room(randomNum, false, rightDoor, topDoor, false, RoomType.SPAWN, i,j);
                }
                else if(i == bossX && j == bossY) {
                    rooms[i][j] = new Room(randomNum, leftDoor, rightDoor, topDoor, bottomDoor, RoomType.BOSS, i,j);
                    System.out.println("Boss Room: " + i + ", " + j);
                }
                else {
                    rooms[i][j] = new Room(randomNum, leftDoor, rightDoor, topDoor, bottomDoor, RoomType.NORMAL, i,j);
                }
            }
        }
        String[] saveData = FileOperations.fileToStringArr(new File("bin/save.dat").getAbsolutePath());
        rooms[0][0].add(new PlayerController(new Vector3(200,300,0), saveData[0], Integer.parseInt(saveData[1]),
                Integer.parseInt(saveData[2]), Integer.parseInt(saveData[3]), Integer.parseInt(saveData[4]), saveData[6]));
        rooms[0][0].add(speechManager);
        SceneManager.getWindow().setBackgroundColor(ColorManager.backgroundColor);
        currentRoom = rooms[currentRoomX][currentRoomY];
    }

    public static void CreateTutorial(){
        currentRoomY = 0;
        currentRoomX = 0;
        inTutorial = true;
        RoomManager.speechManager = new SpeechManager();

        RoomManager.width = 2;
        RoomManager.height = 2;
        rooms = new Room[width][height];
        rooms[0][0] = new Room(0, false, false, true, false, RoomType.NORMAL,0,0);
        rooms[0][1] = new Room(0, false, true, false, true, RoomType.NORMAL,0,1);
        rooms[1][1] = new Room(0, true, false, false, false, RoomType.NORMAL,1,1);

        rooms[0][0].add(new PlayerController(new Vector3(200,300,0)));

        rooms[0][0].add(speechManager);
        Frog firstEnemy = new Frog(new Vector3(1150-300,300));
        tutorialEnemy = firstEnemy;
        //firstEnemy.setCanAttack(false);
        rooms[0][1].add(firstEnemy);
        rooms[1][1].add(new Stairs());

        speechManager.addSpeech(new SpeechStruct(SpeechType.NORMAL, String.format("Welcome to %s!\nControls: Move using WASD/Arrow Keys", GameInfo.getAppName()), 1.5f, true));
        speechManager.startSpeech();
        SceneManager.getWindow().setBackgroundColor(ColorManager.backgroundColor);
    }

    public static void switchRoom(int deltaX, int deltaY) {
        speechManager.clear();

        if(currentRoomX+deltaX < width && currentRoomY+deltaY < height && currentRoomX+deltaX >= 0 && currentRoomY+deltaY >= 0) {
            currentRoomX = currentRoomX+deltaX;
            currentRoomY = currentRoomY+deltaY;
            initEnemies();
            SceneManager.switchScene(rooms[currentRoomX][currentRoomY]);
        }

        tutorialSpeechCheck();
        currentRoom = rooms[currentRoomX][currentRoomY];
    }

    private static void tutorialSpeechCheck(){
        if(inTutorial) {
            if (currentRoomX == 0 && currentRoomY == 0) {
                speechManager.addSpeech(new SpeechStruct(SpeechType.NORMAL, String.format("Welcome to %s!\nControls: Move using WASD/Arrow Keys", GameInfo.getAppName()), 1.5f, true));
            }
            if (currentRoomX == 0 && currentRoomY == 1) {
                speechManager.addSpeech(new SpeechStruct(SpeechType.NORMAL, String.format("You can use Shift to attack using a selected weapon. This %s does %d damage a hit.\nThis %s has %d health",
                        PlayerController.instance.getSelectedWeapon().getClass().getSimpleName(),(int)
                                PlayerController.instance.getSelectedWeapon().getDamage(), tutorialEnemy.getClass().getSimpleName(), (int)tutorialEnemy.getHealth()
                ), 1.5f, false));
            }
            if (currentRoomX == 1 && currentRoomY == 1) {
                speechManager.addSpeech(new SpeechStruct(SpeechType.IMPORTANT, "You can find better weapons by killing bosses and buying them with gold\n" +
                        "You can also heal by purchasing or finding health packs. Pickup/Interact with objects with Control", 1.5f, false));
            }

            speechManager.startSpeech();
        }
    }

    private static void initEnemies(){
        for (GameObject go: rooms[currentRoomX][currentRoomY].getObjects()) {
            if(go instanceof Enemy enemy)
            {
                if(enemy.isQueuedForDeletion() || !enemy.getActive())
                    continue;
                enemy.setPosition(enemy.getStartPos());
                enemy.neutralize();
                GameTimer moveDelay = new GameTimer(500, args -> enemy.activate(rooms[currentRoomX][currentRoomY]), true);
                moveDelay.start();
            }
            if(go instanceof EnemyProjectile ep)
            {
                ep.onHit();
                rooms[currentRoomX][currentRoomY].remove(ep);
            }
            if(go instanceof Boss boss)
            {
                boss.startBattle();
            }
        }
    }

    public static void checkIfDungeonClear() {
        boolean clear = true;
        for (Room[] roomArr : rooms) {
            for (Room room : roomArr) {
                if (room.enemyCount > 0) {
                    clear = false;
                    break;
                }
            }
        }
        if (clear)
        {
            rooms[currentRoomX][currentRoomY].add(new Stairs());
        }
    }

    public static void endDungeon(boolean loadNextOne) {
        if (PlayerController.instance == null)
            return;
        SaveManager.saveGame(PlayerController.instance, true);
        PlayerController.removePlayer();
        if (loadNextOne)
        {
            MainMenu.loadGame();
        }
        else {
            Main.createMainMenu();
        }
    }
}
