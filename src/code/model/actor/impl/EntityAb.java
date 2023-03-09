package code.model.actor.impl;

import code.model.world.api.Tile;
import code.model.actor.api.Entity;

/**
 *
 * @author Nicolas
 */
public abstract class EntityAb implements Entity {

    final private String cName;
    final private Integer cId;
    private Tile currentTile;
    protected boolean canMove;
    protected boolean canDie;
    
    public EntityAb(String name, Tile startingTile, Integer id){
        this.cName = name;
        this.cId = id;
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

    public Integer getId() {
        return this.cId;
    }

    public Tile getTile(){
        return this.currentTile;
    }
    
    //Logic of each sub-type of entity here
    public abstract boolean canMove();
   
    public abstract boolean canDie();
    
    public Tile getCurrentTile(){
        return this.currentTile;
    }
    
}
