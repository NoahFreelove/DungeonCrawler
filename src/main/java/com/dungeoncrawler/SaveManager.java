package com.dungeoncrawler;

import com.JEngine.Utility.IO.FileOperations;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Scenes.MainMenu;

import java.io.File;

public class SaveManager {

    public static void saveGame(PlayerController player)
    {
        String[] saveData = new String[5];
        saveData[0] = player.getName();
        saveData[1] = "" + player.getGameLevel();
        saveData[2] = "" + player.getPlayerLevel();
        saveData[3] = "" + player.getGold();
        saveData[4] = "" + player.getExp();
        FileOperations.stringArrToFile(saveData, new File("bin/save.dat").getAbsolutePath());
    }

    public static void newGame(){
        String[] saveData = new String[5];
        saveData[0] = "PlayerName";
        saveData[1] = "1";
        saveData[2] = "1";
        saveData[3] = "0";
        saveData[4] = "0";
        FileOperations.stringArrToFile(saveData, new File("bin/save.dat").getAbsolutePath());
    }
}
