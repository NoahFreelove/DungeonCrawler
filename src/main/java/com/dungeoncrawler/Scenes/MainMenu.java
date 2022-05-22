package com.dungeoncrawler.Scenes;

import com.JEngine.Core.GameObject;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.UI.TextScroller;
import com.JEngine.Game.Visual.UI.UIAnimator;
import javafx.scene.text.Text;

public class MainMenu extends GameScene {
    public MainMenu() {
        super(100, "Main Menu");
    }

    private void createMenu(){
        GameObject animRoot = new GameObject(Transform.simpleTransform(Vector3.emptyVector()), new Identity("animRoot"));
        Text titleText = new Text("Generic Dungeon Crawler");
        titleText.setStyle("-fx-font-size: 50px;");
        titleText.setFill(ColorManager.titleTextColor);
        titleText.setTranslateX(1280/2 - titleText.getLayoutBounds().getWidth()*2);
        titleText.setTranslateY(100);
        UIAnimator titleAnimator = new UIAnimator(
                new Vector2((float) (1280/2f - titleText.getLayoutBounds().getWidth()*2), 300),
                new Vector2((float) (1280/2 - titleText.getLayoutBounds().getWidth()*2),100),
                new Vector2(0.5f,0.5f),
                new Vector2(1,1),
                titleText, 0.5f);
        titleAnimator.play();

        TextScroller scroller = new TextScroller("Generic Dungeon Crawler", titleText, 1);
        scroller.play();


        Text authorText = new Text("Made by Noah Freelove");
        authorText.setStyle("-fx-font-size: 13px;");
        authorText.setFill(ColorManager.titleTextColor);
        authorText.setTranslateX(640 - authorText.getLayoutBounds().getWidth()/1.5f);
        authorText.setTranslateY(650);
        TextScroller authorScroller = new TextScroller("Made by Noah Freelove", authorText, 2);
        authorScroller.play();


        addUI(authorText);

        addUI(titleText);
        add(animRoot);

        animRoot.addComponent(scroller);
        animRoot.addComponent(authorScroller);
        animRoot.addComponent(titleAnimator);
    }

    @Override
    public void OnSceneActive(){
        this.purge();
        createMenu();
    }
}
