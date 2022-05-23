package com.dungeoncrawler;

import com.JEngine.Core.GameObject;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.About.GameInfo;
import com.JEngine.Utility.Misc.GameUtility;
import com.dungeoncrawler.GameObjects.PlayerController;
import com.dungeoncrawler.Scenes.Rooms.Room;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.Scenes.MainMenu;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

public class Main extends Application {
    public static GameWindow window;
    private static Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        setupGameInfo();

        Main.stage = stage;
        stage.addEventHandler(KEY_PRESSED, (e) -> {
            if(e.getCode() == KeyCode.ESCAPE){
                GameUtility.exitApp();
            }
            if(e.getCode() == KeyCode.F1){
                createMenu();
                SceneManager.getActiveScene().remove(PlayerController.instance);
                PlayerController.instance = null;
            }
            if(e.getCode()==KeyCode.F2) {
                SceneManager.switchScene(new Room(1, true, true, true, true));
            }
        });
        window = new GameWindow(new GameScene("empty"), 1f, "Dungeon Crawler", stage);
        createMenu();
    }

    static void setupGameInfo(){
        GameInfo.appName = "Dungeon Crawler";
        GameInfo.appVersionMajor = 0;
        GameInfo.appVersionMinor = 1;
        GameInfo.authors = new String[]{"Noah Freelove"};
    }
    public static void createMenu(){
        window.setTargetFPS(30);
        MainMenu mainMenu = new MainMenu();
        GameCamera cam = new GameCamera(Vector3.emptyVector(), window, mainMenu, null, new Identity("MenuCam")); // Create the main menu camera
        SceneManager.switchScene(mainMenu, true);
        // Create our window
        window.setBackgroundColor(ColorManager.backgroundColor);
    }
}