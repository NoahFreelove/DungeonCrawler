package com.dungeoncrawler.Scenes;

import com.JEngine.Core.GameObject;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Components.UI.TextScroller;
import com.JEngine.Components.UI.UIAnimator;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.IO.FileOperations;
import com.JEngine.Utility.Misc.GameUtility;
import com.JEngine.Utility.Misc.GenericMethod;
import com.dungeoncrawler.Rooms.Room;
import com.dungeoncrawler.Rooms.RoomManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.File;

public class MainMenu extends GameScene {
    public MainMenu() {
        super(100, "Main Menu");
        createMenu();
    }

    private void createMenu(){
        GameObject animRoot = new GameObject(Transform.simpleTransform(Vector3.emptyVector()), new Identity("animRoot"));

        // Create title text
        Text titleText = new Text("Generic Dungeon Crawler");
        titleText.setStyle("-fx-font-size: 50px;");
        titleText.setFill(ColorManager.titleTextColor);
        titleText.setTranslateX(1280/2 - titleText.getLayoutBounds().getWidth()*2);
        titleText.setTranslateY(100);

        // Animate title text. Start pos, End pos, Start scale, end Scale, text ref, duration
        UIAnimator titleAnimator = new UIAnimator(
                new Vector2((float) (1280 / 2f - titleText.getLayoutBounds().getWidth() * 2), 300),
                new Vector2((float) (1280 / 2 - titleText.getLayoutBounds().getWidth() * 2), 100),
                new Vector2(0.5f, 0.5f),
                new Vector2(1, 1),
                titleText, 0.5f);
        titleAnimator.play();


        // Make title text scroll. Target Text, Text Ref, Duration, When done, create the menu buttons
        TextScroller scroller = new TextScroller("Generic Dungeon Crawler", titleText, 1, args -> createMenuButtons());
        scroller.play();

        // Create author text
        Text authorText = new Text("Made by Noah Freelove");
        authorText.setStyle("-fx-font-size: 13px;");
        authorText.setFill(ColorManager.titleTextColor);
        authorText.setTranslateX(640 - authorText.getLayoutBounds().getWidth()/1.5f);
        authorText.setTranslateY(650);

        // make author text scroll
        TextScroller authorScroller = new TextScroller("Made by Noah Freelove", authorText, 2);
        authorScroller.play();

        addUI(authorText);
        addUI(titleText);

        add(animRoot);
        animRoot.addComponent(scroller);
        animRoot.addComponent(authorScroller);
        animRoot.addComponent(titleAnimator);

    }

    private void createMenuButtons(){
        Button quitButton = new Button("Quit Game");
        quitButton.setMinWidth(400);
        quitButton.setTranslateX(440);
        quitButton.setTranslateY(400);

        quitButton.setOnAction(actionEvent -> GameUtility.exitApp());
        quitButton.setTextFill(ColorManager.buttonTextColor);
        quitButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(4) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");
        addUI(quitButton);

        Button newGameButton = new Button("Start New Game");
        newGameButton.setMinWidth(400);
        newGameButton.setTranslateX(440);
        newGameButton.setTranslateY(200);

        newGameButton.setOnAction(actionEvent -> {

        });
        newGameButton.setTextFill(ColorManager.buttonTextColor);
        newGameButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(4) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");
        addUI(newGameButton);

        if(new File("bin/save.dat").exists())
        {
            String[] saveData = FileOperations.fileToStringArr(new File("bin/save.dat").getAbsolutePath());
            Button loadButton = new Button("Load Game: " + saveData[0]);
            loadButton.setMinWidth(400);
            loadButton.setTranslateX(440);
            loadButton.setTranslateY(300);

            loadButton.setOnAction(actionEvent -> {
                try {
                    SceneManager.switchScene(RoomManager.rooms[0][0]);
                    SceneManager.getWindow().setTargetFPS(60);
                }catch (Exception e){}
            });
            loadButton.setTextFill(ColorManager.buttonTextColor);
            loadButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(4) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");
            addUI(loadButton);
        }
    }
}
