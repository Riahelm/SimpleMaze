package code.model.actor.impl;

import code.model.world.api.Tile;
import code.model.actor.api.Entity;

import javax.swing.*;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 *
 * @author Nicolas
 */
public abstract class EntityAb implements Entity {

    final private String eName;
    final private Icon eSprite;

    private EntityType eType;
    private Optional<Tile> eTile;
    protected boolean canMove;
    protected boolean canDie;
    
    public EntityAb(String name){
        this.eName = name;
        this.eSprite = new ImageIcon(this.getClass().getResource("../../../../resources/entities/" + name + ".JPG"));
        this.eType = EntityType.valueOf(name);
    }
    public EntityAb(String name, Tile startingTile){
        this(name);
        this.eTile = Optional.of(startingTile);
    }


    public void setTile(Tile tile){
        this.eTile = Optional.of(tile);
    }
    
    public String getName() {
        return this.eName;
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
        return eName.equals(entityAb.eName) && eTile.equals(entityAb.eTile);
    }

    //Logic of each subType of entity here
    public abstract boolean canMove();
   
    public abstract boolean canDie();

    
}
