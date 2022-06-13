package com.dungeoncrawler.Scenes.Challenges;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Enemies.Bosses.Graveyard;
import com.dungeoncrawler.Entities.Enemies.Bosses.Knight;
import com.dungeoncrawler.Entities.Enemies.Bosses.Turret;
import com.dungeoncrawler.Entities.Items.ItemSpawn;
import com.dungeoncrawler.Entities.Items.ItemType;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Entities.Weapons.Projectile.BarrettM82;
import com.dungeoncrawler.Entities.Weapons.Projectile.Bow;
import com.dungeoncrawler.Entities.Weapons.WeaponSpawn;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.Scenes.Rooms.Room;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
import com.dungeoncrawler.Scenes.Rooms.RoomType;
import com.dungeoncrawler.Speech.SpeechManager;

import static com.dungeoncrawler.Entities.Weapons.WeaponStats.BOW_IMAGE_PATH;

public class ChallengeOne {
    public static void Create(){
        RoomManager.currentRoomY = 0;
        RoomManager.currentRoomX = 0;
        RoomManager.inChallenge = true;
        RoomManager.speechManager = new SpeechManager();

        RoomManager.width = 5;
        RoomManager.height = 1;
        RoomManager.rooms = new Room[RoomManager.width][RoomManager.height];
        RoomManager.rooms[0][0] = new Room(0, false, true, false, false, RoomType.SPAWN,0,0);
        RoomManager.rooms[1][0] = new Room(0, true, true, false, false, RoomType.SPAWN,1,0);
        RoomManager.rooms[2][0] = new Room(0, true, true, false, false, RoomType.SPAWN,2,0);
        RoomManager.rooms[3][0] = new Room(0, true, true, false, false, RoomType.SPAWN,3,0);
        RoomManager.rooms[4][0] = new Room(0, true, false, false, false, RoomType.CHALLENGE_END,4,0);

        RoomManager.rooms[0][0].add(new PlayerController(new Vector3(200,300,0)));

        RoomManager.rooms[0][0].add(RoomManager.speechManager);

        PlayerController.skills = new double[]{1,1,1,1};
        RoomManager.rooms[0][0].add(new WeaponSpawn(new Vector3(900,300,0), new GameImage(BOW_IMAGE_PATH,64,64), new Bow(new Vector3(900,300)), false, 0, RoomManager.rooms[0][0], new Vector2(0,0)));
        RoomManager.rooms[1][0].add(new Knight(new Vector3(600,200), 1,0));
        RoomManager.rooms[2][0].add(new ItemSpawn(new Vector3(600,300), new GameImage("bin/images/heart.png"), ItemType.HEALTH, false, 0, RoomManager.rooms[2][0], 50, "50 Health", new Vector2(10,0)));
        RoomManager.rooms[3][0].add(new Knight(new Vector3(900,300), 3,0), new Turret(new Vector3(600,300),3,0));


        SceneManager.getWindow().setBackgroundColor(ColorManager.backgroundColor);
        PlayerController.instance.setMaxHealth(50);
        PlayerController.instance.heal(50);
        PlayerController.instance.setPlayerLevel(1);
    }
}
