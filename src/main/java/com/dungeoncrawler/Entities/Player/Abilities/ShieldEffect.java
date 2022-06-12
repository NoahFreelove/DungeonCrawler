package com.dungeoncrawler.Entities.Player.Abilities;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;

public class ShieldEffect extends Pawn {
    float scale = 5;
    public ShieldEffect() {
        super(Transform.simpleTransform(new Vector3(600,350)), new GameImage("bin/images/shieldItem.png",64,64), new Identity("Shield"));
    }

    @Override
    public void Update(){
        setScale(new Vector3(scale,scale,scale));
        scale-=0.15;
        setPosition(new Vector3(600 - 32*scale, 350 - 32*scale));
        if(scale<=0.2)
        {
            SceneManager.getActiveScene().remove(this);
            setActive(false);
            setSprite(null);
        }
    }
}