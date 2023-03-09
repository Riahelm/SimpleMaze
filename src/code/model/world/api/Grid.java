package code.model.world.api;

import code.exceptions.EntityAlreadyPresentException;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.Entity;
import code.model.actor.impl.EntityAb;

public interface Grid {
    
    public Integer getGridSize();
    public Tile[][] getGrid();
    public Tile getSpecificTile(Position2D position) throws IllegalPositionException;
    public Entity getEntityOnTile(Position2D position);
    public void    setEntityOnTile(Position2D position, EntityAb entity) throws EntityAlreadyPresentException;

    public void moveUp(Entity entity)    throws IllegalPositionException, EntityAlreadyPresentException;
    public void moveDown(Entity entity)  throws IllegalPositionException, EntityAlreadyPresentException;
    public void moveLeft(Entity entity)  throws IllegalPositionException, EntityAlreadyPresentException;
    public void moveRight(Entity entity) throws IllegalPositionException, EntityAlreadyPresentException;
}
