package com.dungeoncrawler.Entities.Player;

import com.JEngine.Core.GameObject;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Utility.Misc.GameTimer;
import com.JEngine.Utility.Misc.GenericMethod;

public class Ability extends GameObject {
    public Ability(Vector3 pos, double duration) {
        super(Transform.simpleTransform(pos), new Identity("ability"));
        GameTimer effectTimer = new GameTimer((long) duration * 1000, args -> EndEffect(), true);
        effectTimer.start();
    }

    protected void EndEffect(){    }
}
