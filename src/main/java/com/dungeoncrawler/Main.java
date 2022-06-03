package com.dungeoncrawler;

import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.About.GameInfo;
import com.JEngine.Utility.Misc.GameUtility;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.Scenes.MainMenu;
import com.dungeoncrawler.Scenes.Rooms.Room;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

public class Main extends Application {

    public static GameWindow window;
    public static Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            setupGameInfo();

            Main.stage = stage;
            stage.addEventHandler(KEY_PRESSED, (e) -> {
                if(e.getCode() == KeyCode.ESCAPE){
                    GameUtility.exitApp();
                }
                if(e.getCode() == KeyCode.F1){
                    createMainMenu();
                    SceneManager.getActiveScene().remove(PlayerController.instance);
                    PlayerController.instance = null;
                }
                if(e.getCode()==KeyCode.F2) {
                    SceneManager.switchScene(new Room(1, true, true, true, true));
                }
            });
            window = new GameWindow(new GameScene("empty"), 1f, GameInfo.getAppName(), stage);
            createMainMenu();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    static void setupGameInfo(){
        GameInfo.setAppName("Dungeon Crawler");
        GameInfo.setAppVersionMajor(0);
        GameInfo.setAppVersionMinor(1);
        GameInfo.setAuthors(new String[]{"Noah Freelove"});
    }

    public static void createMainMenu(){
        window.setTargetFPS(30);
        MainMenu mainMenu = new MainMenu();
        new GameCamera(Vector3.emptyVector(), window, mainMenu, null, new Identity("MenuCam")); // Create the main menu camera
        SceneManager.switchScene(mainMenu, true);
        // Create our window
        window.setBackgroundColor(ColorManager.mainMenuColor);
    }
}