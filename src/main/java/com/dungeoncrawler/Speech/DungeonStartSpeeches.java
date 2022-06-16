package com.dungeoncrawler.Speech;

import com.dungeoncrawler.Entities.Player.PlayerController;

public record DungeonStartSpeeches() {
    public static final SpeechStruct[] speeches = new SpeechStruct[]{null,
        new SpeechStruct(SpeechType.NORMAL, "You're " + PlayerController.gameLevelToWin + " floors below the surface. Find a way out.", 3),
            new SpeechStruct(SpeechType.NORMAL, "As you progress through the dungeon, stronger enemies will appear and the dungeon will increase in size...", 3),
            new SpeechStruct(SpeechType.NORMAL, "On floor 14 you will be able to use super abilities to help you with future levels...", 3),
            new SpeechStruct(SpeechType.NORMAL, "The shop will offer discounts every 5 floors. Take advantage of these deals...", 3),
            new SpeechStruct(SpeechType.NORMAL, "You should keep boss weapons as they can be very powerful... or don't.", 3), // 5
            new SpeechStruct(SpeechType.NORMAL, "A RED health-bar means that an enemy has < 50 HP, BLUE is > 50HP, GREEN is > 100HP, and YELLOW is >150HP", 3),
            new SpeechStruct(SpeechType.NORMAL, "The Doctor Boss will heal up to 150HP if you don't stop him from doing so.", 3),
            new SpeechStruct(SpeechType.NORMAL, "Skeletons spawn in pairs because everyone needs a friend", 3),
            new SpeechStruct(SpeechType.NORMAL, "The Graveyard boss won't try to attack you, but instead let her minions take over", 3),
            new SpeechStruct(SpeechType.NORMAL, "Frogs will jump half the distance to you every leap. Don't get too close!", 3), // 10
            new SpeechStruct(SpeechType.NORMAL, "Enemies will sometimes dodge the Staff's magic. This is 100% a feature", 3),
            new SpeechStruct(SpeechType.NORMAL, "The sword is one of the best melee weapons.", 1),
            new SpeechStruct(SpeechType.NORMAL, "Every time you level up, you obtain one skill point. Redeem these in the skill point menu for buffs", 3),
            new SpeechStruct(SpeechType.NORMAL, "Completing the base game will unlock challenges for you to play. Check them out!", 3),
            new SpeechStruct(SpeechType.NORMAL, "I wonder if the player can talk...", 1), // 15
            new SpeechStruct(SpeechType.NORMAL, "The Doctor boss will drop health when he dies. If you're good at the game you shouldn't need it though ;)", 3), // 16
            new SpeechStruct(SpeechType.NORMAL, "What if this dungeon was endless and didn't end at floor 0", 1),
    };
}
