package code.model.world.api;

import code.exceptions.AbsentEntityException;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.ActiveEntity;
import code.model.actor.api.Entity;
import code.view.Direction;

import java.util.List;

public interface GameMap {

    int getMapSize();
    Tile[][] getGrid();
    Tile getSpecificTile(Position2D position2dImpl) throws AbsentEntityException;

    Tile getSpecificTile(int x, int y) throws AbsentEntityException;

    void addEntityToWorld(Tile tile, Entity entity);

    void performTurn(Direction direction, ActiveEntity entity) throws IllegalPositionException;
    void performTurn(ActiveEntity entity) throws IllegalPositionException;
    List<Entity> getEntities();

}
