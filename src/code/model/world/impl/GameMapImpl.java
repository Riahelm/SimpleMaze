package code.model.world.impl;

import code.model.actor.api.ActiveEntity;
import code.model.actor.api.InteractableEntity;
import code.model.actor.impl.EntityFactory;
import code.model.actor.impl.NPCQuestions;
import code.util.MapReader;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.Entity;
import code.util.OperateOnMatrix;
import code.util.Pair;
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
    Tile[][] myGrid;
    Random randomMovementGenerator;

    public GameMapImpl(URL mapPath) throws IOException {
        TileType[][] convertedMap = (MapReader.readMap(mapPath));
        randomMovementGenerator = new Random();
        this.size = convertedMap.length;
        this.aliveEntities = new LinkedList<>();
        this.myGrid = new Tile[size][size];
        OperateOnMatrix.operateOnEachElement(convertedMap, (i, j) -> {
            switch (convertedMap[i][j]){
                case ACCESSIBLE_WITH_ENEMY -> {
                    myGrid[i][j] = new TileImpl(new Position2DImpl(i, j), TileType.ACCESSIBLE);
                    if(new Random().nextInt(100) < 80){
                        addEntityToWorld(myGrid[i][j], EntityFactory.createEnemy());
                    }else addEntityToWorld(myGrid[i][j], EntityFactory.createSmartEnemy(myGrid));
                }
                case ACCESSIBLE_WITH_NPC -> {
                    myGrid[i][j] = new TileImpl(new Position2DImpl(i,j), TileType.ACCESSIBLE);
                    addEntityToWorld(myGrid[i][j], EntityFactory.createNPC(NPCQuestions.getAQuestion()));
                }
                case SPAWNPOINT -> {
                    myGrid[i][j] = new TileImpl(new Position2DImpl(i, j), TileType.ACCESSIBLE);
                    addEntityToWorld(myGrid[i][j], EntityFactory.createCharacter());
                }
                default -> myGrid[i][j] = new TileImpl(new Position2DImpl(i, j), convertedMap[i][j]);
            }
        });
    }


    @Override
    public int getMapSize() {
        return size;
    }

    public Tile[][] getGrid() {
        return myGrid.clone();
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

    public void addEntityToWorld(Tile tile, Entity entity){
        if(tile.getEntity().isEmpty()) {
            tile.setEntity(entity);
            entity.setTile(tile);

            this.aliveEntities.add(entity);
            this.aliveEntities.sort(Comparator.comparingInt(ent -> ent.getType().ordinal())); // STRATEGY PATTERN HERE
        }
    }

    public void performTurn(Direction direction, ActiveEntity entity) throws IllegalPositionException{
        Tile destinationTile;
        Pair<Integer, Integer> dir = direction.toPair();

        destinationTile = myGrid[entity.getTile().getCoords().getPosX() + dir.x()]
                                [entity.getTile().getCoords().getPosY() + dir.y()];

        if(!destinationTile.equals(entity.getTile())){
            if (destinationTile.getEntity().isPresent() && destinationTile.getEntity().get() instanceof InteractableEntity destinationEntity){
                destinationEntity.onInteract(entity.getType());
            }
            if (destinationTile.getEntity().isEmpty()) {
                entity.move(destinationTile);
            }
        }
    }

    public void performTurn(ActiveEntity entity){
        this.performTurn(entity.findADirection(), entity);
    }
    @Override
    public List<Entity> getEntities() {
        return this.aliveEntities;
    }
}
