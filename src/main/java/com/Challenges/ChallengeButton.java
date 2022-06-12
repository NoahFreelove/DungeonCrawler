package com.Challenges;

import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ChallengeButton extends Button {

    public ChallengeButton(int challengeIndex){
        setTranslateX(10+challengeIndex*100);
        setTranslateY(400);

        setText("Challenge " + challengeIndex);

        setOnMousePressed(mouseEvent -> {
            switch (challengeIndex) {
                case 0 -> ChallengeOne.Create();
            }
            SceneManager.switchScene(RoomManager.rooms[0][0], true);
        });
    }
}
