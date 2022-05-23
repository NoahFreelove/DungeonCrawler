package com.dungeoncrawler.GameObjects;

import com.JEngine.Components.Colliders.BoxCollider_Comp;
import com.JEngine.Components.DontDestroyOnLoad_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Player;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.Input;
import com.dungeoncrawler.GameObjects.Weapons.Sword;
import com.dungeoncrawler.GameObjects.Weapons.Weapon;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
import com.dungeoncrawler.Scenes.ColorManager;
import com.dungeoncrawler.SimpleDirection;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class PlayerController extends Player {

    private String name = "Player";
    private int gold = 0;
    private int level = 1;
    private int exp = 0;
    private int expToNextLevel = 10;
    private double health = 10;
    private int maxHealth = 10;
    private int roomsCleared = 0;

    private float moveSpeed = 15f;

    public static PlayerController instance;
    private Text roomNumber = new Text("Room Number");
    private Group playerUI = new Group();

    private Weapon selectedWeapon;
    private Vector3 weaponOffset = new Vector3(0,0,0);
    private Vector3 targetWeaponOffset = new Vector3(0,0,0);
    private SimpleDirection facing = SimpleDirection.LEFT;
    private float animationTime = 0f;

    public PlayerController(Vector3 pos, String name, int initLevel, int initGold, int initExp, int roomsCleared) {
        super(Transform.simpleTransform(pos), new GameImage("bin/player.png"), new Identity("PlayerController", "player"));
        if(instance == null)
            instance = this;
        else
            return;
        this.name = name;
        this.maxHealth = 10+initLevel;
        this.health = maxHealth;
        this.gold = initGold;
        this.exp =initExp;
        this.expToNextLevel = 10*initLevel;
        this.roomsCleared = roomsCleared;
        addCollider(new BoxCollider_Comp(Vector3.emptyVector(), 64, 64, false, this));
        addComponent(new DontDestroyOnLoad_Comp());
        setupUI();
    }

    public PlayerController(Vector3 pos) {
        super(Transform.simpleTransform(pos), new GameImage("bin/player.png"), new Identity("PlayerController", "player"));
        if(instance == null)
            instance = this;
        else
            return;
        addCollider(new BoxCollider_Comp(Vector3.emptyVector(), 64, 64, false, this));
        addComponent(new DontDestroyOnLoad_Comp());
        setupUI();
        setSelectedWeapon(new Sword(pos, new GameImage("bin/sword.png")));
        SceneManager.getActiveScene().add(selectedWeapon);
        addChild(selectedWeapon);
    }

    @Override
    public void Update(){
        super.Update();
        if(selectedWeapon!=null) {
            selectedWeapon.setPosition(getPosition().add(weaponOffset));
            weaponOffset = GameMath.interpolateClamped(weaponOffset, targetWeaponOffset, animationTime);
            animationTime += 0.1f;
            if (animationTime >= 1f)
            {
                animationTime = 0f;
                targetWeaponOffset = Vector3.emptyVector();
            }
        }
        roomNumber.setText("Room " + RoomManager.currentRoomX + ":" + RoomManager.currentRoomY);
        //System.out.println(getPosition());
        if(Input.Up)
        {
            Move(new Vector2(0,-1), moveSpeed);
            facing = SimpleDirection.UP;
        }
        if(Input.Down)
        {
            Move(new Vector2(0,1), moveSpeed);
            facing = SimpleDirection.DOWN;
        }

        if(Input.Left)
        {
            Move(new Vector2(-1,0), moveSpeed);
            facing = SimpleDirection.LEFT;
        }
        if(Input.Right)
        {
            Move(new Vector2(1,0), moveSpeed);
            facing = SimpleDirection.RIGHT;
        }

        if(getPosition().x <=0)
        {
            RoomManager.switchRoom(-1,0);
            PlayerController.instance.setPosition(new Vector3(1100,300,0));
        }
        if(getPosition().x >= 1280)
        {
            RoomManager.switchRoom(1,0);
            PlayerController.instance.setPosition(new Vector3(100,300,0));
        }
        if(getPosition().y <=0 )
        {
            RoomManager.switchRoom(0,1);
            PlayerController.instance.setPosition(new Vector3(580,600,0));
        }
        if(getPosition().y >= 720)
        {
            RoomManager.switchRoom(0,-1);
            PlayerController.instance.setPosition(new Vector3(580,50,0));
        }

    }

    public void addGold(int amount) {
        gold += amount;
    }
    public void removeGold(int amount) {
        gold -= amount;
    }

    public void addExp(int amount) {
        exp += amount;
        if(exp >= expToNextLevel) {
            level++;
            expToNextLevel = (int) (expToNextLevel + 10);
            maxHealth += 1;
            health = maxHealth;
        }
    }
    public void removeExp(int amount) {
        exp -= amount;
    }
    public void takeDamage(double amount) {
        health -= amount;
    }
    public void heal(double amount) {
        health += amount;
        if(health > maxHealth) {
            health = maxHealth;
        }
    }

    public int getGold() {
        return gold;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    public double getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getName() {
        return name;
    }

    public void clearRoom(){
        roomsCleared++;
    }
    public int getRoomsCleared() {
        return roomsCleared;
    }

    @Override
    public String toString(){
        return String.format("Player: %s\nLevel: %d\nGold: %d\nExp: %d\nExp to next level: %d\nHealth: %d/%d\nRooms cleared: %d", name, level, gold, exp, expToNextLevel, (int)health, maxHealth, roomsCleared);
    }

    private void setupUI(){
        roomNumber = new Text();
        roomNumber.setTranslateX(10);
        roomNumber.setTranslateY(40);
        roomNumber.setFill(ColorManager.textColor);
        roomNumber.setStyle("-fx-font-family: 'Arial';-fx-font-size: 25px;");
        playerUI.getChildren().add(roomNumber);
        GameWindow.getInstance().addPermanentUI(playerUI);
    }

    @Override
    public void onKeyPressed(KeyCode k)
    {
        if(k == KeyCode.SHIFT) {
            if (selectedWeapon.requestAttack(facing))
            {
                setWeaponAnimatePos();
                animationTime = 0;
            }
        }
    }

    public void setSelectedWeapon(Weapon w) {
        if(selectedWeapon != null)
        {
            SceneManager.getActiveScene().remove(selectedWeapon);
        }
        selectedWeapon = w;
        SceneManager.getActiveScene().add(w);
    }

    private void setWeaponAnimatePos(){
        if(facing == SimpleDirection.UP)
        {
            targetWeaponOffset = new Vector3(0,-32,0);
        }
        else if(facing == SimpleDirection.DOWN)
        {
            targetWeaponOffset = new Vector3(0,32,0);
        }
        else if(facing == SimpleDirection.LEFT)
        {
            targetWeaponOffset = new Vector3(-32,0,0);
        }
        else if(facing == SimpleDirection.RIGHT)
        {
            targetWeaponOffset = new Vector3(32,0,0);
        }
    }
}
