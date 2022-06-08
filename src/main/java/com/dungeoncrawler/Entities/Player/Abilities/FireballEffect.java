package com.dungeoncrawler.Entities.Player.Abilities;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Direction;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;

public class FireballEffect extends Pawn {
    public FireballEffect(float yPos) {
        super(Transform.simpleTransform(new Vector3(1080,yPos)), new GameImage("bin/fireball.png",128,64), new Identity("Fireball"));
    }

    @Override
    public void Update(){
        Move(Direction.Left, 25);
        if(getPosition().x < -50){
            setActive(false);
            SceneManager.getActiveScene().remove(this);
        }
    }
}
