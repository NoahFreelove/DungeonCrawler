package com.dungeoncrawler.Entities.Weapons.Projectile;

import com.JEngine.Core.Component;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.ImageProcessingEffects.GameLight;
import com.dungeoncrawler.Main;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class Projectile extends Pawn {
    float moveSpeed;
    Vector2 direction;
    double damage;
    int life = 120;
    ProjectileCollider collider;
    Light.Point projectileLight;
    Lighting lightSource;
    GameLight gl;
    boolean ignoreOnHit;

    boolean enableLighting = true;
    public Projectile(Vector3 pos, Vector3 rot, Vector2 moveDirection, double damage, float moveSpeed, GameImage sprite) {
        super(new Transform(pos, rot, Vector3.oneVector()), sprite, new Identity("projectile"));
        this.direction = moveDirection;
        this.damage = damage;
        collider = new ProjectileCollider(Vector3.emptyVector(), 32,32, this);
        this.moveSpeed = moveSpeed;
        addComponent(collider);
        projectileLight = new Light.Point();
        projectileLight.setColor(Color.web("#FFD580"));

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
        lightSource.setSurfaceScale(0.5);
        gl = new GameLight(lightSource, false);
        SceneManager.getActiveScene().addLight(gl);
    }
    public Projectile(Vector3 pos, Vector3 rot, Vector2 moveDirection, double damage, float moveSpeed, GameImage sprite, boolean ignoreOnHit) {
        this(pos, rot, moveDirection, damage, moveSpeed, sprite);
        this.ignoreOnHit = ignoreOnHit;
    }

        @Override
    public void Update(){

        super.Update();
        life--;
        if(life<=0)
        {
            destroySelf();
            return;
        }
        projectileLight.setColor(enableLighting? Color.web("#FFD580") : Color.TRANSPARENT);

        Move(direction, moveSpeed);
        double xOffset = 0;
        double yOffset =0;
        if(Main.lightingOffset)
        {
            yOffset = getPosition().y/256*128;
            xOffset = getPosition().x/256*128;
        }
        projectileLight.setX(getPosition().x+xOffset);
        projectileLight.setY(getPosition().y+yOffset);
    }

    public double getDamage(){ return damage;}

    public void destroySelf(){
        setActive(false);
        SceneManager.getActiveScene().remove(this);
        for (Component comp: getComponents()) {
            if(comp!=null)
            {
                comp.setActive(false);
            }
        }
        SceneManager.getActiveScene().removeLight(gl);
    }
    public void onHit(){
        if(ignoreOnHit)
            return;
        destroySelf();
    }

    public void setEnableLighting(boolean enabled)
    {
        enableLighting = enabled;
    }
}
