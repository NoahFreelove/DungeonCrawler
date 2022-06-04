package com.dungeoncrawler.Entities.Enemies.Bosses;

import com.JEngine.Components.Pathfinding_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Utility.ImageProcessing.GenerateSolidTexture;
import com.JEngine.Utility.Misc.GameTimer;
import com.dungeoncrawler.Entities.Player.PlayerController;
import javafx.scene.effect.ColorAdjust;

public class Knight extends Boss {
    private Pathfinding_Comp chargePathfind;
    private GameTimer chargeDelay = new GameTimer(5000, args -> charge(), false);
    public Knight(Vector3 initPos, int x, int y) {
        super(initPos, new GameImage(GenerateSolidTexture.generateImage(96,96,0xFF6E6E6E))
                , 8, 50, 0.5, 5, "I definitely wont charge at you", 1f, x,y);
        chargePathfind = new Pathfinding_Comp(this);
        chargePathfind.setMoveSpeed(10);
        chargePathfind.setMoveAfterSuccess(false);
        chargePathfind.setSuccessRange(25);
        addComponent(chargePathfind);
    }

    private void startCharge(){
        chargeDelay = new GameTimer(3000, args -> {
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
    }

    @Override
    protected void onDeath(){
        chargeDelay.stop();
        super.onDeath();
    }

}
