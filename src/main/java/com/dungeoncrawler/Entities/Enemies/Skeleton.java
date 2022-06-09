package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Components.Pathfinding_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Utility.Misc.GameTimer;
import com.dungeoncrawler.Entities.Enemies.Bosses.Graveyard;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;

import static com.dungeoncrawler.Entities.EnemyStats.*;

public class Skeleton extends Enemy {
    private final Pathfinding_Comp pathfinding_comp;
    private boolean risen;
    public Skeleton(Vector3 initPos, boolean spawnedIn) {
        super(initPos, new GameImage(SKELETON_IMAGE_PATH), SKELETON_DAMAGE, SKELETON_MAX_HEALTH,
                SKELETON_ATTACK_DELAY, SKELETON_DIFFICULTY);
        pathfinding_comp = new Pathfinding_Comp(PlayerController.instance);
        pathfinding_comp.setSuccessRange(64);
        pathfinding_comp.setMoveAfterSuccess(false);
        addComponent(pathfinding_comp);
        risen = false;

        if(spawnedIn)
        {
            activate(RoomManager.currentRoom);
            setDifficulty(0);
        }

    }

    private void rise(){
        setSprite(new GameImage("bin/skeleton.png", 48,96));
        risen = true;
        setCanAttack(true);
    }

    @Override
    public void activate(GameScene room){
        super.activate(room);

        if(!risen)
        {
            GameTimer riseTimer = new GameTimer((long) (1000*SKELETON_RISE_TIME), args -> rise(), true);
            riseTimer.start();
            setCanAttack(false);
        }
        else {
            setCanAttack(true);
        }
    }

    @Override
    public void OnUnload(){
    }

    @Override
    public void takeDamage(double damage){
        if(!risen)
            return;
        super.takeDamage(damage);
    }

    @Override
    public void onDeath(){
        super.onDeath();
        for (GameObject gameObject :
                RoomManager.currentRoom.getObjects()) {
            if(gameObject instanceof Graveyard graveyard)
            {
                graveyard.removeSkeleton();
            }
        }
    }

    @Override
    public void Update() {
        super.Update();
        if (!canMove || !risen) {
            pathfinding_comp.setMoveSpeed(0);
            setCanAttack(false);
            return;
        }

        pathfinding_comp.setMoveSpeed(SKELETON_MOVE_SPEED);
        pathfinding_comp.setTarget(PlayerController.instance);
    }
}