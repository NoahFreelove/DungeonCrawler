package com.dungeoncrawler.Entities.Player.Abilities;

import com.JEngine.Core.GameObject;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Enemies.Enemy;
import com.dungeoncrawler.Entities.Player.PlayerController;
import javafx.scene.paint.Color;

import static com.dungeoncrawler.Entities.Player.Abilities.AbilityStats.FIRE_DAMAGE;

public class FireAbility extends Ability {

    public FireAbility(){
        super(PlayerController.instance.getPosition(), 3, Color.RED);    }

    @Override
    protected void StartEffect(){


        for (GameObject object: SceneManager.getActiveScene().getObjects()) {
            if(object instanceof Enemy enemy){
                enemy.takeDamage(FIRE_DAMAGE);
            }
        }
    }
    @Override
    protected void EndEffect(){
        super.EndEffect();
    }

}
