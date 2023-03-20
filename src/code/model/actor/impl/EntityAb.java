package code.model.actor.impl;

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
public abstract class EntityAb implements Entity {
    final private Icon eSprite;
    private EntityType eType;
    private Optional<Tile> eTile;
    protected boolean canMove;
    protected boolean isAlive;
    
    public EntityAb(EntityType type){
        this.eType = type;
        this.eSprite = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../../../resources/entities/" + type.name() + ".JPG")));
        this.isAlive = true;
    }

    public void setTile(Optional<Tile> tile){
        this.eTile = tile;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityAb entityAb = (EntityAb) o;
        return eType.equals(entityAb.eType) && eTile.equals(entityAb.eTile);
    }
    public boolean isAlive(){
        return this.isAlive;
    }

    public void setLifeTo(boolean flag){
        this.isAlive = flag;
    }

    //Logic of each subType of entity here
    public abstract boolean canMove();


    
}
