package com.dungeoncrawler.Entities.Player.Abilities;

import com.JEngine.Core.GameObject;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Enemies.Enemy;
import com.dungeoncrawler.Entities.Player.PlayerController;
import javafx.scene.paint.Color;

import static com.dungeoncrawler.Entities.Player.Abilities.AbilityStats.FIRE_DAMAGE;

public class FireAbility extends Ability {

    public FireAbility(){
        super(PlayerController.instance.getPosition(), 1, Color.RED);    }

    @Override
    protected void StartEffect(){
        for (GameObject object: SceneManager.getActiveScene().getObjects()) {
            if(object instanceof Enemy enemy){
                enemy.takeDamage(FIRE_DAMAGE);
            }
        }
        FireballEffect fireballEffect1 = new FireballEffect(100);
        FireballEffect fireballEffect2 = new FireballEffect(200);
        FireballEffect fireballEffect3 = new FireballEffect(300);
        FireballEffect fireballEffect4 = new FireballEffect(400);
        FireballEffect fireballEffect5 = new FireballEffect(500);

        SceneManager.getActiveScene().add(fireballEffect1, fireballEffect2, fireballEffect3, fireballEffect4, fireballEffect5);
    }
    @Override
    protected void EndEffect(){
        super.EndEffect();
    }

}
