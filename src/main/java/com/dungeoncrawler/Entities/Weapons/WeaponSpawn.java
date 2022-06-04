package com.dungeoncrawler.Entities.Weapons;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;

import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Scenes.ColorManager;

import javafx.scene.text.Text;

public class WeaponSpawn extends Pawn {
    private final WeaponSpawnCollider weaponSpawnCollider;
    private final boolean toBeBought;
    private final int cost;
    private final Weapon weapon;
    private final Text priceTag;

    private boolean hasBeenPickedUp = false;

    public WeaponSpawn(Vector3 pos, GameImage newSprite, Weapon parent, boolean toBeBought, int cost, GameScene scene) {
        super(Transform.simpleTransform(pos), newSprite, new Identity("weapon spawn " + parent.getClass().getSimpleName()));
        this.toBeBought = toBeBought;
        this.cost = cost;
        this.weapon = parent;
        weaponSpawnCollider = new WeaponSpawnCollider(Vector3.emptyVector(), newSprite.getWidth(), newSprite.getHeight(), this);
        addCollider(weaponSpawnCollider);
        priceTag = new Text("Price: "  + cost);
        priceTag.setFill(ColorManager.importantText);
        priceTag.setX(pos.x-10);
        priceTag.setY(pos.y-10);
        scene.addUI(priceTag);
    }

    public void Buy(){
        if(hasBeenPickedUp)
            return;
        if(PlayerController.instance !=null)
        {
            if(toBeBought)
            {
                if(PlayerController.instance.getGold() >= cost)
                {
                    PlayerController.instance.buyItem(cost);
                }
                else {
                    return;
                }
            }
            hasBeenPickedUp = true;
            setActive(false);
            weaponSpawnCollider.setActive(false);
            SceneManager.getActiveScene().remove(this);
            SceneManager.getActiveScene().removeUI(priceTag);
            PlayerController.instance.setSelectedWeapon(weapon);
        }
    }

}
