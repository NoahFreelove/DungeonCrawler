package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
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
    public Boss(Vector3 initPos, GameImage newSprite, double damage, double health, double attackDelay, int difficulty, String startSpeech, float speechDuration) {
        super(initPos, newSprite, damage, health, attackDelay, difficulty);
        this.speechStruct = new SpeechStruct(SpeechType.BOSS, startSpeech, speechDuration);
    }


    public void startBattle(){
        if(hasFought)
            return;
        hasFought = true;
        SceneManager.getActiveScene().add(leftWall);
        SceneManager.getActiveScene().add(rightWall);
        SceneManager.getActiveScene().add(topWall);
        SceneManager.getActiveScene().add(bottomWall);
        RoomManager.speechManager.addSpeech(speechStruct);

    }

    @Override
    protected void onDeath(){
        SceneManager.getActiveScene().remove(leftWall);
        SceneManager.getActiveScene().remove(rightWall);
        SceneManager.getActiveScene().remove(topWall);
        SceneManager.getActiveScene().remove(bottomWall);

        super.onDeath();
    }
}
