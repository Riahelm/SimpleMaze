package code.model.world.impl;

import code.exceptions.AbsentEntityException;
import code.model.actor.api.Entity;
import code.model.world.api.Position2D;
import code.model.world.api.Tile;

import javax.swing.*;
import java.util.Objects;
import java.util.Optional;

public class TileImpl implements Tile {
    
    private final Position2D myCoords;
    private final TileType myTileType;
    private final Icon myIcon;
    private Optional<? extends Entity> entityOnTile;
    
    public TileImpl(Position2D coords, TileType type){
        this.myCoords = coords;
        this.myTileType = type;
        this.entityOnTile = Optional.empty();
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

    public Optional<? extends Entity> getEntity(){
        return this.entityOnTile;
    }

    public void setEntity(Entity entity){
        if(this.entityOnTile.isEmpty()){
            this.entityOnTile = Optional.of(entity);
        }
    }

    @Override
    public boolean isAdjacentTo(Tile otherTile) {
        return Math.abs(otherTile.getCoords().getPosX() - this.getCoords().getPosX()) +
                Math.abs(otherTile.getCoords().getPosY() - this.getCoords().getPosY()) <= 1;
    }

    public void resetTile(){
        this.entityOnTile = Optional.empty();
    }

    @Override
    public String toString() {
        return "TileImpl [myCoords=" + myCoords + ", myTileType=" + myTileType + ", entityOnTile=" + entityOnTile + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TileImpl tile = (TileImpl) o;

        if (!myCoords.equals(tile.myCoords)) return false;
        if (myTileType != tile.myTileType) return false;
        return Objects.equals(entityOnTile, tile.entityOnTile);
    }
}
