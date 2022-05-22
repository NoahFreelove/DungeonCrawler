package com.dungeoncrawler.UI;

import com.JEngine.Core.GameObject;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Utility.Misc.GenericMethod;
import javafx.scene.text.Text;

public class TextScroller extends GameObject {

    private Text animText;
    private String content;
    private int index = 0;
    private float timeTarget;
    private float progress = 0f;
    private GenericMethod onComplete;
    private boolean isPlaying;

    public TextScroller(String content, Text text, float time, GenericMethod onComplete) {
        super(Transform.simpleTransform(Vector3.emptyVector()), new Identity("textScroll"));
        this.animText = text;
        this.timeTarget = time;
        this.content = content;
        this.onComplete = onComplete;
    }
    public TextScroller(String content, Text text, float time) {
        super(Transform.simpleTransform(Vector3.emptyVector()), new Identity("textScroll"));
        this.animText = text;
        this.timeTarget = time;
        this.content = content;
        this.onComplete = null;
    }

    public void play(){
        isPlaying = true;
    }

    public void pause(){
        isPlaying = false;
    }

    public void stop(){
        pause();
        animText.setText("");
        progress = 0;
    }

    public void restart(){
        stop();
        play();
    }
    public void skip(){
        progress = 1f;
        if(onComplete != null)
            onComplete.call(null);
    }

    @Override
    public void Update(){
        if(isPlaying)
        {
            if(progress < 1)
            {
                progress += 1/ GameWindow.getInstance().getTargetFPS()/timeTarget;
                index = (int) (progress * content.length());
                animText.setText(content.substring(0, index));
            }
            else {
                pause();
                if(onComplete !=null)
                {
                    onComplete.call(null);
                }
            }
        }
    }

    public float getProgress() {
        return progress;
    }
}
