package com.dungeoncrawler.Speech;

import com.dungeoncrawler.Entities.Player.PlayerController;

public record DungeonStartSpeeches() {
    public static final SpeechStruct[] speeches = new SpeechStruct[]{null,
        new SpeechStruct(SpeechType.NORMAL, "You're " + PlayerController.gameLevelToWin + " floors below the surface. Find a way out.", 3),
            new SpeechStruct(SpeechType.IMPORTANT, "As you progress through the dungeon, stronger enemies will appear and the dungeon will increase in size...", 3),
            new SpeechStruct(SpeechType.NORMAL, "The shop will offer discounts every 5 floors. Take advantage of these deals...", 3),
    };
}
