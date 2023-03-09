package code.model.world.impl;

import code.exceptions.AbsentEntityException;
import code.exceptions.EntityAlreadyPresentException;
import code.model.actor.api.Entity;
import code.model.world.api.Position2D;
import code.model.world.api.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class TileImpl implements Tile {
    
    private final Position2D myCoords;
    private TileType myTileType;
    private final Image myIcon;
    private Optional<Entity> entityOnTile;
    
    public TileImpl(Position2D coords, TileType type){
        this.myCoords = coords;
        this.myTileType = type;
        if(myTileType.equals(TileType.PASSABLE)){
            this.myIcon = new ImageIcon(this.getClass().getResource("../../../../resources/tiles/unknown.jpg")).getImage();
        }else{
            this.myIcon = new ImageIcon(this.getClass().getResource("../../../../resources/tiles/index.png")).getImage();
        }
        this.entityOnTile = Optional.empty();
    }

    public Position2D getCoords() {
        return myCoords;
    }

    public TileType getTileType() {
        return myTileType;
    }

    public void setTileType(TileType myTileType) {
        this.myTileType = myTileType;
    }

    @Override
    public Image getImage() {
        return this.myIcon;

    }

    public Optional<Entity> getEntity() throws AbsentEntityException {
        return this.entityOnTile;
    }

    public void setEntity(Entity entity) throws EntityAlreadyPresentException {
        if(this.entityOnTile.isPresent()){
            throw new EntityAlreadyPresentException();
        }else{
            this.entityOnTile = Optional.of(entity);
        }
    }

    @Override
    public boolean isAdjacentTo(Tile otherTile) {
        if(Math.abs(otherTile.getCoords().getPosX() - this.getCoords().getPosX()) - Math.abs(otherTile.getCoords().getPosY() - this.getCoords().getPosY()) <= 1){
            return true;
        }
        return false;
    }

    public void resetTile(){
        this.entityOnTile = Optional.empty();
    }

    @Override
    public String toString() {
        return "TileImpl [myCoords=" + myCoords + ", myTileType=" + myTileType + ", entityOnTile=" + entityOnTile + "]";
    }

}
