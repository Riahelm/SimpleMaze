package code.model.world.api;



import code.model.world.impl.TileType;
import code.exceptions.EntityAlreadyPresentException;
import code.model.actor.api.Entity;

import java.awt.*;
import java.util.Optional;

public interface Tile {
    
    //Getters
    public Position2D getCoords();
    public TileType getTileType();
    public Image getImage();

    public Optional<Entity> getEntity();

    //Setters
    public void setEntity(Entity entity) throws EntityAlreadyPresentException;
    public void setTileType(TileType myTileType);

    //Utility
    public boolean isAdjacentTo(Tile otherTile);
    public void resetTile();
}

