package code.model.actor.api;

import code.model.world.api.Tile;

import javax.swing.*;

public interface Entity {

    void setTile(Tile tile);
    
    String getName();

    Tile getTile();

    Icon getSprite();
    
    boolean canMove();
   
    boolean canDie();


}

