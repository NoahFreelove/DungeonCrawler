package com.dungeoncrawler;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Utility.ImageProcessing.GenerateSolidTexture;
import com.JEngine.Utility.ImageProcessing.MissingTexture;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.Scenes.MainMenu;
import com.dungeoncrawler.UI.SpeechManager;
import com.dungeoncrawler.UI.SpeechStruct;
import com.dungeoncrawler.UI.SpeechType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static SpeechManager speechManager;
    @Override
    public void start(Stage stage) {
        MainMenu mainMenu = new MainMenu(); // Creating a new MainMenu will restart all the animations and that good stuff

        GameWindow window = new GameWindow(mainMenu, 1f, "Dungeon Crawler", stage); // Create our window
        new GameCamera(Vector3.emptyVector(), window, mainMenu, null, new Identity("MenuCam")); // Create the main menu camera
        window.setBackgroundColor(ColorManager.backgroundColor);

       setupSpeechManager();
       mainMenu.add(speechManager);

    }

    static void setupSpeechManager(){
        speechManager = new SpeechManager();
        speechManager.addSpeech(new SpeechStruct(SpeechType.BOSS, "This is boss text!", 2));
        speechManager.addSpeech(new SpeechStruct(SpeechType.NORMAL, "This is normal text!", 2));
        speechManager.speak();
    }

    public static void main(String[] args) {
        launch();
    }
}