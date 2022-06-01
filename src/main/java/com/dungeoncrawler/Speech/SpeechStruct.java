package com.dungeoncrawler.Speech;

public class SpeechStruct {
    public SpeechType type;
    public String text;
    public float duration;
    public boolean skipable;

    public SpeechStruct(SpeechType type, String text, float duration) {
        this.type = type;
        this.text = text;
        this.duration = duration;
        this.skipable = true;
    }

    public SpeechStruct(SpeechType type, String text, float duration, boolean skipable) {
        this.type = type;
        this.text = text;
        this.duration = duration;
        this.skipable = skipable;
    }

}
