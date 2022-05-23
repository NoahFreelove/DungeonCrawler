package com.dungeoncrawler.Scenes.Rooms;

import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.About.GameInfo;
import com.dungeoncrawler.GameObjects.PlayerController;
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

    public static void CreateRooms(int width, int height, int overallDifficulty) {
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
                rooms[i][j] = new Room(randomNum, leftDoor, rightDoor, topDoor, bottomDoor);
            }
        }
        rooms[0][0].add(new PlayerController(new Vector3(200,300,0)));
        rooms[0][0].add(speechManager);
        speechManager.addSpeech(new SpeechStruct(SpeechType.NORMAL, String.format("Welcome to %s! Controls: WASD/Arrow Keys - Movement : ", GameInfo.getAppName()), 1.5f));
        speechManager.startSpeech();
    }

    public static void switchRoom(int deltaX, int deltaY) {
        speechManager.clear();

        if(currentRoomX+deltaX < width && currentRoomY+deltaY < height && currentRoomX+deltaX >= 0 && currentRoomY+deltaY >= 0) {
            currentRoomX = currentRoomX+deltaX;
            currentRoomY = currentRoomY+deltaY;
            SceneManager.switchScene(rooms[currentRoomX][currentRoomY]);
        }
        //System.out.println("Switched to room: " + currentRoomX + ", " + currentRoomY);
        speechManager.addSpeech(new SpeechStruct(SpeechType.NORMAL, "You have entered a room.", 0.5f));
        speechManager.startSpeech();
    }
}
