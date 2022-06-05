package com.dungeoncrawler.Scenes;

import com.JEngine.Components.UI.TextScroller;
import com.JEngine.Components.UI.UIAnimator;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.About.GameInfo;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.IO.FileOperations;
import com.JEngine.Utility.Misc.GameUtility;
import com.dungeoncrawler.SaveManager;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
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
        GameWindow.getInstance().permanentUI.getChildren().clear();
        // Create title text
        Text titleText = new Text(GameInfo.getAppName());
        titleText.setStyle("-fx-font-size: 50px;");
        titleText.setFill(ColorManager.titleTextColor);
        titleText.setTranslateY(100);

        // Animate title text. Start pos, End pos, text ref, duration
        UIAnimator titleAnimator = new UIAnimator(
                new Vector2((float) (1280 / 2f - titleText.getLayoutBounds().getWidth() * 2)-10, 500),
                new Vector2((float) (1280 / 2 - titleText.getLayoutBounds().getWidth() * 2)-10, 100),
                titleText, 1);
        titleAnimator.play();


        // Make title text scroll. Target Text, Text Ref, Duration, When done, create the menu buttons
        TextScroller scroller = new TextScroller(GameInfo.getAppName(), titleText, 1, args -> createMenuButtons());
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
        quitButton.setTranslateY(500);

        quitButton.setOnAction(actionEvent -> GameUtility.exitApp());
        quitButton.setTextFill(ColorManager.buttonTextColor);
        quitButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");
        addUI(quitButton);

        Button newGameButton = new Button("Start New Game");
        newGameButton.setMinWidth(400);
        newGameButton.setTranslateX(440);
        newGameButton.setTranslateY(200);

        newGameButton.setOnAction(actionEvent -> {
            GameWindow.getInstance().setTargetFPS(60);
            SaveManager.newGame();
            RoomManager.CreateTutorial();
            SceneManager.switchScene(RoomManager.rooms[0][0]);

        });
        newGameButton.setTextFill(ColorManager.buttonTextColor);
        newGameButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");
        addUI(newGameButton);


        if(new File("bin/save.dat").exists())
        {
            String[] saveData = FileOperations.fileToStringArr(new File("bin/save.dat").getAbsolutePath());
            Button loadButton = new Button("Load Game: '" + saveData[0] + "'\n(Dungeon #" + saveData[1] + ")");
            loadButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            loadButton.setMinWidth(400);
            loadButton.setTranslateX(440);
            loadButton.setTranslateY(300);
            loadButton.setMaxWidth(400);
            loadButton.setOnAction(actionEvent -> {
                try {
                    int level = GameMath.clamp(1,100, Integer.parseInt(saveData[1]));
                    int size = 2 + level/4;

                    //RoomManager.CreateRooms(5 + level,5 + level, level);
                    RoomManager.CreateRooms(size,size, level);

                    GameWindow.getInstance().setTargetFPS(60);
                    SceneManager.switchScene(RoomManager.rooms[0][0]);

                }catch (Exception ignored){}
            });
            loadButton.setTextFill(ColorManager.buttonTextColor);
            loadButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");
            addUI(loadButton);

            Button skillPointsButton = new Button("Skill Points");
            skillPointsButton.setPrefWidth(200);
            skillPointsButton.setTranslateX(10);
            skillPointsButton.setTranslateY(600);

            skillPointsButton.setOnAction(actionEvent -> {
                SkillPointScreen skillPointScreen = new SkillPointScreen();
                SceneManager.switchScene(skillPointScreen);
            });
            skillPointsButton.setTextFill(ColorManager.buttonTextColor);
            skillPointsButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");
            addUI(skillPointsButton);
        }

    }
}
