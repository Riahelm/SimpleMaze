package code.model.actor.api;

import code.model.actor.impl.EntityType;
import code.model.world.api.Tile;

import javax.swing.*;

public interface Entity {

    void setTile(Tile tile);
    Tile getTile();

    Icon getSprite();
    EntityType getType();

    @Override
    boolean equals(Object anObj);

    boolean canMove();

    boolean isAlive();
    void setLifeTo(boolean flag);
}

