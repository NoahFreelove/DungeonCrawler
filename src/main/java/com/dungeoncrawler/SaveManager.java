package com.dungeoncrawler;

import com.JEngine.Utility.IO.FileOperations;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Scenes.MainMenu;

import java.io.File;

public class SaveManager {

    public static void saveGame(PlayerController player)
    {
        saveGame(player, false);
    }

    public static void saveGame(PlayerController player, boolean incrementLevel)
    {
        int gameLevel = player.getGameLevel();
        if(incrementLevel)
        {
            gameLevel++;
        }
        String[] saveData = new String[9];
        saveData[0] = player.getName();
        saveData[1] = "" + gameLevel;
        saveData[2] = "" + player.getPlayerLevel();
        saveData[3] = "" + player.getGold();
        saveData[4] = "" + player.getExp();
        saveData[5] = "" + player.getSkillPoints();
        saveData[6] = "" + player.getSelectedWeapon().getClass().getSimpleName();
        saveData[7] = "" + player.getSuperAbility().toString();
        saveData[8] = "" + player.getSuperCharge();

        FileOperations.stringArrToFile(saveData, new File("bin/save/save.dat").getAbsolutePath());
        FileOperations.stringArrToFile(new String[]{"" + player.hasBeatGame}, new File("bin/save/permdata.dat").getAbsolutePath());

    }

    public static void newGame(){
        String[] saveData = new String[9];
        saveData[0] = "PlayerName";
        saveData[1] = "1";
        saveData[2] = "1";
        saveData[3] = "0";
        saveData[4] = "0";
        saveData[5] = "0";
        saveData[6] = "Sword";
        saveData[7] = "NONE";
        saveData[8] = "0";

        FileOperations.stringArrToFile(saveData, new File("bin/save/save.dat").getAbsolutePath());
        FileOperations.stringArrToFile(new String[]{"false"}, new File("bin/save/permdata.dat").getAbsolutePath());

    }
}
