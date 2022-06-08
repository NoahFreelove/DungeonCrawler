package com.dungeoncrawler.Entities;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.dungeoncrawler.Entities.Items.ItemSpawn;
import com.dungeoncrawler.Entities.Items.ItemType;
import com.dungeoncrawler.Entities.Weapons.Melee.Boomerang;
import com.dungeoncrawler.Entities.Weapons.Melee.Sword;
import com.dungeoncrawler.Entities.Weapons.Projectile.BarrettM82;
import com.dungeoncrawler.Entities.Weapons.Projectile.Bow;
import com.dungeoncrawler.Entities.Weapons.WeaponSpawn;
import com.dungeoncrawler.Scenes.Rooms.Room;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Shop extends Pawn {

    private static WeaponSpawn bowSpawn;
    private static WeaponSpawn boomerangSpawn;
    private static WeaponSpawn swordSpawn;
    private static WeaponSpawn barrettSpawn;
    private static WeaponSpawn bbGunSpawn;
    private static ItemSpawn healthSpawn;
    private static ItemSpawn shieldSpawn;
    private static ItemSpawn fireAbilitySpawn;
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
        //parent.add(bbGunSpawn);
        if(floor<6)
            return;

        parent.add(fireAbilitySpawn);
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

        bowSpawn = new WeaponSpawn(new Vector3(200,200), new GameImage("bin/bow.png"), new Bow(new Vector3(200,200)), true, (floor%5 ==0)? 100 : 200, parent);
        boomerangSpawn = new WeaponSpawn(new Vector3(550,200), new GameImage("bin/boomerang.png"), new Boomerang(new Vector3(900,200)), true, (floor%5 ==0)? 125 : 250, parent);
        swordSpawn = new WeaponSpawn(new Vector3(550,400), new GameImage("bin/sword.png"), new Sword(new Vector3(900,400)), true, (floor%5 ==0)? 5 : 30, parent);
        barrettSpawn = new WeaponSpawn(new Vector3(200,400), new GameImage("bin/barrett.png", 128, 128), new BarrettM82(new Vector3(200,400)), true, (floor%5 ==0)? 2000 : 3000, parent);

        healthSpawn = new ItemSpawn(new Vector3(800,200), new GameImage("bin/heart.png"), ItemType.HEALTH, true, 50, parent, 15, "15 Health");
        shieldSpawn= new ItemSpawn(new Vector3(1000,200), new GameImage("bin/shieldItem.png"), ItemType.SHIELD, true, 100, parent, 2, "2-Hit Shield");
        if(floor<6)
            return;

        fireAbilitySpawn = new ItemSpawn(new Vector3(1000,400), new GameImage("bin/fireball.png"), ItemType.ABILITY, true, 300, parent, 1, "Fire Ability");

        //bbGunSpawn = new WeaponSpawn(new Vector3(550,400), new GameImage("bin/bbGun.png",64,64), new BBGun(new Vector3(550,400)), true, 500, parent);

    }
}
