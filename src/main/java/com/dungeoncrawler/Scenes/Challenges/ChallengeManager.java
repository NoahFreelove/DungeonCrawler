package com.dungeoncrawler.Scenes.Challenges;

import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.IO.FileOperations;
import com.dungeoncrawler.Main;
import com.dungeoncrawler.Scenes.ColorManager;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.File;

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

        addUI(titleText,returnButton);

        int challengeLevel = Integer.parseInt(FileOperations.fileToStringArr(new File("bin/save/permdata.dat").getAbsolutePath())[1]);

        for (int i = 0; i < GameMath.clamp(0,12,challengeLevel); i++) {
            addUI(new ChallengeButton(i));
        }
    }
    public static void CompleteChallenge(){
        String[] permData = FileOperations.fileToStringArr(new File("bin/save/permdata.dat").getAbsolutePath());
        int challengeLevel = Integer.parseInt(permData[1]);
        challengeLevel+=1;
        FileOperations.stringArrToFile(new String[]{permData[0], "" + challengeLevel}, new File("bin/save/permdata.dat").getAbsolutePath());
    }
}
