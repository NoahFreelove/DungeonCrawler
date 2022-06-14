package com.dungeoncrawler.Entities.Enemies;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.ImageProcessingAndEffects.GameLight;
import com.dungeoncrawler.Main;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class EnemyProjectile extends Pawn {
    Vector2 direction;
    private int life = 180;
    private float moveSpeed;
    private final double damage;
    public boolean canMove;
    private Light.Point projectileLight;
    private Lighting lightSource = new Lighting();
    private GameLight gl;
    private boolean enableLight = false;
    // Make collider a bit smaller than the bullet because it's hard to dodge them all
    EnemyProjectileCollider ec = new EnemyProjectileCollider(Vector3.emptyVector(), 24,24,true,this);


    public EnemyProjectile(Vector3 initPos, double angle, double damage, float moveSpeed, GameImage sprite) {
        super(new Transform(initPos,new Vector3((float) angle+90,0,0), new Vector3(0.5f,0.5f,0)), sprite, new Identity("EnemyProjectile"));
        this.direction = angleToVector(Math.toRadians(angle));
        this.moveSpeed = moveSpeed;
        addCollider(ec);
        this.damage = damage;
        this.canMove = true;
    }

    public EnemyProjectile(Vector3 initPos, double angle, double damage, float moveSpeed, GameImage sprite, boolean enableLight) {
        super(new Transform(initPos,new Vector3((float) angle+90,0,0), new Vector3(0.5f,0.5f,0)), sprite, new Identity("EnemyProjectile"));
        this.direction = angleToVector(Math.toRadians(angle));
        this.moveSpeed = moveSpeed;
        addCollider(ec);
        this.damage = damage;
        this.canMove = true;

        projectileLight = new Light.Point();
        projectileLight.setColor(Color.ORANGE);
        double xOffset = 0;
        double yOffset = 0;
        projectileLight.setX(getPosition().x+xOffset+moveSpeed);
        projectileLight.setY(getPosition().y+yOffset+moveSpeed);
        projectileLight.setZ(50);
        lightSource = new Lighting();

        lightSource.setLight(projectileLight);
        // make light dim
        lightSource.setDiffuseConstant(0.5);
        lightSource.setSpecularConstant(0.5);
        lightSource.setSurfaceScale(0.1);
        this.enableLight = enableLight;
        gl = new GameLight(lightSource, false);
        SceneManager.getActiveScene().addLight(gl);
    }
    private Vector2 angleToVector(double radian){
        return new Vector2(Math.cos(radian), Math.sin(radian));
    }

    @Override
    public void Update() {
        if (enableLight)
        {
            lightSource.setLight(projectileLight);
            double xOffset = 0;
            double yOffset =0;
            if(Main.lightingOffset)
            {
                yOffset = getPosition().y/256*128;
                xOffset = getPosition().x/256*128;
            }
            if(projectileLight != null)
            {
                projectileLight.setX(getPosition().x+xOffset);
                projectileLight.setY(getPosition().y+yOffset);
            }
        }
        else {
            lightSource.setLight(null);
        }
        if(!canMove)
            return;
        life--;
        if (life <= 0)
        {
            onHit();
        }else {
            super.Update();
            Move(direction, moveSpeed);
        }
    }

    public void onHit(){
        SceneManager.getActiveScene().removeLight(gl);
        SceneManager.getActiveScene().remove(this);
        setActive(false);
        ec.setActive(false);
        life = 0;
    }

    public double getDamage() {
        return damage;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public boolean isEnableLight() {
        return enableLight;
    }

    public void setEnableLight(boolean enableLight) {
        this.enableLight = enableLight;
    }

    public void setLightColor(Color lightColor) {
        projectileLight.setColor(lightColor);
    }
}
