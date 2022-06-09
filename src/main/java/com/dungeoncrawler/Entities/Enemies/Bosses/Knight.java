package com.dungeoncrawler.Entities.Enemies.Bosses;

import com.JEngine.Components.Pathfinding_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Utility.Misc.GameTimer;
import com.dungeoncrawler.Entities.Player.PlayerController;
import javafx.scene.effect.ColorAdjust;

import static com.dungeoncrawler.Entities.EnemyStats.*;

public class Knight extends Boss {
    private Pathfinding_Comp chargePathfind;
    private GameTimer chargeDelay = new GameTimer((long) (KNIGHT_CHARGE_DELAY*1000), args -> charge(), false);

    public Knight(Vector3 initPos, int x, int y) {
        super(initPos, new GameImage(KNIGHT_IMAGE_PATH)
                , KNIGHT_DAMAGE, KNIGHT_MAX_HEALTH, KNIGHT_ATTACK_DELAY, KNIGHT_DIFFICULTY, "I definitely wont charge at you", 1f, x,y);
        chargePathfind = new Pathfinding_Comp(this);
        chargePathfind.setMoveSpeed(KNIGHT_CHARGE_SPEED);
        chargePathfind.setMoveAfterSuccess(false);
        chargePathfind.setSuccessRange(25);
        addComponent(chargePathfind);
    }

    private void startCharge(){
        chargeDelay = new GameTimer((long) (KNIGHT_CHARGE_DELAY*1000), args -> {
            charge();
        }, false);
        chargeDelay.start();
    }

    private void charge(){
        if(PlayerController.instance == null)
            return;
        getSprite().setColorAdjust(new ColorAdjust());
        chargePathfind.setTarget(PlayerController.instance.getPosition());

    }

    @Override
    public void battleInit(){
        startCharge();
    }

    @Override
    public void Update(){
        super.Update();
        if(!canMove || !canAttack())
        {
            chargePathfind.setMoveSpeed(0);
            return;
        }
        else {
            chargePathfind.setMoveSpeed(KNIGHT_CHARGE_SPEED);
        }
    }

    @Override
    protected void onDeath(){
        chargeDelay.stop();
        super.onDeath();
    }

}
