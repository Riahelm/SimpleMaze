package code.model.world.api;

import code.exceptions.AbsentEntityException;
import code.exceptions.EntityAlreadyPresentException;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.Entity;
import code.view.Directions;

import java.util.List;
import java.util.TreeSet;

public interface GameMap {
    public static final int MAX_MAP_SIZE = 16;

    public Tile[][] getGrid();
    public Tile getSpecificTile(Position2D position2dImpl) throws AbsentEntityException;

    public Tile getSpecificTile(int x, int y) throws AbsentEntityException;

    public void addEntityToWorld(Position2D position, Entity entity) throws EntityAlreadyPresentException;

    public void addEntityToWorld(int x, int y, Entity entity);

    public void move(Directions direction, Entity entity) throws IllegalPositionException, EntityAlreadyPresentException;
    public void move(Entity entity) throws IllegalPositionException, EntityAlreadyPresentException;


    public TreeSet<Entity> getEntities();
    public List<Entity> getDeadEntities();

}
