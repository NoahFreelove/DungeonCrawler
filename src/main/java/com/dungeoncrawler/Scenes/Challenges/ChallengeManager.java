package com.dungeoncrawler.Scenes.Challenges;

import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Main;
import com.dungeoncrawler.Scenes.ColorManager;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ChallengeManager extends GameScene {

    public ChallengeManager(){
        super("Challenge Manager");
        SceneManager.getWindow().setTargetFPS(60);

        Text titleText = new Text("Challenges");
        titleText.setStyle("-fx-font-size: 50px;");
        titleText.setFill(ColorManager.titleTextColor);
        titleText.setTranslateY(100);
        titleText.setTranslateX(500);

        Button returnButton = new Button("Return");
        returnButton.setPrefWidth(200);
        returnButton.setTranslateX(10);
        returnButton.setTranslateY(600);

        returnButton.setOnAction(actionEvent -> {
            Main.createMainMenu();
        });
        returnButton.setTextFill(ColorManager.buttonTextColor);
        returnButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");

        addUI(titleText,returnButton,new ChallengeButton(0), new ChallengeButton(1), new ChallengeButton(2),new ChallengeButton(3),new ChallengeButton(4),new ChallengeButton(5),new ChallengeButton(6),new ChallengeButton(7),
        new ChallengeButton(8),new ChallengeButton(9),new ChallengeButton(10), new ChallengeButton(11));
    }
}
