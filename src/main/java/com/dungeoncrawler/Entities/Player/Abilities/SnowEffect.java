package com.dungeoncrawler.Entities.Player.Abilities;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Direction;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;

public class SnowEffect extends Pawn {
    public SnowEffect(float xPos) {
        super(Transform.simpleTransform(new Vector3(xPos,0)), new GameImage("bin/images/snowflake.png",64,64), new Identity("Snow"));
    }

    @Override
    public void Update(){
        Move(Direction.Down, 15);
        Rotate(new Vector2(1,0), 15,true);
        if(getPosition().y > 800){
            setActive(false);
            SceneManager.getActiveScene().remove(this);
        }
    }
}
