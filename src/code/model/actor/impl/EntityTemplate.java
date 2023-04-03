package code.model.actor.impl;

import code.controller.GameChatController;
import code.controller.GameController;
import code.model.world.api.Tile;
import code.model.actor.api.Entity;

import javax.swing.*;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author Nicolas
 */
public abstract class EntityTemplate implements Entity {
    final private Icon eSprite;
    private final EntityType eType;
    private Optional<Tile> eTile;
    protected boolean isAlive;

    //I put these for the sake of not having verbose code
    protected GameChatController gCC;
    protected GameController gc;

    protected EntityTemplate(EntityType type){
        this.eType = type;
        this.eSprite = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../../../resources/entities/" + type.name() + ".JPG")));
        this.isAlive = true;
        gCC = GameChatController.getInstance();
        gc  = GameController.getInstance();
    }

    public void setTile(Tile tile){
        this.eTile = Optional.of(tile);
    }

    public Icon getSprite(){
        return this.eSprite;
    }

    public EntityType getType(){
        return this.eType;
    }

    public Tile getTile() throws NoSuchElementException{
        return eTile.get();
    }

    public boolean isAlive(){
        return this.isAlive;
    }

    public void setLifeTo(boolean flag){
        this.isAlive = flag;
    }

    //Logic of each subType of entity here
    public abstract boolean canMove();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityTemplate entityTemplate = (EntityTemplate) o;
        return eType.equals(entityTemplate.eType) && eTile.equals(entityTemplate.eTile);
    }

}
