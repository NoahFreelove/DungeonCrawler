package com.dungeoncrawler.Entities.Enemies.BaseEnemies;

import com.JEngine.Components.Pathfinding_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.dungeoncrawler.Entities.Enemies.Enemy;
import com.dungeoncrawler.Entities.Player.PlayerController;

import static com.dungeoncrawler.Entities.EnemyStats.*;

public class Follower extends Enemy {
    private final Pathfinding_Comp pathfinding_comp;
    public Follower(Vector3 initPos) {
        super(initPos, new GameImage(FOLLOWER_IMAGE_PATH), FOLLOWER_DAMAGE, FOLLOWER_MAX_HEALTH, FOLLOWER_ATTACK_DELAY,FOLLOWER_DIFFICULTY);
        pathfinding_comp = new Pathfinding_Comp(PlayerController.instance);
        pathfinding_comp.setMoveSpeed(FOLLOWER_SPEED);
        pathfinding_comp.setSuccessRange(64);
        pathfinding_comp.setMoveAfterSuccess(false);
        addComponent(pathfinding_comp);
    }

    @Override
    public void Update(){
        super.Update();
        if(!canMove)
        {
            pathfinding_comp.setMoveSpeed(0);
            return;
        }
        pathfinding_comp.setMoveSpeed(FOLLOWER_SPEED);
        pathfinding_comp.setTarget(PlayerController.instance);
        setRotation(new Vector3((float) facePlayer(),0,0));
    }
}
