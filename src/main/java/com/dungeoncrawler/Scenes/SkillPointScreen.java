package com.dungeoncrawler.Scenes;

import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Utility.IO.FileOperations;
import com.dungeoncrawler.Main;
import javafx.scene.text.Text;

import java.io.File;

public class SkillPointScreen extends GameScene {

    private int skillPoints = 0;
    private Text pointText = new Text("Points: ");

    public SkillPointScreen() {
        super("Game Scene");
        if (!new File("bin/save.dat").exists()) {
            return;
        }
        String[] saveData = FileOperations.fileToStringArr(new File("bin/save.dat").getAbsolutePath());
        skillPoints = Integer.parseInt(saveData[5]);

        pointText.setText(skillPoints + " Skill Point" + ((skillPoints==1)? "" : "s"));
        pointText.setTranslateX(10);
        pointText.setTranslateY(40);
        pointText.setFill(ColorManager.textColor);
        pointText.setStyle("-fx-font-family: 'Arial';-fx-font-size: 25px;");

        addUI(pointText);
    }

    private boolean buy(int price){
        if(skillPoints >= price)
        {
            skillPoints-=price;
            pointText.setText(skillPoints + " Skill Point" + ((skillPoints==1)? "" : "s"));
            return true;
        }
        return false;
    }
}
