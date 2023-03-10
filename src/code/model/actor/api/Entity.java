package code.model.actor.api;

import code.model.actor.impl.EntityType;
import code.model.world.api.Tile;
import code.model.world.impl.TileType;

import javax.swing.*;

public interface Entity {

    void setTile(Tile tile);
    
    String getName();

    Tile getTile();

    Icon getSprite();

    EntityType getType();

    @Override
    boolean equals(Object anObj);
    
    boolean canMove();
   
    boolean canDie();


}

