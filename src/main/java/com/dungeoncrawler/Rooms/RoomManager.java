package com.dungeoncrawler.Rooms;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.ImageProcessing.MissingTexture;
import com.dungeoncrawler.GameObjects.PlayerController;

public class RoomManager {
    public static Room[][] rooms;
    public static int currentRoomX;
    public static int currentRoomY;
    public static int width;
    public static int height;
    public static void CreateRooms(int width, int height) {
        RoomManager.width = width;
        RoomManager.height = height;

        rooms = new Room[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // create random num 1-15
                int randomNum = (int) (Math.random() * 15) + 1;
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
        rooms[0][0].add(new PlayerController(new Vector3(200,300,0), new GameImage(MissingTexture.getMissingTextureImage(64,64))));

    }

    public static void switchRoom(int deltaX, int deltaY) {
        SceneManager.getWindow().setTargetFPS(30);

        if(currentRoomX+deltaX < width && currentRoomY+deltaY < height && currentRoomX+deltaX >= 0 && currentRoomY+deltaY >= 0) {
            currentRoomX = currentRoomX+deltaX;
            currentRoomY = currentRoomY+deltaY;
            SceneManager.switchScene(rooms[currentRoomX][currentRoomY]);
        }
        System.out.println("Switched to room: " + currentRoomX + ", " + currentRoomY);
        SceneManager.getWindow().setTargetFPS(60);

    }
}
