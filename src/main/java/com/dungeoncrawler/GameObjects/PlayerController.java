package com.dungeoncrawler.GameObjects;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Game.PlayersAndPawns.Player;

public class PlayerController extends Player {
    public PlayerController(Transform transform, GameImage newSprite, Identity Identity) {
        super(transform, newSprite, Identity);
    }
}
