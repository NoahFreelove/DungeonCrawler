package com.dungeoncrawler.Scenes;

import com.dungeoncrawler.Scenes.Challenges.ChallengeManager;
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
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Main;
import com.dungeoncrawler.SaveManager;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
import com.dungeoncrawler.Speech.DungeonStartSpeeches;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.File;

public class MainMenu extends GameScene {
    private boolean enableAnimation = false;

    public MainMenu() {
        super(100, "Main Menu");
        if(!new File("bin/save/permdata.dat").exists())
        {
            FileOperations.stringArrToFile(new String[]{"false"},new File("bin/save/permData.dat").getAbsolutePath());
        }
        else {
            String[] permData = FileOperations.fileToStringArr(new File("bin/save/permdata.dat").getAbsolutePath());
            if(Boolean.parseBoolean(permData[0]))
            {
                Button skillPointsButton = new Button("Challenges");
                skillPointsButton.setPrefWidth(200);
                skillPointsButton.setTranslateX(10);
                skillPointsButton.setTranslateY(500);

                skillPointsButton.setOnAction(actionEvent -> {
                    SceneManager.switchScene(new ChallengeManager(), true);
                });
                skillPointsButton.setTextFill(ColorManager.buttonTextColor);
                skillPointsButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");
                addUI(skillPointsButton);
            }
        }
        if(!new File("bin/save/skills.dat").exists())
        {
            FileOperations.stringArrToFile(new String[]{"1", "1", "1", "1"},new File("bin/save/skills.dat").getAbsolutePath());
        }
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
        if(enableAnimation)
        {
            titleAnimator.play();
        }
        else{
            titleAnimator.skip();
        }
        // Make title text scroll. Target Text, Text Ref, Duration, When done, create the menu buttons
        TextScroller scroller = new TextScroller(GameInfo.getAppName(), titleText, 1, args -> createMenuButtons());
        if(enableAnimation)
        {
            scroller.play();
        }
        else {
            scroller.skip();
        }
        // Create author text
        Text authorText = new Text("Made by " + GameInfo.getAuthors()[0]);
        authorText.setStyle("-fx-font-size: 13px;");
        authorText.setFill(ColorManager.titleTextColor);
        authorText.setTranslateX(640 - authorText.getLayoutBounds().getWidth()/1.5f);
        authorText.setTranslateY(650);

        // make author text scroll
        TextScroller authorScroller = new TextScroller("Made by Noah Freelove", authorText, 2);
        if(enableAnimation) authorScroller.play();
        else authorScroller.skip();


        addUI(authorText,titleText);

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
        newGameButton.setTranslateY(400);

        newGameButton.setOnAction(actionEvent -> {
            GameWindow.getInstance().setTargetFPS(60);
            SaveManager.newGame();
            RoomManager.CreateTutorial();
            SceneManager.switchScene(RoomManager.rooms[0][0]);

        });
        newGameButton.setTextFill(ColorManager.buttonTextColor);
        newGameButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");
        addUI(newGameButton);


        if(new File("bin/save/save.dat").exists())
        {
            String[] saveData = FileOperations.fileToStringArr(new File("bin/save/save.dat").getAbsolutePath());
            Button loadButton = new Button("Load Game: '" + saveData[0] + "'\n(Floor " + (PlayerController.gameLevelToWin+1-Integer.parseInt(saveData[1])) + ")");
            loadButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            loadButton.setMinWidth(400);
            loadButton.setTranslateX(440);
            loadButton.setTranslateY(250);
            loadButton.setMaxWidth(400);
            loadButton.setOnAction(actionEvent -> {
                loadGame();
            });
            loadButton.setTextFill(ColorManager.buttonTextColor);
            loadButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");
            addUI(loadButton);

            Button skillPointsButton = new Button("Skill Points");
            skillPointsButton.setPrefWidth(200);
            skillPointsButton.setTranslateX(10);
            skillPointsButton.setTranslateY(600);

            skillPointsButton.setOnAction(actionEvent -> {
                SceneManager.switchScene(new SkillPointScreen(), true);
            });
            skillPointsButton.setTextFill(ColorManager.buttonTextColor);
            skillPointsButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");
            addUI(skillPointsButton);
        }

        Button purgeData = new Button("Purge Save Data");
        purgeData.setPrefWidth(300);
        purgeData.setTranslateX(950);
        purgeData.setTranslateY(600);

        purgeData.setOnAction(actionEvent -> {
            purgeSave();
        });
        purgeData.setTextFill(ColorManager.buttonTextColor);
        purgeData.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");
        addUI(purgeData);

    }

    public static void loadGame(){
        String[] saveData = FileOperations.fileToStringArr(new File("bin/save/save.dat").getAbsolutePath());
        try {
            int level = GameMath.clamp(1,100, Integer.parseInt(saveData[1]));
            int size = 2 + level/4;

            //RoomManager.CreateRooms(5 + level,5 + level, level);
            RoomManager.CreateRooms(size,size, level);

            GameWindow.getInstance().setTargetFPS(60);
            SceneManager.switchScene(RoomManager.rooms[0][0]);
            RoomManager.speechManager.addSpeech(DungeonStartSpeeches.speeches[GameMath.clamp(1, DungeonStartSpeeches.speeches.length-1,level)]);
            RoomManager.speechManager.startSpeech();

        }catch (Exception ignored){}
    }

    public static void purgeSave() {
        FileOperations.stringArrToFile(new String[]{"false"}, new File("bin/save/permData.dat").getAbsolutePath());
        FileOperations.stringArrToFile(new String[]{"1", "1", "1", "1"}, new File("bin/save/skills.dat").getAbsolutePath());
        new File("bin/save/save.dat").delete();
        Main.createMainMenu();
    }
}
