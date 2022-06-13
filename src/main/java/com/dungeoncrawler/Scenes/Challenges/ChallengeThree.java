package com.dungeoncrawler.Scenes.Challenges;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Enemies.BaseEnemies.Follower;
import com.dungeoncrawler.Entities.Enemies.BaseEnemies.Wizard;
import com.dungeoncrawler.Entities.Enemies.Bosses.Doctor;
import com.dungeoncrawler.Entities.Enemies.Bosses.Knight;
import com.dungeoncrawler.Entities.Enemies.Bosses.Turret;
import com.dungeoncrawler.Entities.Items.ItemSpawn;
import com.dungeoncrawler.Entities.Items.ItemType;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Entities.Weapons.Melee.Boomerang;
import com.dungeoncrawler.Entities.Weapons.Melee.Knife;
import com.dungeoncrawler.Entities.Weapons.Projectile.Bow;
import com.dungeoncrawler.Entities.Weapons.WeaponSpawn;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.Scenes.Rooms.Room;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
import com.dungeoncrawler.Scenes.Rooms.RoomType;
import com.dungeoncrawler.Speech.SpeechManager;

import static com.dungeoncrawler.Entities.Weapons.WeaponStats.*;

public class ChallengeThree {
    public static void Create(){
        RoomManager.currentRoomY = 0;
        RoomManager.currentRoomX = 0;
        RoomManager.inChallenge = true;
        RoomManager.speechManager = new SpeechManager();

        RoomManager.width = 3;
        RoomManager.height = 3;
        RoomManager.rooms = new Room[RoomManager.width][RoomManager.height];
        RoomManager.rooms[0][0] = new Room(0, false, false, true, false, RoomType.SPAWN,0,0);
        RoomManager.rooms[0][1] = new Room(0, false, false, true, true, RoomType.SPAWN,0,1);
        RoomManager.rooms[0][2] = new Room(0, false, true, false, true, RoomType.SPAWN,0,2);
        RoomManager.rooms[1][2] = new Room(0, true, false, false, true, RoomType.SPAWN,1,2);
        RoomManager.rooms[1][1] = new Room(0, false, false, true, true, RoomType.SPAWN,1,1);
        RoomManager.rooms[1][0] = new Room(0, false, true, true, false, RoomType.SPAWN,1,0);
        RoomManager.rooms[2][0] = new Room(0, true, false, false, false, RoomType.CHALLENGE_END,2,0);

        RoomManager.rooms[0][0].add(new PlayerController(new Vector3(200,300,0)));

        RoomManager.rooms[0][0].add(RoomManager.speechManager);

        PlayerController.skills = new double[]{1,1,1,1};
        RoomManager.rooms[0][0].add(new WeaponSpawn(new Vector3(500,300,0), new GameImage(BOOMERANG_IMAGE_PATH,64,64), new Boomerang(new Vector3(500,300)), false, 0, RoomManager.rooms[0][0], new Vector2(0,0)));
        RoomManager.rooms[0][2].add(new ItemSpawn(new Vector3(600,300), new GameImage("bin/images/heart.png"), ItemType.HEALTH, false, 0, RoomManager.rooms[2][0], 50, "50 Health", new Vector2(10,0)));
        RoomManager.rooms[1][1].add(new ItemSpawn(new Vector3(600,300), new GameImage("bin/images/heart.png"), ItemType.HEALTH, false, 0, RoomManager.rooms[2][0], 50, "50 Health", new Vector2(10,0)));

        RoomManager.rooms[0][1].add(new Doctor(new Vector3(900,300), 0,1));

        RoomManager.rooms[1][2].add(new Wizard(new Vector3(700,200)), new Wizard(new Vector3(300,200)),new Wizard(new Vector3(500,500)));

        RoomManager.rooms[1][0].add(new Follower(new Vector3(700,200)), new Follower(new Vector3(300,200)),new Follower(new Vector3(500,500)));

        RoomManager.challengeEnemyCount = 7;
        SceneManager.getWindow().setBackgroundColor(ColorManager.backgroundColor);
        PlayerController.instance.setMaxHealth(50);
        PlayerController.instance.heal(50);
        PlayerController.instance.setPlayerLevel(1);
    }
}
