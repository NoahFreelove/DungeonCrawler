package com.dungeoncrawler.Entities.Player;

import com.JEngine.Game.Visual.GameWindow;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;

public class PlayerUI {
    private Group playerUI = new Group();
    private Text roomNumber;
    private Text healthText;
    private Text goldText;
    private Text xpText;
    private Text gameLevelText;


    public PlayerUI(double health, int gold, int playerLevel, int exp, int expToNextLevel, int gameLevel) {
        roomNumber = new Text();
        roomNumber.setTranslateX(10);
        roomNumber.setTranslateY(40);
        roomNumber.setFill(ColorManager.textColor);
        roomNumber.setStyle("-fx-font-family: 'Arial';-fx-font-size: 25px;");

        ImageView healthIcon = new ImageView(new File("bin/heart.png").getAbsolutePath());
        healthIcon.setFitHeight(32);
        healthIcon.setFitWidth(32);
        healthIcon.setLayoutX(15);
        healthIcon.setLayoutY(410);

        healthText = new Text(""+ health);

        healthText.setTranslateX(10);
        healthText.setTranslateY(470);
        healthText.setFill(ColorManager.boldText);
        Font font = Font.font("Arial", FontWeight.BOLD, 25);
        healthText.setFont(font);
        healthText.setStroke(Color.WHITE);
        healthText.setStrokeWidth(1);
        goldText = new Text("Gold: "+ gold);
        goldText.setTranslateX(320);
        goldText.setTranslateY(40);
        goldText.setFill(ColorManager.textColor);
        goldText.setStyle("-fx-font-family: 'Arial';-fx-font-size: 25px;");

        xpText = new Text(String.format("Level %d (%d/%d)", playerLevel, exp, expToNextLevel));
        xpText.setTranslateX(1080-250);
        xpText.setTranslateY(40);
        xpText.setFill(ColorManager.boldText);
        xpText.setStyle("-fx-font-family: 'Arial';-fx-font-size: 25px;");

        gameLevelText = new Text("Floor "+ (PlayerController.gameLevelToWin - gameLevel));
        gameLevelText.setTranslateX(1080-10);
        gameLevelText.setTranslateY(40);
        gameLevelText.setFill(ColorManager.textColor);
        gameLevelText.setStyle("-fx-font-family: 'Arial';-fx-font-size: 25px;");

        playerUI.getChildren().add(healthText);
        playerUI.getChildren().add(roomNumber);
        playerUI.getChildren().add(goldText);
        playerUI.getChildren().add(xpText);
        playerUI.getChildren().add(gameLevelText);

        GameWindow.getInstance().addPermanentUI(healthText);
        GameWindow.getInstance().addPermanentUI(healthIcon);
        GameWindow.getInstance().addPermanentUI(playerUI);
        GameWindow.getInstance().addPermanentUI(goldText);
        GameWindow.getInstance().addPermanentUI(xpText);
        GameWindow.getInstance().addPermanentUI(gameLevelText);
    }

    public void UpdateUI(double health, int gold, int playerLevel, int exp, int expToNextLevel, int gameLevel)
    {
        roomNumber.setText("Room " + RoomManager.currentRoomX + ":" + RoomManager.currentRoomY);
        healthText.setText(""+ (int)(health));
        goldText.setText("Gold: " + gold);
        xpText.setText(String.format("Level %d (%d/%d)", playerLevel, exp, expToNextLevel));
        gameLevelText.setText("Floor "+ (PlayerController.gameLevelToWin - gameLevel));
    }
}
