package com.dungeoncrawler.GameObjects.Weapons;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Utility.GameMath;
import com.dungeoncrawler.GameObjects.PlayerController;
import com.dungeoncrawler.GameObjects.SwordCollider;
import com.dungeoncrawler.SimpleDirection;

public class Sword extends Weapon {
    private Vector3 weaponOffset = new Vector3(0,0,0);
    private Vector3 targetWeaponOffset = new Vector3(0,0,0);
    private float animationTime = 0f;
    public Sword(Vector3 pos, GameImage sprite) {
        super(2, 0.5, pos, sprite);
        addCollider(new SwordCollider(new Vector3(0,0,0),16,32, true, this));
    }

    @Override
    public void Update(){
        super.Update();
        setPosition(PlayerController.instance.getPosition().add(weaponOffset));
        weaponOffset = GameMath.interpolateClamped(weaponOffset, targetWeaponOffset, animationTime);
        animationTime += 0.1f;
        if (animationTime >= 1f)
        {
            animationTime = 0f;
            targetWeaponOffset = Vector3.emptyVector();
            PlayerController.instance.setAttacking(false);
        }
    }

    @Override
    protected void attack(SimpleDirection direction) {
        animationTime = 0;
        PlayerController.instance.setAttacking(true);

        switch (direction) {
            case UP -> targetWeaponOffset = new Vector3(0, -32, 0);
            case DOWN -> targetWeaponOffset = new Vector3(0, 32, 0);
            case LEFT -> targetWeaponOffset = new Vector3(-32, 0, 0);
            case RIGHT -> targetWeaponOffset = new Vector3(32, 0, 0);
        }
    }
}
