package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Components.Pathfinding_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Utility.Misc.GameTimer;
import com.JEngine.Utility.Misc.GenericMethod;
import com.dungeoncrawler.Entities.Player.PlayerController;

public class Frog extends Enemy{
    private Pathfinding_Comp pathfinding_comp;
    private GameTimer jumpTimer = new GameTimer(2000, args -> jump());
    private float magnetToPlayerDistance = 400f;
    public Frog(Vector3 initPos) {
        super(initPos, new GameImage("bin/frog.png"), 5, 15, 1,1);
        pathfinding_comp = new Pathfinding_Comp(null);
        pathfinding_comp.setMoveSpeed(15);
        pathfinding_comp.setSuccessRange(64);
        pathfinding_comp.setMoveAfterSuccess(false);
        addComponent(pathfinding_comp);
    }

    @Override
    public void Update(){
        super.Update();
        if(!canMove)
            return;
        setRotation(new Vector3((float) facePlayer(),0,0));
    }

    @Override
    public void activate(GameScene room) {
        super.activate(room);
        jumpTimer.start();
    }

    private void jump(){
        if(PlayerController.instance == null)
            return;
        float xDist = PlayerController.instance.getPosition().x - getPosition().x;
        float yDist = PlayerController.instance.getPosition().y - getPosition().y;
        if(Math.abs(xDist) + Math.abs(yDist) < magnetToPlayerDistance)
        {
            pathfinding_comp.setTarget(new GameObject(Transform.simpleTransform(new Vector3(PlayerController.instance.getPosition())), new Identity("frog target")));
        }
        else {
            pathfinding_comp.setTarget(new GameObject(Transform.exSimpleTransform(getPosition().x + xDist/2, getPosition().y + yDist/2), new Identity("frog target")));
        }
    }

    @Override
    public void OnUnload(){
        super.OnUnload();
        jumpTimer.stop();
    }

    @Override
    public void onDeath(){
        jumpTimer.stop();
        super.onDeath();
    }
}
