package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Components.Pathfinding_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.dungeoncrawler.Entities.Player.PlayerController;

public class Follower extends Enemy{
    private Pathfinding_Comp pathfinding_comp;
    public Follower(Vector3 initPos) {
        super(initPos, new GameImage("bin/follower.png"), 5, 25, 3,1);
        pathfinding_comp = new Pathfinding_Comp(PlayerController.instance);
        pathfinding_comp.setMoveSpeed(3);
        pathfinding_comp.setSuccessRange(64);
        pathfinding_comp.setMoveWhenSuccess(false);
        addComponent(pathfinding_comp);
    }

    @Override
    public void Update(){
        if(!canMove)
            return;
        super.Update();
        pathfinding_comp.setTarget(PlayerController.instance);
        // face player
        Vector3 deltaPos = PlayerController.instance.getPosition().subtract(getPosition());
        double deg;
        if(deltaPos.x<0)
        {
            deg = Math.toDegrees(Math.atan(deltaPos.y/deltaPos.x)-(Math.PI/2));
        }
        else
        {
            deg = Math.toDegrees(Math.atan(deltaPos.y/deltaPos.x)+(Math.PI/2));
        }
        setRotation(new Vector3((float) deg,0,0));
    }
}
