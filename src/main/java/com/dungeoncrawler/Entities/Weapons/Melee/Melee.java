package com.dungeoncrawler.Entities.Weapons.Melee;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Utility.GameMath;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Entities.Weapons.Weapon;
import com.JEngine.Core.Position.SimpleDirection;

public class Melee extends Weapon {
    private Vector3 weaponOffset = new Vector3(0,0,0);
    private Vector3 targetWeaponOffset = new Vector3(0,0,0);
    private float animationTime = 0f;
    private float attackDistance;
    private float animationSpeed;
    private Vector3 posWhenThrown = new Vector3(0,0,0);
    private boolean isAttacking;
    private boolean isThrowable;
    protected boolean alwaysAttacking;

    public Melee(Vector3 pos, float damage, double attackDelay, GameImage sprite, float attackDistance, float animationSpeed, boolean isThrowable) {
        super(damage, attackDelay, pos, sprite, 2);
        this.attackDistance = attackDistance;
        this.animationSpeed = animationSpeed;
        this.isThrowable = isThrowable;
        addCollider(new MeleeCollider(new Vector3(0,0,0),32,32, this));
    }

    @Override
    public void Update(){
        super.Update();
        if(PlayerController.instance == null)
            return;

        if(isAttacking && isThrowable){
            setPosition(posWhenThrown.add(weaponOffset));
        }
        else {
            setPosition(PlayerController.instance.getPosition().add(weaponOffset));
        }
        weaponOffset = GameMath.interpolateClamped(weaponOffset, targetWeaponOffset, animationTime);
        animationTime += 0.1f*animationSpeed;
        if (animationTime >= 1f)
        {
            isAttacking = false;
            animationTime = 0f;
            targetWeaponOffset = Vector3.emptyVector();
            PlayerController.instance.setAttacking(alwaysAttacking);
        }
    }

    @Override
    protected void attack(SimpleDirection direction) {
        if(PlayerController.instance == null)
            return;
        isAttacking = true;
        animationTime = 0;
        PlayerController.instance.setAttacking(true);
        posWhenThrown = PlayerController.instance.getPosition();
        switch (direction) {
            case UP -> targetWeaponOffset = new Vector3(0, -attackDistance, 0);
            case DOWN -> targetWeaponOffset = new Vector3(0, attackDistance+32, 0);
            case LEFT -> targetWeaponOffset = new Vector3(-attackDistance, 0, 0);
            case RIGHT -> targetWeaponOffset = new Vector3(attackDistance+32, 0, 0);
        }
    }


}
