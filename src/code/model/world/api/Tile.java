package code.model.world.api;



import code.model.world.impl.TileType;
import code.model.actor.api.Entity;

import javax.swing.*;
import java.util.Optional;

public interface Tile {
    
    //Getters
    Position2D getCoords();
    TileType getTileType();
    Icon getImage();

    Optional<? extends Entity> getEntity();

    //Setters
    void setEntity(Entity entity);

    //Utility
    boolean isAdjacentTo(Tile otherTile);
    void resetTile();
}

