package com.dungeoncrawler.Entities.Enemies.Bosses;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Enemies.Enemy;
import com.dungeoncrawler.Scenes.Rooms.Room;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
import com.dungeoncrawler.Scenes.Rooms.Wall;
import com.dungeoncrawler.Speech.SpeechStruct;
import com.dungeoncrawler.Speech.SpeechType;

public class Boss extends Enemy {
    private Wall leftWall = new Wall(new Vector2(0,0), new Vector2(1, 11));
    private Wall rightWall = new Wall(new Vector2(1200,0), new Vector2(1, 11));
    private Wall topWall = new Wall(new Vector2(0,0), new Vector2(20, 1));
    private Wall bottomWall = new Wall(new Vector2(0,620), new Vector2(20, 1));
    private boolean hasFought;
    private SpeechStruct speechStruct;
    private int xRoom;
    private int yRoom;
    public Boss(Vector3 initPos, GameImage newSprite, double damage, double health, double attackDelay, int difficulty, String startSpeech, float speechDuration, int roomX, int roomY) {
        super(initPos, newSprite, damage, health, attackDelay, difficulty);
        this.speechStruct = new SpeechStruct(SpeechType.BOSS, startSpeech, speechDuration);
        this.xRoom = roomX;
        this.yRoom = roomY;
    }


    public void startBattle(){
        if(hasFought)
            return;
        hasFought = true;
        RoomManager.rooms[xRoom][yRoom].add(leftWall);
        RoomManager.rooms[xRoom][yRoom].add(rightWall);
        RoomManager.rooms[xRoom][yRoom].add(topWall);
        RoomManager.rooms[xRoom][yRoom].add(bottomWall);
        RoomManager.speechManager.addSpeech(speechStruct);
        RoomManager.speechManager.startSpeech();
        battleInit();

    }
    protected void battleInit(){}
    @Override
    protected void onDeath(){
        RoomManager.rooms[xRoom][yRoom].remove(leftWall);
        RoomManager.rooms[xRoom][yRoom].remove(rightWall);
        RoomManager.rooms[xRoom][yRoom].remove(topWall);
        RoomManager.rooms[xRoom][yRoom].remove(bottomWall);

        super.onDeath();
    }

}
