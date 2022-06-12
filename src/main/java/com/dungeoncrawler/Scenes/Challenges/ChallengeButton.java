package com.dungeoncrawler.Scenes.Challenges;

import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
import javafx.scene.control.Button;

public class ChallengeButton extends Button {

    public ChallengeButton(int challengeIndex){
        setTranslateY(200 + 100*(challengeIndex/6));
        setTranslateX(50+(challengeIndex%6)*200);

        setPrefWidth(150);
        setPrefHeight(75);
        setTextFill(ColorManager.buttonTextColor);
        setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent;");
        setText("Challenge " + (challengeIndex+1));

        setOnMousePressed(mouseEvent -> {
            switch (challengeIndex) {
                case 0 -> ChallengeOne.Create();
                case 1 -> ChallengeOne.Create();
                case 2 -> ChallengeOne.Create();
                case 3 -> ChallengeOne.Create();
                case 4 -> ChallengeOne.Create();
                case 5 -> ChallengeOne.Create();
                case 6 -> ChallengeOne.Create();
                case 7 -> ChallengeOne.Create();
                case 8 -> ChallengeOne.Create();
                case 9 -> ChallengeOne.Create();
                case 10 -> ChallengeOne.Create();
                case 11 -> ChallengeOne.Create();
            }
            SceneManager.switchScene(RoomManager.rooms[0][0], true);
        });
    }
}
