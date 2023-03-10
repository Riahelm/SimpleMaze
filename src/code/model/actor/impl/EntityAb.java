package code.model.actor.impl;

import code.model.world.api.Tile;
import code.model.actor.api.Entity;
import code.view.Directions;

import javax.swing.*;

/**
 *
 * @author Nicolas
 */
public abstract class EntityAb implements Entity {

    final private String cName;
    final private Icon cSprite;

    private EntityType cType;
    private Tile currentTile;
    protected boolean canMove;
    protected boolean canDie;
    
    public EntityAb(String name, Tile startingTile){
        this.cName = name;
        this.cSprite = new ImageIcon(this.getClass().getResource("../../../../resources/entities/" + name + ".JPG"));
        this.cType = EntityType.valueOf(name);
        this.currentTile = startingTile;
        this.canMove = true;
        this.canDie = true;
    }
    
    public void setTile(Tile tile){
        this.currentTile = tile;
    }
    
    public String getName() {
        return this.cName;
    }

    public Icon getSprite(){
        return this.cSprite;
    }

    public EntityType getType(){
        return this.cType;
    }

    public Tile getTile(){
        return this.currentTile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityAb entityAb = (EntityAb) o;
        return cName.equals(entityAb.cName) && currentTile.equals(entityAb.currentTile);
    }

    //Logic of each subType of entity here
    public abstract boolean canMove();
   
    public abstract boolean canDie();

    
}
