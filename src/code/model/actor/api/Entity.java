package code.model.actor.api;

import code.model.world.api.Tile;

public interface Entity {

    public void setTile(Tile tile);
    
    public String getName();

    public Integer getId();

    public Tile getTile();
    
    public boolean canMove();
   
    public boolean canDie();
    
    public Tile getCurrentTile();

}

