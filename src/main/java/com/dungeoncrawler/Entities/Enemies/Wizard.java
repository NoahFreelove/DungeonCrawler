package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.Misc.GameTimer;

import static com.dungeoncrawler.Entities.EnemyStats.*;

public class Wizard extends Enemy {

    GameTimer teleportTimer = new GameTimer((long) (1000 * WIZARD_TELEPORT_DELAY), args -> teleport());

    public Wizard(Vector3 initPos) {
        super(initPos, new GameImage(WIZARD_IMAGE_PATH), WIZARD_DAMAGE, WIZARD_MAX_HEALTH, WIZARD_ATTACK_DELAY, WIZARD_DIFFICULTY);
    }

    @Override
    public void Update() {
        super.Update();
        if (!canMove) {
            if(teleportTimer.isRunning())
            {
                teleportTimer.stop();
            }
            return;
        }
        if(!teleportTimer.isRunning())
        {
            teleportTimer.start();
        }
    }

    @Override
    public void activate(GameScene room){
        teleportTimer.start();
        super.activate(room);
    }

    @Override
    public void OnUnload(){
        teleportTimer.stop();
    }

    @Override
    public void onDeath(){
        teleportTimer.stop();
        super.onDeath();
    }

    public void teleport(){
        setPosition(new Vector3(generatePosition(),getPosition().z));
        if(!canAttack())
            return;
        SceneManager.getActiveScene().add(new EnemyProjectile(getPosition(), Math.toDegrees(playerPositionToRadians()), WIZARD_MAGIC_DAMAGE, WIZARD_MAGIC_MOVE_SPEED, new GameImage(WIZARD_MAGIC_IMAGE_PATH)));
    }

    private Vector2 generatePosition(){
        int xPos = GameMath.randRangeInclusive(100,900);
        int yPos = GameMath.randRangeInclusive(150,500);
        return new Vector2(xPos,yPos);
    }
}