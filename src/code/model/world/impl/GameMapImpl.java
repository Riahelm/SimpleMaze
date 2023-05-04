package code.model.world.impl;

import code.model.actor.api.ActiveEntity;
import code.model.actor.api.InteractableEntity;
import code.model.actor.impl.EntityFactory;
import code.model.actor.impl.EntityType;
import code.model.actor.impl.NPCQuestions;
import code.model.actor.impl.SmartEnemy;
import code.util.MapReader;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.Entity;
import code.util.OperateOnMatrix;
import code.util.Pair;
import code.model.world.api.GameMap;
import code.model.world.api.Tile;
import code.view.Direction;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameMapImpl implements GameMap {
    final Integer size;
    List<Entity> aliveEntities;
    Tile[][] myGrid;

    public GameMapImpl(URL mapPath) throws IOException {
        TileType[][] convertedMap = (MapReader.readMap(mapPath));
        this.size = convertedMap.length;
        this.aliveEntities = new LinkedList<>();
        this.myGrid = new Tile[size][size];
        OperateOnMatrix.operateOnEachElement(convertedMap, (i, j) -> {
            switch (convertedMap[i][j]){
                case ACCESSIBLE_WITH_ENEMY -> {
                    myGrid[i][j] = new TileImpl(new Position2DImpl(i, j), TileType.ACCESSIBLE);
                    var rnd = new Random().nextInt(100);
                    if(rnd < 60){
                        addEntityToWorld(myGrid[i][j], EntityFactory.createEnemy());
                    }else if (rnd < 80){
                        addEntityToWorld(myGrid[i][j], EntityFactory.createSmartEnemy());
                    }else addEntityToWorld(myGrid[i][j], EntityFactory.createPhantom());
                }
                case ACCESSIBLE_WITH_NPC -> {
                    myGrid[i][j] = new TileImpl(new Position2DImpl(i,j), TileType.ACCESSIBLE);
                    addEntityToWorld(myGrid[i][j], EntityFactory.createNPC(NPCQuestions.getAQuestion()));
                }
                case SPAWN_POINT -> {
                    myGrid[i][j] = new TileImpl(new Position2DImpl(i, j), TileType.ACCESSIBLE);
                    addEntityToWorld(myGrid[i][j], EntityFactory.createCharacter());
                }
                default -> myGrid[i][j] = new TileImpl(new Position2DImpl(i, j), convertedMap[i][j]);
            }
        });

        /* This is a lot of code, all it does is find a character in the world, and then let every smart enemy know where he is
           This is so that each smart enemy can now move towards him
        */
        Optional<Entity> mainCharacter = aliveEntities.stream().filter(e -> e.getType().equals(EntityType.CHARACTER)).findAny();
        mainCharacter.ifPresent(entity -> aliveEntities.stream().filter(e -> e.getType().equals(EntityType.SMART_ENEMY) || e.getType().equals(EntityType.PHANTOM)).forEach(e -> ((SmartEnemy) e).setCharacterToFollow(entity)));
    }


    @Override
    public int getMapSize() {
        return size;
    }

    public Tile[][] getGrid() {
        return myGrid.clone();
    }

    private void addEntityToWorld(Tile tile, Entity entity){

            entity.setTile(tile);
            this.aliveEntities.add(entity);
            this.aliveEntities.sort(Comparator.comparingInt(ent -> ent.getType().ordinal())); // STRATEGY PATTERN HERE

    }

    public void performTurn(Direction direction, ActiveEntity entity) throws IllegalPositionException{
        Tile destinationTile;
        Pair<Integer, Integer> dir = direction.toPair();

        destinationTile = myGrid[entity.getTile().getCoords().getPosX() + dir.x()]
                                [entity.getTile().getCoords().getPosY() + dir.y()];

        if(destinationTile.equals(entity.getTile())) {
            return;
        }

        Optional<Entity> destinationEntity = aliveEntities.stream().filter(e -> e.getTile().equals(destinationTile)).findAny();

        if(destinationEntity.isPresent() && destinationEntity.get() instanceof  InteractableEntity interactableEntity){
            interactableEntity.onInteract(entity.getType());
        }

        if(destinationEntity.isEmpty() || !destinationEntity.get().isAlive()){
            entity.move(destinationTile);
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
