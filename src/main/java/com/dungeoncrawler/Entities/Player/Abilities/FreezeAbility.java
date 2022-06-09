package com.dungeoncrawler.Entities.Player.Abilities;

import com.JEngine.Core.GameObject;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Enemies.Enemy;
import com.dungeoncrawler.Entities.Enemies.EnemyProjectile;
import com.dungeoncrawler.Entities.Player.PlayerController;
import javafx.scene.paint.Color;

import static com.dungeoncrawler.Entities.Player.Abilities.AbilityStats.FREEZE_DURATION;

public class FreezeAbility extends Ability {
        GameScene sceneStartedIn;
    public FreezeAbility(){
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

        SnowEffect snowEffect1 = new SnowEffect(100);
        SnowEffect snowEffect2 = new SnowEffect(200);
        SnowEffect snowEffect3 = new SnowEffect(300);
        SnowEffect snowEffect4 = new SnowEffect(400);
        SnowEffect snowEffect5 = new SnowEffect(500);
        SnowEffect snowEffect6 = new SnowEffect(600);
        SnowEffect snowEffect7 = new SnowEffect(700);
        SnowEffect snowEffect8 = new SnowEffect(800);
        SnowEffect snowEffect9 = new SnowEffect(900);
        SnowEffect snowEffect10 = new SnowEffect(1000);
        SnowEffect snowEffect11 = new SnowEffect(1100);
        SceneManager.getActiveScene().add(snowEffect1, snowEffect2, snowEffect3, snowEffect4, snowEffect5, snowEffect6, snowEffect7, snowEffect8, snowEffect9, snowEffect10, snowEffect11);
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
