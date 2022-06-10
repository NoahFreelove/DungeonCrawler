package com.dungeoncrawler.Entities.Enemies.Bosses;

import com.JEngine.Components.Pathfinding_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GameTimer;
import com.dungeoncrawler.Entities.Enemies.BaseEnemies.Skeleton;
import com.dungeoncrawler.Entities.Player.PlayerController;

import static com.dungeoncrawler.Entities.EnemyStats.*;

public class Graveyard extends Boss {
    private int skeletonsAlive = 0;
    private GameTimer spawnDelay = new GameTimer((long) (GRAVEYARD_SPAWN_DELAY*1000), args -> spawnSpookyMen(), false);
    private final Pathfinding_Comp pathfinding_comp;

    public Graveyard(Vector3 initPos, int x, int y) {
        super(initPos, new GameImage(GRAVEYARD_IMAGE_PATH)
                , GRAVEYARD_DAMAGE, GRAVEYARD_MAX_HEALTH, GRAVEYARD_ATTACK_DELAY, GRAVEYARD_DIFFICULTY, "get spook'd", 1f, x,y);
        pathfinding_comp = new Pathfinding_Comp(this);
        pathfinding_comp.setMoveSpeed(GRAVEYARD_SPEED);
        pathfinding_comp.setSuccessRange(256);
        pathfinding_comp.setMoveAfterSuccess(false);
        addComponent(pathfinding_comp);
    }

    private void spawnSpookyMen(){
        if(PlayerController.instance == null)
            return;
        if(skeletonsAlive >=4)
            return;
        while (skeletonsAlive < 4)
        {
            switch (skeletonsAlive)
            {
                case 0 ->{
                    SceneManager.getActiveScene().add(new Skeleton(getPosition().subtract(new Vector3(100,0)), true));
                }
                case 1 -> {
                    SceneManager.getActiveScene().add(new Skeleton(getPosition().add(new Vector3(0,100)), true));
                }
                case 2 -> {
                    SceneManager.getActiveScene().add(new Skeleton(getPosition().add(new Vector3(100,0)), true));
                }
                case 3 ->{
                    SceneManager.getActiveScene().add(new Skeleton(getPosition().subtract(new Vector3(0,100)), true));
                }
            }
            skeletonsAlive++;
        }
    }

    @Override
    public void battleInit(){
        spawnDelay = new GameTimer((long) (GRAVEYARD_SPAWN_DELAY*1000), args -> spawnSpookyMen(), false);
        spawnDelay.start();
    }

    @Override
    public void activate(GameScene room){
        if(getHealth()<=0)
        {
            return;
        }
        spawnSpookyMen();
        super.activate(room);
    }

    @Override
    public void Update(){
        if(!canMove)
        {
            pathfinding_comp.setMoveSpeed(0);
            return;
        }
        else {
            pathfinding_comp.setTarget(PlayerController.instance);
            pathfinding_comp.setMoveSpeed(GRAVEYARD_SPEED);
        }
        super.Update();
    }

    @Override
    protected void onDeath(){
        spawnDelay.stop();
        super.onDeath();
    }

    public void removeSkeleton(){
        skeletonsAlive--;
        if(skeletonsAlive<0)
        {
            skeletonsAlive = 0;
        }
    }
}
