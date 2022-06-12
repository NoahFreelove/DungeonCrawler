package com.dungeoncrawler;

import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GameUtility;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.Scenes.MainMenu;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Pause {

    private static Group pauseUI = new Group();
    private static Group parent = SceneManager.getWindow().parent;
    private static boolean hasInit;
    public static boolean isPaused;
    public static void InitPauseMenu(){
        Text pausedText = new Text("Paused");
        pausedText.setFill(Color.WHITE);
        pausedText.setFont(new Font("Arial", 25));
        pausedText.setX(640-pausedText.getLayoutBounds().getWidth()/2);
        pausedText.setY(300);
        pauseUI.getChildren().add(pausedText);


        Button quitButton = new Button("Quit Game");
        quitButton.setMinWidth(300);

        quitButton.setOnAction(actionEvent -> GameUtility.exitApp());
        quitButton.setTextFill(Color.WHITE);
        quitButton.setStyle("-fx-background-color: #461111; -fx-focus-color: transparent; -fx-font-size: 30px;");
        quitButton.setTranslateX(640-150);
        quitButton.setTranslateY(520);
        pauseUI.getChildren().add(quitButton);

        Button quitToMainMenu = new Button("Quit To Main Menu");
        quitToMainMenu.setMinWidth(300);
        quitToMainMenu.setOnAction(actionEvent -> {
            Pause.UnPauseGame();
            PlayerController.removePlayer();
            Main.createMainMenu();
        });
        quitToMainMenu.setTextFill(Color.WHITE);
        quitToMainMenu.setStyle("-fx-background-color: #804f1e; -fx-focus-color: transparent; -fx-font-size: 30px;");
        quitToMainMenu.setTranslateX(640-150);
        quitToMainMenu.setTranslateY(420);
        pauseUI.getChildren().add(quitToMainMenu);

        Button resumeButton = new Button("Resume");
        resumeButton.setMinWidth(300);

        resumeButton.setOnAction(actionEvent -> Pause.UnPauseGame());
        resumeButton.setTextFill(Color.WHITE);
        resumeButton.setStyle("-fx-background-color: #184611; -fx-focus-color: transparent; -fx-font-size: 30px;");
        resumeButton.setTranslateX(640-150);
        resumeButton.setTranslateY(320);
        pauseUI.getChildren().add(resumeButton);
    }

    public static void PauseGame(){
        if(!hasInit)
        {
            InitPauseMenu();
            hasInit = true;
        }
        if(PlayerController.instance !=null)
        {
            SceneManager.getWindow().pause();
            if(!parent.getChildren().contains(pauseUI))
                parent.getChildren().add(pauseUI);

            isPaused = true;
        }
    }

    public static void UnPauseGame(){
        SceneManager.getWindow().resume();
        parent.getChildren().remove(pauseUI);
        isPaused = false;
    }
}
