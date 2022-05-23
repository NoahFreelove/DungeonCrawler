package com.dungeoncrawler;

import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GameUtility;
import com.dungeoncrawler.Rooms.Room;
import com.dungeoncrawler.Rooms.RoomManager;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.Scenes.MainMenu;
import com.dungeoncrawler.UI.SpeechManager;
import com.dungeoncrawler.UI.SpeechStruct;
import com.dungeoncrawler.UI.SpeechType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
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
        Main.stage = stage;

        stage.addEventHandler(KEY_PRESSED, (e) -> {
            if(e.getCode() == KeyCode.ESCAPE){
                GameUtility.exitApp();
            }
            if(e.getCode() == KeyCode.F1){
                createMenu();
            }
            if(e.getCode()==KeyCode.F2)
            {
                SceneManager.switchScene(new Room(1, true, true, true, true));
            }

        });

        createMenu();

    }

    public static void createMenu(){
        MainMenu mainMenu = new MainMenu();
        window = new GameWindow(mainMenu, 1f, "Dungeon Crawler", stage);
        GameWindow.getInstance().setTargetFPS(30);

        GameCamera cam = new GameCamera(Vector3.emptyVector(), window, mainMenu, null, new Identity("MenuCam")); // Create the main menu camera
        SceneManager.switchScene(mainMenu);
        // Create our window
        window.setBackgroundColor(ColorManager.backgroundColor);
        window.setTargetFPS(30);
        RoomManager.CreateRooms(10,10);
    }
}