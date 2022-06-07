package com.dungeoncrawler.Speech;

import com.dungeoncrawler.Entities.Player.PlayerController;

public record DungeonStartSpeeches() {
    public static final SpeechStruct[] speeches = new SpeechStruct[]{null,
        new SpeechStruct(SpeechType.NORMAL, "You're " + PlayerController.gameLevelToWin + " floors below the surface. Find a way out.", 3)
    };
}
