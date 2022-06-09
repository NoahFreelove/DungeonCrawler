package com.dungeoncrawler.Entities.Enemies.Bosses;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.Misc.GameTimer;
import com.dungeoncrawler.Entities.Enemies.Skeleton;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;

import static com.dungeoncrawler.Entities.EnemyStats.*;

public class Graveyard extends Boss {
    private int skeletonsAlive = 0;
    private GameTimer spawnDelay = new GameTimer((long) (GRAVEYARD_SPAWN_DELAY*1000), args -> spawnSpookyMen(), false);

    public Graveyard(Vector3 initPos, int x, int y) {
        super(initPos, new GameImage(GRAVEYARD_IMAGE_PATH)
                , GRAVEYARD_DAMAGE, GRAVEYARD_MAX_HEALTH, GRAVEYARD_ATTACK_DELAY, GRAVEYARD_DIFFICULTY, "spooky", 1f, x,y);

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
        spawnSpookyMen();
        super.activate(room);
    }

    @Override
    public void Update(){
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
