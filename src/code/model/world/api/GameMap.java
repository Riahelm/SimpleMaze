package code.model.world.api;

import code.exceptions.AbsentEntityException;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.Entity;
import code.view.Direction;

import java.util.List;

public interface GameMap {

    int getMapSize();
    Tile[][] getGrid();
    Tile getSpecificTile(Position2D position2dImpl) throws AbsentEntityException;

    Tile getSpecificTile(int x, int y) throws AbsentEntityException;

    void addEntityToWorld(Position2D position, Entity entity);

    void addEntityToWorld(int x, int y, Entity entity);

    void move(Direction direction, Entity entity) throws IllegalPositionException;
    void move(Entity entity) throws IllegalPositionException;



    List<Entity> getEntities();
    List<Entity> getDeadEntities();

}
