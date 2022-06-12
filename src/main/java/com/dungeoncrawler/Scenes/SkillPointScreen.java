package com.dungeoncrawler.Scenes;

import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Utility.IO.FileOperations;
import com.dungeoncrawler.Main;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.File;

public class SkillPointScreen extends GameScene {

    private static int skillPoints = 0;
    private static Text pointText = new Text("Points: ");

    private static Text meleeDamageText = new Text("Melee Damage Multiplier: ");
    private static Button mDamageIncrease = new Button("+");
    private static Button mDamageDecrease = new Button("-");

    private static Text meleeSpeedText = new Text("Melee Attack Speed Multiplier: ");
    private static Button mSpeedIncrease = new Button("+");
    private static Button mSpeedDecrease = new Button("-");

    private static Text projectileDamageText = new Text("Projectile Damage Multiplier: ");
    private static Button pDamageIncrease = new Button("+");
    private static Button pDamageDecrease = new Button("-");

    private static Text projectileSpeedText = new Text("Projectile Attack Speed Multiplier: ");
    private static Button pSpeedIncrease = new Button("+");
    private static Button pSpeedDecrease = new Button("-");

    private static double[] skills;

    public SkillPointScreen() {
        super("Game Scene");
        if (!new File("bin/save/save.dat").exists()) {
            return;
        }
        String[] skillPointData = FileOperations.fileToStringArr(new File("bin/save/skills.dat").getAbsolutePath());

        skills = new double[]{Double.parseDouble(skillPointData[0]),Double.parseDouble(skillPointData[1]),
                Double.parseDouble(skillPointData[2]),Double.parseDouble(skillPointData[3])};

        String[] saveData = FileOperations.fileToStringArr(new File("bin/save/save.dat").getAbsolutePath());
        skillPoints = Integer.parseInt(saveData[5]);

        pointText.setText(skillPoints + " Skill Point" + ((skillPoints==1)? "" : "s"));
        pointText.setTranslateX(10);
        pointText.setTranslateY(40);
        pointText.setFill(ColorManager.textColor);
        pointText.setStyle("-fx-font-family: 'Arial';-fx-font-size: 25px;");

        createMeleeSkillUI();
        createProjectileSkillUI();

        Button returnButton = new Button("Return");
        returnButton.setPrefWidth(200);
        returnButton.setTranslateX(10);
        returnButton.setTranslateY(600);

        returnButton.setOnAction(actionEvent -> {
            Main.createMainMenu();
        });
        returnButton.setTextFill(ColorManager.buttonTextColor);
        returnButton.setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent; -fx-font-size: 30px;");


        addUI(returnButton, pointText, meleeDamageText, mDamageIncrease, mDamageDecrease, meleeSpeedText, mSpeedIncrease, mSpeedDecrease, projectileDamageText, pDamageIncrease, pDamageDecrease, projectileSpeedText, pSpeedIncrease, pSpeedDecrease);
    }

    public static void buy(int skillIndex){
        if(skillPoints >= 1)
        {
            skillPoints--;
            skills[skillIndex]+=0.1;
            save();
            updateText();
        }
    }

    public static void refund(int skillIndex)
    {
        if(skills[skillIndex] > 1)
        {
            skillPoints++;
            skills[skillIndex]-=0.1;
            save();
            updateText();
            if(skills[skillIndex] <1)
                skills[skillIndex] = 1;
        }
    }

    private static void save(){
        String[] saveData = FileOperations.fileToStringArr(new File("bin/save/save.dat").getAbsolutePath());
        saveData[5] = "" + skillPoints;
        FileOperations.stringArrToFile(saveData, new File("bin/save/save.dat").getAbsolutePath());
        FileOperations.stringArrToFile(new String[]{"" +skills[0], "" +skills[1],"" +skills[2],"" +skills[3]}, new File("bin/save/skills.dat").getAbsolutePath());

    }

    private static void updateText(){
        pointText.setText(skillPoints + " Skill Point" + ((skillPoints==1)? "" : "s"));
        meleeDamageText.setText(String.format("Melee Damage Multiplier: %.1f", skills[0]));
        meleeSpeedText.setText(String.format("Melee Attack Speed Multiplier: %.1f", skills[1]));
        projectileDamageText.setText(String.format("Projectile Damage Multiplier: %.1f", skills[2]));
        projectileSpeedText.setText(String.format("Projectile Speed / Fire-rate Multiplier: %.1f", skills[3]));
    }

    private void createMeleeSkillUI(){
        meleeDamageText.setText(String.format("Melee Damage Multiplier: %.1f", skills[0]));
        meleeDamageText.setStyle("-fx-font-family: 'Arial';-fx-font-size: 25px;");
        meleeDamageText.setTranslateX(10);
        meleeDamageText.setTranslateY(100);
        meleeDamageText.setFill(ColorManager.textColor);

        mDamageIncrease = new SkillPointButton(true, 350,70,0);
        mDamageDecrease = new SkillPointButton(false, 400,70,0);

        meleeSpeedText.setText(String.format("Melee Attack Speed Multiplier: %.1f", skills[1]));
        meleeSpeedText.setStyle("-fx-font-family: 'Arial';-fx-font-size: 25px;");
        meleeSpeedText.setTranslateX(10);
        meleeSpeedText.setTranslateY(200);
        meleeSpeedText.setFill(ColorManager.textColor);

        mSpeedIncrease = new SkillPointButton(true, 400,170,1);
        mSpeedDecrease = new SkillPointButton(false, 450,170,1);
    }

    private void createProjectileSkillUI(){
        projectileDamageText.setText(String.format("Projectile Damage Multiplier: %.1f", skills[2]));
        projectileDamageText.setStyle("-fx-font-family: 'Arial';-fx-font-size: 25px;");
        projectileDamageText.setTranslateX(10);
        projectileDamageText.setTranslateY(300);
        projectileDamageText.setFill(ColorManager.textColor);

        pDamageIncrease = new SkillPointButton(true, 400,270,2);
        pDamageDecrease = new SkillPointButton(false, 450,270,2);

        projectileSpeedText.setText(String.format("Projectile Speed / Fire-rate Multiplier: %.1f", skills[3]));
        projectileSpeedText.setStyle("-fx-font-family: 'Arial';-fx-font-size: 25px;");
        projectileSpeedText.setTranslateX(10);
        projectileSpeedText.setTranslateY(400);
        projectileSpeedText.setFill(ColorManager.textColor);

        pSpeedIncrease = new SkillPointButton(true, 500,370,3);
        pSpeedDecrease = new SkillPointButton(false, 550,370,3);
    }
}
