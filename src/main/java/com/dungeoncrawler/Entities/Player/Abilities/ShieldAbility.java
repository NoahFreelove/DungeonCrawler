package com.dungeoncrawler.Entities.Player.Abilities;

import com.JEngine.Core.GameObject;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Enemies.Enemy;
import com.dungeoncrawler.Entities.Player.PlayerController;
import javafx.scene.paint.Color;

import static com.dungeoncrawler.Entities.Player.Abilities.AbilityStats.FIRE_DAMAGE;
import static com.dungeoncrawler.Entities.Player.Abilities.AbilityStats.SHIELD_STRENGTH;

public class ShieldAbility extends Ability {

    public ShieldAbility(){
        super(PlayerController.instance.getPosition(), 0.8f, Color.BLUE);
        PlayerController.instance.addShield(new Shield(SHIELD_STRENGTH));
    }

    @Override
    protected void StartEffect(){

        SceneManager.getActiveScene().add(new ShieldEffect());

    }
    @Override
    protected void EndEffect(){
        super.EndEffect();
    }

}
