package com.Challenges;

import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.Scenes.Rooms.Room;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
import com.dungeoncrawler.Scenes.Rooms.RoomType;
import com.dungeoncrawler.Speech.SpeechManager;

public class ChallengeOne {
    public static void Create(){
        RoomManager.currentRoomY = 0;
        RoomManager.currentRoomX = 0;
        RoomManager.inChallenge = true;
        RoomManager.speechManager = new SpeechManager();

        RoomManager.width = 4;
        RoomManager.height = 1;
        RoomManager.rooms = new Room[RoomManager.width][RoomManager.height];
        RoomManager.rooms[0][0] = new Room(0, false, true, false, false, RoomType.SPAWN,0,0);
        RoomManager.rooms[1][0] = new Room(0, true, true, false, false, RoomType.SPAWN,1,0);
        RoomManager.rooms[2][0] = new Room(0, true, true, false, false, RoomType.SPAWN,2,0);
        RoomManager.rooms[3][0] = new Room(0, true, true, false, false, RoomType.SPAWN,3,0);

        RoomManager.rooms[0][0].add(new PlayerController(new Vector3(200,300,0)));

        RoomManager.rooms[0][0].add(RoomManager.speechManager);

        SceneManager.getWindow().setBackgroundColor(ColorManager.backgroundColor);
    }
}
