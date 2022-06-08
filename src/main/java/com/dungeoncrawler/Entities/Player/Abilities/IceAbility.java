package com.dungeoncrawler.Entities.Player.Abilities;

import com.JEngine.Core.GameObject;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.ImageProcessingEffects.GameLight;
import com.dungeoncrawler.Entities.Enemies.Enemy;
import com.dungeoncrawler.Entities.Enemies.EnemyProjectile;
import com.dungeoncrawler.Entities.Player.PlayerController;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

import static com.dungeoncrawler.Entities.Player.Abilities.AbilityStats.FREEZE_DURATION;

public class IceAbility extends Ability {
        GameScene sceneStartedIn;
    public IceAbility(){
        super(PlayerController.instance.getPosition(), FREEZE_DURATION, Color.CYAN);
    }

    @Override
    protected void StartEffect() {
        for (GameObject object: SceneManager.getActiveScene().getObjects()) {
            if(object instanceof Enemy enemy){
                enemy.neutralize();
            }
            if(object instanceof EnemyProjectile projectile){
                projectile.canMove = false;
            }
        }
    }

    @Override
    protected void EndEffect(){
        super.EndEffect();
        for (GameObject object: SceneManager.getActiveScene().getObjects()) {
            if(object instanceof Enemy enemy){
                enemy.activate(sceneStartedIn);
            }
            if(object instanceof EnemyProjectile projectile){
                projectile.canMove = true;
            }
        }
    }

}
