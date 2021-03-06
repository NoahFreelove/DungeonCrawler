package com.dungeoncrawler.Entities;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.dungeoncrawler.Entities.Items.ItemSpawn;
import com.dungeoncrawler.Entities.Items.ItemType;
import com.dungeoncrawler.Entities.Weapons.Melee.Boomerang;
import com.dungeoncrawler.Entities.Weapons.Melee.Knife;
import com.dungeoncrawler.Entities.Weapons.Melee.Sword;
import com.dungeoncrawler.Entities.Weapons.Projectile.BarrettM82;
import com.dungeoncrawler.Entities.Weapons.Projectile.Bow;
import com.dungeoncrawler.Entities.Weapons.Projectile.Staff;
import com.dungeoncrawler.Entities.Weapons.WeaponSpawn;
import com.dungeoncrawler.Scenes.Rooms.Room;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static com.dungeoncrawler.Entities.Weapons.WeaponStats.*;

public class Shop extends Pawn {

    private static WeaponSpawn bowSpawn;
    private static WeaponSpawn boomerangSpawn;
    private static WeaponSpawn swordSpawn;
    private static WeaponSpawn barrettSpawn;
    private static WeaponSpawn bbGunSpawn;
    private static WeaponSpawn staffSpawn;
    private static WeaponSpawn knifeSpawn;

    private static ItemSpawn healthSpawn;
    private static ItemSpawn shieldSpawn;
    private static ItemSpawn fireAbilitySpawn;
    private static ItemSpawn iceAbilitySpawn;
    private static ItemSpawn shieldAbilitySpawn;

    private static Room parent;
    public Shop(Room parent, int floor) {
        super(Transform.simpleTransform(new Vector3(500,300)), null, new Identity("Shop"));
        Shop.parent = parent;

        regenerateSpawns(floor);
        parent.add(bowSpawn);
        parent.add(boomerangSpawn);
        parent.add(swordSpawn);
        parent.add(barrettSpawn);
        parent.add(healthSpawn);
        parent.add(shieldSpawn);
        parent.add(staffSpawn);
        parent.add(knifeSpawn);
        //parent.add(bbGunSpawn);
        if(floor<6)
            return;

        parent.add(fireAbilitySpawn);
        parent.add(iceAbilitySpawn);
        parent.add(shieldAbilitySpawn);
    }

    public static void regenerateSpawns(int floor){
        Text weaponTitleText = new Text("Weapons");
        weaponTitleText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        weaponTitleText.setX(350);
        weaponTitleText.setY(150);
        weaponTitleText.setFill(Color.WHITE);
        parent.addUI(weaponTitleText);

        Text itemTitleText = new Text("Items");
        itemTitleText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        itemTitleText.setX(900);
        itemTitleText.setY(150);
        itemTitleText.setFill(Color.WHITE);
        parent.addUI(itemTitleText);

        // Weapons:
        bowSpawn = new WeaponSpawn(new Vector3(200,200), new GameImage(BOW_IMAGE_PATH), new Bow(new Vector3(200,200)), true, (floor%5 ==0)? 100 : 200, parent, new Vector2(-10,0));
        boomerangSpawn = new WeaponSpawn(new Vector3(500,200), new GameImage(BOOMERANG_IMAGE_PATH), new Boomerang(new Vector3(900,200)), true, (floor%5 ==0)? 125 : 250, parent, new Vector2(-8,-5));
        swordSpawn = new WeaponSpawn(new Vector3(500,400), new GameImage(SWORD_IMAGE_PATH), new Sword(new Vector3(900,400)), true, (floor%5 ==0)? 5 : 30, parent, new Vector2(0,0));
        barrettSpawn = new WeaponSpawn(new Vector3(200,400), new GameImage(BARRETT_M82_IMAGE_PATH, 128, 128), new BarrettM82(new Vector3(200,400)), true, (floor%5 ==0)? 2000 : 3000, parent, new Vector2(-40,-50));
        staffSpawn = new WeaponSpawn(new Vector3(350,400), new GameImage(STAFF_IMAGE_PATH,64,64), new Staff(new Vector3(350,400)), true, 5000, parent, new Vector2(0,0));
        knifeSpawn = new WeaponSpawn(new Vector3(350,200), new GameImage(KNIFE_IMAGE_PATH,32,32), new Knife(new Vector3(350,200)), true, 50, parent, new Vector2(0,0));

        // Items:
        healthSpawn = new ItemSpawn(new Vector3(800,200), new GameImage("bin/images/heart.png"), ItemType.HEALTH, true, (floor%5 ==0)? 25 : 100, parent, 30, "30 Health", new Vector2(10,0) );
        shieldSpawn= new ItemSpawn(new Vector3(1000,200), new GameImage("bin/images/shieldItem.png"), ItemType.SHIELD, true, 100, parent, 2, "2-Hit Shield", new Vector2(15,0));
        if(floor<6)
            return;
        Text abilityText = new Text("Abilities");
        abilityText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        abilityText.setX(900);
        abilityText.setY(350);
        abilityText.setFill(Color.WHITE);
        parent.addUI(abilityText);
        fireAbilitySpawn = new ItemSpawn(new Vector3(1000,400), new GameImage("bin/images/fireball.png"), ItemType.ABILITY, true, (floor%5 ==0)? 220 : 300, parent, 1, "Fire Ability", new Vector2(-10,0));
        iceAbilitySpawn = new ItemSpawn(new Vector3(800,400), new GameImage("bin/images/snowflake.png"), ItemType.ABILITY, true, (floor%5 ==0)? 300 : 350, parent, 1, "Freeze Ability", new Vector2(25,0));
        shieldAbilitySpawn = new ItemSpawn(new Vector3(900,500), new GameImage("bin/images/shieldAbility.png"), ItemType.ABILITY, true, (floor%5==0)? 340 : 500, parent, 1, "Shield Ability", new Vector2(25,0));
        //bbGunSpawn = new WeaponSpawn(new Vector3(550,400), new GameImage("bin/images/bbGun.png",64,64), new BBGun(new Vector3(550,400)), true, 500, parent);

    }
}
