package code.model.world.api;

import code.exceptions.AbsentEntityException;
import code.exceptions.EntityAlreadyPresentException;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.Entity;
import code.view.Directions;

public interface GameMap {
    public static final int MAX_MAP_SIZE = 16;

    public Tile getSpecificTile(Position2D position2dImpl) throws AbsentEntityException;

    public Tile getSpecificTile(int x, int y) throws AbsentEntityException;
    public Tile[][] getGrid();

    public void setEntityOnPosition(Position2D position, Entity entity) throws EntityAlreadyPresentException;
    
    public void move(Directions direction, Entity entity) throws IllegalPositionException, EntityAlreadyPresentException;

}
