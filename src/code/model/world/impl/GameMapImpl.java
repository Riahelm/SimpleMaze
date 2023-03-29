package code.model.world.impl;

import code.model.actor.api.ActiveEntity;
import code.model.actor.impl.EntityFactory;
import code.model.util.MapReader;
import code.exceptions.EntityAlreadyPresentException;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.Entity;
import code.model.util.Pair;
import code.model.world.api.GameMap;
import code.model.world.api.Position2D;
import code.model.world.api.Tile;
import code.view.Direction;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameMapImpl implements GameMap {
    Integer size;
    List<Entity> aliveEntities;
    List<Entity> deadEntities;
    Tile[][] myGrid;

    public GameMapImpl(URL mapPath) throws IOException {
        this.size = GameMap.MAX_MAP_SIZE;
        this.aliveEntities = new LinkedList<>();
        this.deadEntities = new LinkedList<>();
        this.myGrid = new Tile[size][size];
        TileType[][] convertedMap = (MapReader.readMap(size, mapPath));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                switch (convertedMap[i][j]){
                    case ACCESSIBLE_WITH_ENEMY -> {
                        myGrid[i][j] = new TileImpl(new Position2DImpl(i, j), TileType.ACCESSIBLE);
                        this.addEntityToWorld(new Position2DImpl(i,j), EntityFactory.createEnemy());
                    }
                    case SPAWNPOINT -> {
                        myGrid[i][j] = new TileImpl(new Position2DImpl(i, j), TileType.ACCESSIBLE);
                        this.addEntityToWorld(new Position2DImpl(i,j), EntityFactory.createCharacter());
                    }
                    default -> myGrid[i][j] = new TileImpl(new Position2DImpl(i, j), convertedMap[i][j]);
                }
            }
        }
    }


    public Tile[][] getGrid() {
        return myGrid;
    }

    public Tile getSpecificTile(Position2D position) throws IllegalPositionException {
        if (position.getPosX() < this.size && position.getPosY() < this.size) {
            return myGrid[position.getPosX()][position.getPosY()];
        } else {
            throw new IllegalPositionException();
        }
    }
    public Tile getSpecificTile(int x, int y) throws IllegalPositionException {
        return this.getSpecificTile(new Position2DImpl(x,y));
    }

    public void addEntityToWorld(Position2D position, Entity entity){
        this.myGrid[position.getPosX()][position.getPosY()].setEntity(entity);
        entity.setTile(Optional.of(this.getSpecificTile(position.getPosX(), position.getPosY())));
        this.aliveEntities.add(entity);
        this.aliveEntities.sort(Comparator.comparingInt(o -> o.getType().ordinal())); // STRATEGY PATTERN HERE
    }

    public void addEntityToWorld(int x, int y, Entity entity){
        this.addEntityToWorld(new Position2DImpl(x,y), entity);
    }

    //TODO the move method should be renamed to better represent it actually performing a turn
    public void move(Direction direction, Entity entity) throws IllegalPositionException, EntityAlreadyPresentException {
        Tile destinationTile;
        Pair<Integer, Integer> dir = direction.toPair();

        destinationTile = myGrid[entity.getTile().getCoords().getPosX() + dir.getX()]
                [entity.getTile().getCoords().getPosY() + dir.getY()];

        if(entity instanceof ActiveEntity){
            if (destinationTile.getEntity().isPresent() && !destinationTile.equals(entity.getTile())) {
                ((ActiveEntity) entity).interact(destinationTile);
                if(!destinationTile.getEntity().get().isAlive()){
                    destinationTile.getEntity().get().setTile(Optional.empty());
                    destinationTile.resetTile();
                }
            }
            if (!destinationTile.getEntity().isPresent()) {
                ((ActiveEntity) entity).move(destinationTile);
            }
        }
    }

    public void move(Entity entity){
        this.move(Direction.fromInt(new Random().nextInt(0, 4)), entity);
    }
    @Override
    public List<Entity> getEntities() {
        return aliveEntities;
    }
    @Override
    public List<Entity> getDeadEntities() {
        return deadEntities;
    }
}
