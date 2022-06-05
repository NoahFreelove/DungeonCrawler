package com.dungeoncrawler.Entities;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.dungeoncrawler.Entities.Weapons.Melee.Boomerang;
import com.dungeoncrawler.Entities.Weapons.Melee.Sword;
import com.dungeoncrawler.Entities.Weapons.Projectile.BBGun;
import com.dungeoncrawler.Entities.Weapons.Projectile.BarrettM82;
import com.dungeoncrawler.Entities.Weapons.Projectile.Bow;
import com.dungeoncrawler.Entities.Weapons.Weapon;
import com.dungeoncrawler.Entities.Weapons.WeaponSpawn;
import com.dungeoncrawler.Scenes.Rooms.Room;

public class Shop extends Pawn {

    private static WeaponSpawn bowSpawn;
    private static WeaponSpawn boomerangSpawn;
    private static WeaponSpawn swordSpawn;
    private static WeaponSpawn barrettSpawn;
    private static WeaponSpawn bbGunSpawn;

    private static Room parent;
    public Shop(Room parent) {
        super(Transform.simpleTransform(new Vector3(500,300)), null, new Identity("Shop"));
        Shop.parent = parent;

        regenerateSpawns();
        parent.add(bowSpawn);
        parent.add(boomerangSpawn);
        parent.add(swordSpawn);
        parent.add(barrettSpawn);
        //parent.add(bbGunSpawn);
    }

    public static void regenerateSpawns(){
        bowSpawn = new WeaponSpawn(new Vector3(200,200), new GameImage("bin/bow.png"), new Bow(new Vector3(200,200)), true, 200, parent);
        boomerangSpawn = new WeaponSpawn(new Vector3(900,200), new GameImage("bin/boomerang.png"), new Boomerang(new Vector3(900,200)), true, 250, parent);
        swordSpawn = new WeaponSpawn(new Vector3(900,400), new GameImage("bin/sword.png"), new Sword(new Vector3(900,400)), true, 30, parent);
        barrettSpawn = new WeaponSpawn(new Vector3(200,400), new GameImage("bin/barrett.png", 128, 128), new BarrettM82(new Vector3(200,400)), true, 3000, parent);
        //bbGunSpawn = new WeaponSpawn(new Vector3(550,400), new GameImage("bin/bbGun.png",64,64), new BBGun(new Vector3(550,400)), true, 500, parent);

    }
}
