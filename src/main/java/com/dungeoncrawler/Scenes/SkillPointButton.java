package com.dungeoncrawler.Scenes;

import javafx.scene.control.Button;

import static com.dungeoncrawler.Scenes.SkillPointScreen.buy;
import static com.dungeoncrawler.Scenes.SkillPointScreen.refund;

public class SkillPointButton extends Button {

    public SkillPointButton(boolean increase, double x, double y, int index){
        setTranslateX(x);
        setTranslateY(y);

        if(increase)
        {
            setText("+");
            setOnAction(actionEvent -> {
                buy(index);
            });

        }
        else {
            setText("-");
            setOnAction(actionEvent -> {
                refund(index);
            });
        }
        setPrefWidth(40);
        setPrefHeight(40);
        setTextFill(ColorManager.buttonTextColor);
        setStyle("-fx-background-color: #" + ColorManager.buttonColor.toString().substring(2) + "; -fx-focus-color: transparent;");
    }
}
