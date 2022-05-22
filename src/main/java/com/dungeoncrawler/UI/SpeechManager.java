package com.dungeoncrawler.UI;

import com.JEngine.Components.DontDestroyOnLoad_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Game.PlayersAndPawns.Player;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.SceneManager;

import com.dungeoncrawler.Scenes.ColorManager;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class SpeechManager extends Player {
    private final ArrayList<SpeechStruct> speechQueue;

    private final Text activeText;
    private SpeechStruct activeSpeech;
    private TextScroller activeScroller;

    private final GameImage normalBackground = new GameImage("bin/speechBackground.png");
    public SpeechManager() {
        super(Transform.simpleTransform(0,400,0), new GameImage("bin/speechBackground.png"), new Identity("SpeechManager"));
        activeText = new Text();
        activeText.setTranslateX(0);
        activeText.setTranslateY(getPosition().y + 60);
        activeText.setWrappingWidth(1280*GameWindow.getInstance().getScaleMultiplier());
        activeText.setStyle("-fx-font-size: 40px;");

        addComponent(new DontDestroyOnLoad_Comp());
        speechQueue = new ArrayList<>(0);
    }

    public void addSpeech(SpeechStruct speech)
    {
        speechQueue.add(speech);
    }

    public void speak(){
        if(speechQueue != null){

            if(!SceneManager.getActiveScene().uiObjects.getChildren().contains(activeText))
            {
                SceneManager.getActiveScene().addUI(activeText);
            }

            activeSpeech = speechQueue.get(0);

            switch (activeSpeech.type)
            {
                case BOSS -> activeText.setFill(ColorManager.bossTextColor);
                case PLAYER -> activeText.setFill(ColorManager.playerTextColor);
                case NORMAL -> activeText.setFill(ColorManager.textColor);
            }
            activeScroller = new TextScroller(activeSpeech.text, activeText,activeSpeech.duration, args -> onComplete());
            SceneManager.getActiveScene().add(activeScroller);
            activeScroller.play();
        }
    }

    private void removeFirst(){
        speechQueue.remove(0);
    }

    private void onComplete(){
        activeSpeech.skipable = true;
    }

    private void next(){
        removeFirst();
        if(speechQueue.size() > 0)
        {
            setSprite(normalBackground);
            activeText.setVisible(true);
            speak();
        }
        else
        {
            setSprite(null);
            activeText.setVisible(false);
            System.out.println("No more speeches");
        }
    }

    @Override
    public void onKeyReleased(KeyCode keyCode)
    {
        if(keyCode == KeyCode.SPACE)
        {
            if(activeSpeech != null)
            {
                if(activeSpeech.skipable)
                {
                    next();
                }
            }
        }
    }
}
