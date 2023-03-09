package code.model.actor.impl;

import code.model.world.api.Tile;
import code.model.actor.api.Entity;

import javax.swing.*;

/**
 *
 * @author Nicolas
 */
public abstract class EntityAb implements Entity {

    final private String cName;
    final private Icon cSprite;
    private Tile currentTile;
    protected boolean canMove;
    protected boolean canDie;
    
    public EntityAb(String name, Tile startingTile){
        this.cName = name;
        this.currentTile = startingTile;
        this.canMove = true;
        this.canDie = true;
        //TODO fix this.
        this.cSprite = new ImageIcon(this.getClass().getResource("../../../../resources/entities/" + name + ".JPG"));
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

    public Tile getTile(){
        return this.currentTile;
    }
    
    //Logic of each sub-type of entity here
    public abstract boolean canMove();
   
    public abstract boolean canDie();

    
}
