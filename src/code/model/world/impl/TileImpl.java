package code.model.world.impl;

import code.exceptions.AbsentEntityException;
import code.exceptions.EntityAlreadyPresentException;
import code.model.actor.api.Entity;
import code.model.actor.impl.EntityFactory;
import code.model.world.api.Position2D;
import code.model.world.api.Tile;

import javax.swing.*;
import java.util.Objects;
import java.util.Optional;

public class TileImpl implements Tile {
    
    private final Position2D myCoords;
    private TileType myTileType;
    private final Icon myIcon;
    private Optional<Entity> entityOnTile;
    
    public TileImpl(Position2D coords, TileType type){
        this.myCoords = coords;
        this.myTileType = type;
        myIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../../../resources/tiles/" + myTileType + ".JPG")));
        this.entityOnTile = Optional.empty();
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

    public Optional<Entity> getEntity() throws AbsentEntityException {
        return this.entityOnTile;
    }

    public void setEntity(Entity entity){
        if(!this.entityOnTile.isPresent()){
            this.entityOnTile = Optional.of(entity);
        }
    }

    @Override
    public boolean isAdjacentTo(Tile otherTile) {
        return Math.abs(otherTile.getCoords().getPosX() - this.getCoords().getPosX()) -
                Math.abs(otherTile.getCoords().getPosY() - this.getCoords().getPosY()) <= 1;
    }

    public void resetTile(){
        this.entityOnTile = Optional.empty();
    }

    @Override
    public String toString() {
        return "TileImpl [myCoords=" + myCoords + ", myTileType=" + myTileType + ", entityOnTile=" + entityOnTile + "]";
    }

}
