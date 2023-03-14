package code.model.actor.api;

import code.model.actor.impl.EntityType;
import code.model.world.api.Tile;

import javax.swing.*;
import java.util.NoSuchElementException;

public interface Entity {

    void setTile(Tile tile);
    
    String getName();

    Tile getTile() throws NoSuchElementException;

    Icon getSprite();

    @Override
    boolean equals(Object anObj);
    
    boolean canMove();
   
    boolean canDie();


    EntityType getType();
}

