package com.dungeoncrawler.Entities.Enemies.Bosses;

import com.JEngine.Components.Pathfinding_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Utility.Misc.GameTimer;
import com.JEngine.Utility.Misc.GenericMethod;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;

import static com.dungeoncrawler.Entities.EnemyStats.*;

public class Doctor extends Boss{

    private boolean currentlyHealing;
    private boolean runningToHealSpot;
    private Pathfinding_Comp pathfinding_comp;
    private GameObject healSpot;
    private final GameTimer healTimer = new GameTimer(200, args -> Heal());
    public Doctor(Vector3 initPos, int x, int y) {
        super(initPos, new GameImage(DOCTOR_IMAGE_PATH)
                , DOCTOR_DAMAGE, DOCTOR_BASE_HEALTH, DOCTOR_ATTACK_DELAY, DOCTOR_DIFFICULTY, "needle goes stab stab", 0.5f, x,y);

        pathfinding_comp = new Pathfinding_Comp(this);
        pathfinding_comp.setMoveSpeed(DOCTOR_MOVE_SPEED);
        pathfinding_comp.setMoveAfterSuccess(false);
        pathfinding_comp.setSuccessRange(75);
        addComponent(pathfinding_comp);

        healSpot = new GameObject(Transform.simpleTransform(1000,100,0), new Identity("healSpot"));

        pathfinding_comp.setOnTargetReachedEvent(args -> ReachedHealSpot());
    }

    @Override
    public void Update() {
        runningToHealSpot = (getHealth() < DOCTOR_HEAL_THRESHOLD || currentlyHealing);
        if(!canMove)
            pathfinding_comp.setMoveSpeed(0);
        if (runningToHealSpot)
        {
            pathfinding_comp.setTarget(healSpot);
            pathfinding_comp.setMoveSpeed(DOCTOR_RUN_SPEED);
            setCanAttack(false);
        }
        else {
            pathfinding_comp.setTarget(PlayerController.instance);
            pathfinding_comp.setMoveSpeed(DOCTOR_MOVE_SPEED);
        }

        if(getMaxHealth() > DOCTOR_BASE_HEALTH)
        {
            healthBar.setStyle("-fx-accent: blue");
        }
        else {
            healthBar.setStyle("-fx-accent: red");
        }
        System.out.println(getHealth());
        super.Update();
    }

    @Override
    public void takeDamage(double damage){
        if(getHealth()>=DOCTOR_BASE_HEALTH)
        {
            healTimer.stop();
            currentlyHealing = false;
            setCanAttack(true);
        }
        super.takeDamage(damage);
    }


    private void ReachedHealSpot(){
        if(!runningToHealSpot)
            return;
        // Have short delay after reaching heal spot to give player fair chance to kill
        new GameTimer(1000, args -> healTimer.start(), true, true);
    }
    private void Heal() {
        currentlyHealing = true;
        setHealth(getHealth() + DOCTOR_HEAL_RATE);
        if (getHealth() > DOCTOR_MAX_HEALTH) {
            setHealth(DOCTOR_MAX_HEALTH);
        }
        if (getHealth() > getMaxHealth()) {
            setMaxHealth(getHealth());
        }
        if (getHealth() >= DOCTOR_MAX_HEALTH)
        {
            currentlyHealing = false;
            healTimer.stop();
            setCanAttack(true);
        }
    }
}
