package code.model.actor.api;

import code.model.world.api.Tile;

import javax.swing.*;

public interface Entity {

    public void setTile(Tile tile);
    
    public String getName();

    public Tile getTile();

    public Icon getSprite();
    
    public boolean canMove();
   
    public boolean canDie();


}

