package code.model.world.api;

import code.exceptions.IllegalPositionException;
import code.model.actor.api.ActiveEntity;
import code.model.actor.api.Entity;
import code.view.Direction;

import java.util.List;

public interface GameMap {

    int getMapSize();
    Tile[][] getGrid();
    void performTurn(Direction direction, ActiveEntity entity) throws IllegalPositionException;
    void performTurn(ActiveEntity entity) throws IllegalPositionException;
    List<Entity> getEntities();

}
