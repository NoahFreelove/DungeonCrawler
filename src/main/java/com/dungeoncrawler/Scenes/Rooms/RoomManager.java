package com.dungeoncrawler.Scenes.Rooms;

import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.About.GameInfo;
import com.JEngine.Utility.Misc.GameTimer;
import com.JEngine.Utility.Misc.GenericMethod;
import com.dungeoncrawler.GameObjects.Enemy;
import com.dungeoncrawler.GameObjects.Follower;
import com.dungeoncrawler.GameObjects.PlayerController;
import com.dungeoncrawler.GameObjects.Weapons.Melee.Sword;
import com.dungeoncrawler.GameObjects.Weapons.Projectile.BarrettM82;
import com.dungeoncrawler.GameObjects.Weapons.Projectile.Bow;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.UI.SpeechManager;
import com.dungeoncrawler.UI.SpeechStruct;
import com.dungeoncrawler.UI.SpeechType;

public class RoomManager {
    public static Room[][] rooms;
    public static int currentRoomX = 0;
    public static int currentRoomY = 0;
    public static int width;
    public static int height;
    public static SpeechManager speechManager;
    private static boolean inTutorial = false;

    public static void CreateRooms(int width, int height, int overallDifficulty) {
        currentRoomY = 0;
        currentRoomX = 0;
        inTutorial = false;
        if(overallDifficulty <= 0)
            overallDifficulty = 1;

        RoomManager.speechManager = new SpeechManager();
        RoomManager.width = width;
        RoomManager.height = height;

        rooms = new Room[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // create random num 1-15
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
                if(i == 0 && j == 0) {
                    rooms[0][0] = new Room(randomNum, false, rightDoor, topDoor, false, true);
                    System.out.println("spawn room");
                }
                else {
                    rooms[i][j] = new Room(randomNum, leftDoor, rightDoor, topDoor, bottomDoor);
                }
            }
        }
        rooms[0][0].add(new PlayerController(new Vector3(200,300,0)));
        PlayerController.instance.setSelectedWeapon(new Bow(PlayerController.instance.getPosition()));

        rooms[0][0].add(speechManager);
        SceneManager.getWindow().setBackgroundColor(ColorManager.backgroundColor);
    }

    public static void CreateTutorial(){
        currentRoomY = 0;
        currentRoomX = 0;
        inTutorial = true;
        RoomManager.speechManager = new SpeechManager();

        RoomManager.width = 2;
        RoomManager.height = 2;
        rooms = new Room[2][2];
        rooms[0][0] = new Room(0, false, false, true, false);
        rooms[0][1] = new Room(0, false, true, false, true);
        rooms[1][1] = new Room(0, true, false, false, false);

        rooms[0][0].add(new PlayerController(new Vector3(200,300,0)));
        PlayerController.instance.setSelectedWeapon(new BarrettM82(PlayerController.instance.getPosition()));

        rooms[0][0].add(speechManager);
        Follower firstEnemy = new Follower(new Vector3(1150-300,300));
        //firstEnemy.setCanAttack(false);
        rooms[0][1].add(firstEnemy);
        speechManager.addSpeech(new SpeechStruct(SpeechType.NORMAL, String.format("Welcome to %s!\nControls: Move using WASD/Arrow Keys", GameInfo.getAppName()), 1.5f, true));
        speechManager.startSpeech();
        SceneManager.getWindow().setBackgroundColor(ColorManager.backgroundColor);
    }

    public static void switchRoom(int deltaX, int deltaY) {
        speechManager.clear();

        if(currentRoomX+deltaX < width && currentRoomY+deltaY < height && currentRoomX+deltaX >= 0 && currentRoomY+deltaY >= 0) {
            currentRoomX = currentRoomX+deltaX;
            currentRoomY = currentRoomY+deltaY;
            SceneManager.switchScene(rooms[currentRoomX][currentRoomY]);
            for (GameObject go: rooms[currentRoomX][currentRoomY].getObjects()) {
                if(go instanceof Enemy enemy)
                {
                    enemy.setPosition(enemy.getStartPos());
                    enemy.neutralize();
                    GameTimer moveDelay = new GameTimer(500, args -> enemy.activate(), true);
                    moveDelay.start();
                }
            }
        }
        //System.out.println("Switched to room: " + currentRoomX + ", " + currentRoomY);
        //speechManager.addSpeech(new SpeechStruct(SpeechType.NORMAL, "You have entered a room.", 0.5f));
        //speechManager.startSpeech();

        if(inTutorial) {
            if (currentRoomX == 0 && currentRoomY == 0) {
                speechManager.addSpeech(new SpeechStruct(SpeechType.NORMAL, String.format("Welcome to %s!\nControls: Move using WASD/Arrow Keys", GameInfo.getAppName()), 1.5f, false));
            }
            if (currentRoomX == 0 && currentRoomY == 1) {
                speechManager.addSpeech(new SpeechStruct(SpeechType.NORMAL, "You can use Shift to attack using a selected weapon. This sword does 10 damage a hit.\nThis enemy has 10 health", 1.5f, false));
            }
            if (currentRoomX == 1 && currentRoomY == 1) {
                speechManager.addSpeech(new SpeechStruct(SpeechType.IMPORTANT, "You can find better weapons by killing bosses and buying them with gold\n" +
                        "You can also heal by purchasing or finding health packs", 1.5f, false));
            }

            speechManager.startSpeech();
        }
    }
}
