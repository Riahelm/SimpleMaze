package code.model.world.api;



import code.model.world.impl.TileType;

import javax.swing.*;

public interface Tile {
    
    //Getters
    Position2D getCoords();
    TileType getTileType();
    Icon getImage();

    //Utility
    boolean isAdjacentTo(Tile otherTile);
}

