package com.dungeoncrawler.Speech;

import com.dungeoncrawler.Entities.Player.PlayerController;

public record DungeonStartSpeeches() {
    public static final SpeechStruct[] speeches = new SpeechStruct[]{null,
        new SpeechStruct(SpeechType.NORMAL, "You're " + PlayerController.gameLevelToWin + " floors below the surface. Find a way out.", 3),
            new SpeechStruct(SpeechType.IMPORTANT, "As you progress through the dungeon, stronger enemies will appear and the dungeon will increase in size...", 3),
            new SpeechStruct(SpeechType.IMPORTANT, "On floor 14 you will be able to use super abilities to help you with future levels...", 3),
            new SpeechStruct(SpeechType.NORMAL, "The shop will offer discounts every 5 floors. Take advantage of these deals...", 3),
            new SpeechStruct(SpeechType.NORMAL, "You should keep boss weapons as they can be very powerful... or don't.", 3),
            new SpeechStruct(SpeechType.NORMAL, "A RED health-bar means that an enemy has < 50 HP, BLUE is > 50HP, GREEN is > 100HP, and YELLOW is >150HP", 3),

    };
}
