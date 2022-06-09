package com.dungeoncrawler.Entities.Player;

import com.JEngine.Components.DontDestroyOnLoad_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.*;
import com.JEngine.Game.PlayersAndPawns.Player;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.ImageProcessingEffects.ShakeScreen;
import com.JEngine.Utility.Input;
import com.JEngine.Utility.Misc.GameTimer;
import com.dungeoncrawler.Entities.Player.Abilities.*;
import com.dungeoncrawler.Entities.Weapons.*;
import com.dungeoncrawler.Entities.Weapons.Melee.Boomerang;
import com.dungeoncrawler.Entities.Weapons.Melee.Sword;
import com.dungeoncrawler.Entities.Weapons.Projectile.BBGun;
import com.dungeoncrawler.Entities.Weapons.Projectile.BarrettM82;
import com.dungeoncrawler.Entities.Weapons.Projectile.Bow;
import com.dungeoncrawler.Main;
import com.dungeoncrawler.Scenes.Rooms.RoomManager;
import com.dungeoncrawler.Scenes.ColorManager;
import com.JEngine.Core.Position.SimpleDirection;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class PlayerController extends Player {
    public static PlayerController instance;

    // Player Stats
    private String name = "Player";
    private int gold = 0;
    private int gameLevel = 1;
    public static int gameLevelToWin = 20;
    private int playerLevel = 1;
    private int exp = 0;
    private int expToNextLevel = 10;
    private int skillPoints = 0;
    private double health = 20;
    private int maxHealth = 20;
    private int roomsCleared = 0;
    private boolean hasShield = true;
    public Shield shieldInstance;

    private SimpleDirection directionFacing = SimpleDirection.LEFT;
    private float moveSpeed = 15f;
    private boolean isAttacking;
    private Weapon selectedWeapon;
    private boolean wasdMovement = true;

    private double superCharge = 1;
    private AbilityType superAbility = AbilityType.SHIELD;

    // UI
    PlayerUI playerUI = new PlayerUI(health, gold, playerLevel, exp, expToNextLevel, gameLevel);

    public PlayerController(Vector3 pos, String name, int gameLevel, int initLevel, int initGold, int initExp, String initWeapon) {
        super(Transform.simpleTransform(pos), new GameImage("bin/player.png"), new Identity("PlayerController", "player"));
        if(instance == null)
            instance = this;
        else
            return;
        this.name = name;
        this.gameLevel = gameLevel;
        this.playerLevel = initLevel;
        this.maxHealth = 10+(initLevel*2);
        this.health = maxHealth;
        this.gold = initGold;
        this.exp = initExp;
        this.expToNextLevel = 5*initLevel;

        switch (initWeapon)
        {
            case "Sword"-> setSelectedWeapon(new Sword(getPosition()));
            case "Boomerang"-> setSelectedWeapon(new Boomerang(getPosition()));
            case "BBGun"-> setSelectedWeapon(new BBGun(getPosition()));
            case "Bow" -> setSelectedWeapon(new Bow(getPosition()));
            case "BarrettM82" -> setSelectedWeapon(new BarrettM82(getPosition()));
        }

        addCollider(new PlayerCollider(Vector3.emptyVector(), 64, 64, this));
        addComponent(new DontDestroyOnLoad_Comp());
    }

    public PlayerController(Vector3 pos) {
        super(Transform.simpleTransform(pos), new GameImage("bin/player.png"), new Identity("PlayerController", "player"));
        if(instance == null)
            instance = this;
        else
            return;
        addComponents(new DontDestroyOnLoad_Comp(), new PlayerCollider(Vector3.emptyVector(), 64, 64, this));
        setSelectedWeapon(new Sword(getPosition()));
        SceneManager.getActiveScene().add(selectedWeapon);
    }

    @Override
    public void Update() {
        super.Update();

        if(health<= 0)
        {
            health = 0;
            removePlayer();
            return;
        }

        checkInput();
        playerUI.UpdateUI(health, gold, playerLevel, exp, expToNextLevel, gameLevel);

        if (selectedWeapon != null)
        {
            if (Input.Shift_Pressed) {
                selectedWeapon.requestAttack(directionFacing);
            }
            float flipOffset = 180;

            selectedWeapon.updateRotation(new Vector2(DirectionAngleConversion.dirToAngle(directionFacing), flipOffset));
        }
        checkMoveRoom();
    }

    private void checkInput() {
        if (Input.Up) {
            if (Input.UArrow_Pressed) {
                MoveUp();
                if (wasdMovement) {
                    directionFacing = SimpleDirection.UP;
                }
            }
            else if (Input.W_Pressed) {
                directionFacing = SimpleDirection.UP;
                if (wasdMovement) {

                    MoveUp();
                }
            }
        }
        if(Input.Down)
        {
            if (Input.DArrow_Pressed) {
                MoveDown();
                if (wasdMovement) {
                    directionFacing = SimpleDirection.DOWN;
                }
            }
            else if (Input.S_Pressed) {
                directionFacing = SimpleDirection.DOWN;
                if (wasdMovement) {
                    MoveDown();
                }
            }
        }
        if(Input.Left)
        {
            if (Input.LArrow_Pressed) {
                MoveLeft();
                if (wasdMovement) {
                    directionFacing = SimpleDirection.LEFT;
                }
            }else if (Input.A_Pressed) {
                directionFacing = SimpleDirection.LEFT;
                if (wasdMovement) {
                    MoveLeft();
                }
            }
        }

        if(Input.Right){
            if (Input.RArrow_Pressed) {
                MoveRight();
                if (wasdMovement) {
                    directionFacing = SimpleDirection.RIGHT;
                }
            }
            else if (Input.D_Pressed) {
                directionFacing = SimpleDirection.RIGHT;
                if (wasdMovement) {
                    MoveRight();
                }
            }
        }
    }

    private void checkMoveRoom(){
        if(getPosition().x <=0)
        {
            RoomManager.switchRoom(-1,0);
            PlayerController.instance.setPosition(new Vector3(1100,310,0));
        }
        if(getPosition().x >= 1280)
        {
            RoomManager.switchRoom(1,0);
            PlayerController.instance.setPosition(new Vector3(150,310,0));
        }
        if(getPosition().y <=0 )
        {
            RoomManager.switchRoom(0,1);
            PlayerController.instance.setPosition(new Vector3(580,550,0));
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
            playerLevel++;
            skillPoints++;
            expToNextLevel = 5*playerLevel;
            maxHealth += 2;
            health = maxHealth;
            exp = 0;
        }
    }
    public void removeExp(int amount) {
        exp -= amount;
    }
    public void takeDamage(double amount) {
        if(amount <= 0)
        {
            return;
        }
        if(hasShield)
        {
            if(shieldInstance != null)
            {
                shieldInstance.hit();
                return;
            }
        }
        health -= amount;

        playHitEffect();
    }
    public void playHitEffect(){
        GameTimer hurtEffect = new GameTimer(150, args -> getSprite().setColorAdjust(new ColorAdjust()),true);
        getSprite().setColorAdjust(new ColorAdjust(1,1,0.5,1));
        hurtEffect.start();
        ShakeScreen.shake(25, 0.150);
        addSuperCharge(0.05);
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

    public int getPlayerLevel() {
        return playerLevel;
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
        return String.format("Player: %s\nLevel: %d\nGold: %d\nExp: %d\nExp to next level: %d\nHealth: %d/%d\nRooms cleared: %d", name, playerLevel, gold, exp, expToNextLevel, (int)health, maxHealth, roomsCleared);
    }

    public void setSelectedWeapon(Weapon w) {
        if(selectedWeapon != null)
        {
            SceneManager.getActiveScene().remove(selectedWeapon);
        }
        selectedWeapon = w;
        SceneManager.getActiveScene().add(w);
    }

    @Override
    public void onKeyReleased(KeyCode key) {
        if(key == KeyCode.ENTER || key == KeyCode.Q){
            useSuper();
        }
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public SimpleDirection getDirectionFacing() {
        return directionFacing;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    private void MoveUp(){
        Move(new Vector2(0,-1), moveSpeed);
    }
    private void MoveDown(){
        Move(new Vector2(0,1), moveSpeed);
    }
    private void MoveLeft(){
        Move(new Vector2(-1,0), moveSpeed);
    }
    private void MoveRight(){
        Move(new Vector2(1,0), moveSpeed);
    }

    public Weapon getSelectedWeapon() {
        return selectedWeapon;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public void incrementGameLevel(){
        this.gameLevel++;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
    public void buyItem(int itemCost)
    {
        this.gold -= itemCost;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public static void removePlayer(){
        Platform.runLater(() -> {
            SceneManager.getActiveScene().remove(PlayerController.instance);
            PlayerController.instance = null;
            Main.createMainMenu();
        });
    }

    public void addShield(Shield instance) {
        this.hasShield = true;
        this.shieldInstance = instance;
        SceneManager.getActiveScene().add(instance);
    }
    public void removeShield(){
        this.hasShield = false;
        this.shieldInstance.destroy();
        this.shieldInstance = null;
    }

    private void useSuper(){
        if(gameLevel<6)
        {
            return;
        }
        if(superCharge>=1) {
            if (superAbility != null)
            {
                switch (superAbility)
                {
                    case FIRE-> new FireAbility();
                    case FREEZE -> new FreezeAbility();
                    case SHIELD -> new ShieldAbility();
                }
                //superCharge = 0;
            }
        }
    }
    private void addSuperCharge(double amount) {
        superCharge += GameMath.clamp(0, 1, (float) amount);
        if (superCharge > 1)
        {
            superCharge = 1;
        }
    }

    public void setSuperAbility(AbilityType ability)
    {
        this.superAbility = ability;
    }

    public void onHurtEnemy(){
        addSuperCharge(0.02);
    }
}
