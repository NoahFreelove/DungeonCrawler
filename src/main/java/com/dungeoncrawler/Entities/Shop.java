package com.dungeoncrawler.Entities;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.dungeoncrawler.Entities.Weapons.Melee.Boomerang;
import com.dungeoncrawler.Entities.Weapons.Projectile.Bow;
import com.dungeoncrawler.Entities.Weapons.WeaponSpawn;
import com.dungeoncrawler.Scenes.Rooms.Room;

public class Shop extends Pawn {

    private static WeaponSpawn bowSpawn;
    private static WeaponSpawn boomerangSpawn;
    private static Room parent;
    public Shop(Room parent) {
        super(Transform.simpleTransform(new Vector3(500,300)), null, new Identity("Shop"));
        Shop.parent = parent;

        regenerateSpawns();
        parent.add(bowSpawn);
        parent.add(boomerangSpawn);

    }

    public static void regenerateSpawns(){
        bowSpawn = new WeaponSpawn(new Vector3(200,200), new GameImage("bin/bow.png"), new Bow(new Vector3(200,200)), true, 400, parent);
        boomerangSpawn = new WeaponSpawn(new Vector3(900,200), new GameImage("bin/boomerang.png"), new Boomerang(new Vector3(900,200)), true, 250, parent);

    }
}
