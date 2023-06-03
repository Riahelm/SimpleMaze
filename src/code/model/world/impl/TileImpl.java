package code.model.world.impl;

import code.model.world.api.Position2D;
import code.model.world.api.Tile;

import javax.swing.*;
import java.util.Objects;

public class TileImpl implements Tile {
    
    private final Position2D myCoords;
    private final TileType myTileType;
    private final Icon myIcon;

    public TileImpl(Position2D coords, TileType type){
        this.myCoords = coords;
        this.myTileType = type;
        this.myIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../../../resources/tiles/" + myTileType + ".JPG")));
    }

    public Position2D getCoords() {
        return myCoords;
    }

    public TileType getTileType() {
        return myTileType;
    }

    @Override
    public Icon getImage() {
        return this.myIcon;
    }

    @Override
    public boolean isAdjacentTo(Tile otherTile) {
        //Fun maths ahead, it checks if the tile is in the first next cardinal direction
        return Math.abs(otherTile.getCoords().getPosX() - this.getCoords().getPosX()) +
               Math.abs(otherTile.getCoords().getPosY() - this.getCoords().getPosY()) <= 1;
    }

    @Override
    public String toString() {
        return "TileImpl [myCoords=" + myCoords + ", myTileType=" + myTileType + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TileImpl tile)) return false;
        return myCoords.equals(tile.myCoords) && myTileType == tile.myTileType;
    }
}
