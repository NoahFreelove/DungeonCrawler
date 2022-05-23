package com.dungeoncrawler;

import com.JEngine.Utility.IO.FileOperations;
import com.dungeoncrawler.GameObjects.PlayerController;

import java.io.File;

public class SaveManager {

    public static void saveGame(PlayerController player)
    {
        String[] saveData = new String[5];
        saveData[0] = player.getName();
        saveData[1] = "" + player.getLevel();
        saveData[2] = "" + player.getGold();
        saveData[3] = "" + player.getExp();
        saveData[4] = "" + player.getHealth();
        saveData[5] = "" + player.getRoomsCleared();
        FileOperations.stringArrToFile(saveData, new File("bin/save.dat").getAbsolutePath());
    }
}
