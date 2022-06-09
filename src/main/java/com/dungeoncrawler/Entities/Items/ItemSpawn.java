package com.dungeoncrawler.Entities.Items;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.dungeoncrawler.Entities.Player.Abilities.AbilityType;
import com.dungeoncrawler.Entities.Player.Abilities.Shield;
import com.dungeoncrawler.Entities.Player.PlayerController;
import com.dungeoncrawler.Scenes.ColorManager;
import javafx.scene.text.Text;

public class ItemSpawn extends Pawn {
    private final ItemSpawnCollider itemSpawnCollider;
    private final boolean toBeBought;
    private final int cost;
    private final ItemType itemType;
    private final int quantity;
    private final String name;
    private final Text priceTag;

    private boolean hasBeenPickedUp = false;

    public ItemSpawn(Vector3 pos, GameImage newSprite, ItemType parent, boolean toBeBought, int cost, GameScene scene, int quantity, String name, Vector2 offsetFromPrice) {
        super(Transform.simpleTransform(pos.add(offsetFromPrice)), newSprite, new Identity("item spawn " + name));
        this.toBeBought = toBeBought;
        this.cost = cost;
        this.itemType = parent;
        this.quantity = quantity;
        this.name = name;
        itemSpawnCollider = new ItemSpawnCollider(Vector3.emptyVector(), newSprite.getWidth(), newSprite.getHeight(), this);
        addCollider(itemSpawnCollider);
        priceTag = new Text(name + " : Price: "  + cost);
        priceTag.setFill(ColorManager.textColor);
        priceTag.setX(pos.x-10);
        priceTag.setY(pos.y-10);
        if(toBeBought)
        {
            scene.addUI(priceTag);
        }
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
            itemSpawnCollider.setActive(false);
            SceneManager.getActiveScene().remove(this);
            SceneManager.getActiveScene().removeUI(priceTag);
            doAction();
        }
    }

    private void doAction(){
        switch (itemType)
        {
            case GOLD -> {
                PlayerController.instance.addGold(quantity);
            }
            case HEALTH -> {
                PlayerController.instance.heal(quantity);
            }
            case SHIELD -> {
                PlayerController.instance.addShield(new Shield(quantity));
            }
            case ABILITY -> {
                switch (name)
                {
                    case "Fire Ability" -> {
                        PlayerController.instance.setSuperAbility(AbilityType.FIRE);
                    }
                    case "Freeze Ability" -> {
                        PlayerController.instance.setSuperAbility(AbilityType.FREEZE);
                    }
                    case "Shield Ability" -> {
                        PlayerController.instance.setSuperAbility(AbilityType.SHIELD);
                    }
                }
            }
        }
    }

}
