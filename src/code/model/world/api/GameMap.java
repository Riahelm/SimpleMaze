package code.model.world.api;

import code.exceptions.AbsentEntityException;
import code.exceptions.EntityAlreadyPresentException;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.Entity;
import code.view.Direction;

import java.util.List;

public interface GameMap {
    int  MAX_MAP_SIZE = 16;

    Tile[][] getGrid();
    Tile getSpecificTile(Position2D position2dImpl) throws AbsentEntityException;

    Tile getSpecificTile(int x, int y) throws AbsentEntityException;

    void addEntityToWorld(Position2D position, Entity entity) throws EntityAlreadyPresentException;

    void addEntityToWorld(int x, int y, Entity entity);

    void move(Direction direction, Entity entity) throws IllegalPositionException, EntityAlreadyPresentException;
    void move(Entity entity) throws IllegalPositionException, EntityAlreadyPresentException;


    List<Entity> getEntities();
    List<Entity> getDeadEntities();

}
