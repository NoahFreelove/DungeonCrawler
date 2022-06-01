package com.dungeoncrawler.UI;

import com.JEngine.Components.DontDestroyOnLoad_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Game.PlayersAndPawns.Player;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.SceneManager;

import com.JEngine.Components.UI.TextScroller;
import com.dungeoncrawler.Scenes.ColorManager;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class SpeechManager extends Player {
    private final ArrayList<SpeechStruct> speechQueue;
    private final GameImage background = new GameImage("bin/speechBackground.png");

    private final Text activeText;
    private SpeechStruct activeSpeech;
    private TextScroller activeScroller;
    private boolean textCanBeSkipped;

    public SpeechManager() {
        super(Transform.simpleTransform(0,600,0), null, new Identity("SpeechManager"));

        activeText = new Text();
        activeText.setTranslateX(30);
        activeText.setTranslateY(getPosition().y + 30);
        activeText.setWrappingWidth(1280*GameWindow.getInstance().getScaleMultiplier());

        // make text pixelated
        activeText.setStyle("-fx-font-family: 'Arial';-fx-font-size: 25px;");

        addComponent(new DontDestroyOnLoad_Comp());
        speechQueue = new ArrayList<>(0);
        GameWindow.getInstance().addPermanentUI(activeText);

    }

    public void addSpeech(SpeechStruct speech)
    {
        speechQueue.add(speech);
    }

    private void speak(){
        if(speechQueue != null){


            activeSpeech = speechQueue.get(0);

            switch (activeSpeech.type)
            {
                case BOSS -> activeText.setFill(ColorManager.bossTextColor);
                case PLAYER -> activeText.setFill(ColorManager.playerTextColor);
                case NORMAL -> activeText.setFill(ColorManager.textColor);
                case IMPORTANT -> activeText.setFill(ColorManager.importantText);
            }
            textCanBeSkipped = activeSpeech.skipable;
            if(activeScroller !=null)
            {
                activeScroller.stop();
            }
            activeScroller = new TextScroller(activeSpeech.text, activeText,activeSpeech.duration, args -> onComplete());
            addComponent(activeScroller);
            activeScroller.play();
        }
    }

    private void removeFirst(){
        speechQueue.remove(0);
    }

    private void onComplete(){
        textCanBeSkipped = true;
    }

    private void next(){
        removeFirst();
        startSpeech();
    }

    public void startSpeech(){
        if(speechQueue.size() > 0)
        {
            setSprite(background);
            activeText.setVisible(true);
            speak();
        }
        else
        {
            setSprite(null);
            activeText.setVisible(false);
        }
    }
    public void clear(){
        speechQueue.clear();
        startSpeech();
    }

    @Override
    public void onKeyReleased(KeyCode keyCode)
    {
        if(keyCode == KeyCode.SPACE)
        {
            if(activeSpeech != null)
            {
                if(textCanBeSkipped)
                {
                    if(activeScroller.getProgress() < 1)
                    {
                        activeScroller.skip();
                        return;
                    }
                    next();
                }
            }
        }
    }

    @Override
    public void Update(){
        super.Update();
    }
}
