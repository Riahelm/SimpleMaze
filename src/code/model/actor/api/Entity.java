package code.model.actor.api;

import code.model.world.api.Tile;

import javax.swing.*;

public interface Entity {

    void setTile(Tile tile);
    
    String getName();

    Tile getTile();

    Icon getSprite();

    @Override
    boolean equals(Object anObj);
    
    boolean canMove();
   
    boolean canDie();


}

