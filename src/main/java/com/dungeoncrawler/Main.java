package com.dungeoncrawler;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.About.GameInfo;
import com.JEngine.Utility.Misc.GameUtility;
import com.dungeoncrawler.Entities.Player.Abilities.Shield;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.Scenes.MainMenu;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

public class Main extends Application {

    public static GameWindow window;
    public static Stage stage;
    public static boolean lightingOffset = false; // some machines have issues with position based lights, so this is a hacky fix

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            setupGameInfo();

            Main.stage = stage;
            stage.addEventHandler(KEY_PRESSED, (e) -> {
                if (e.getCode() == KeyCode.ESCAPE) {

                    // Player instance is only null on the main menu
                    if(PlayerController.instance == null)
                    {
                        GameUtility.exitApp();
                        return;
                    }

                    if(Pause.isPaused)
                    {
                        Pause.UnPauseGame();
                    }
                    else{
                        Pause.PauseGame();
                    }
                }

                if (e.getCode() == KeyCode.F1) {
                    PlayerController.removePlayer();
                    createMainMenu();
                }
                if(e.getCode() == KeyCode.F2)
                {
                    PlayerController.instance.addShield(new Shield(10));
                }
            });

            window = new GameWindow(new GameScene("empty"), 1f, GameInfo.getAppName(), stage);
            window.setIcon(new GameImage("bin/images/icon.png",64,64));
            createMainMenu();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + " (start)");
        }
    }

    static void setupGameInfo(){
        GameInfo.setAppName("Dungeon Crawler");
        GameInfo.setAppVersionMajor(0);
        GameInfo.setAppVersionMinor(5);
        GameInfo.setAuthors(new String[]{"Noah Freelove"});
    }

    public static void createMainMenu(){
        window.setTargetFPS(30);
        MainMenu mainMenu = new MainMenu();

        new GameCamera(Vector3.emptyVector(), window, mainMenu, null, new Identity("MenuCam"));
        SceneManager.switchScene(mainMenu, true);
        window.setBackgroundColor(ColorManager.mainMenuColor);
    }
}