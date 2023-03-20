package code.model.world.impl;

import code.controller.GameChatController;
import code.model.actor.impl.EntityType;
import code.model.actor.impl.NPC;
import code.model.util.MapReader;
import code.exceptions.EntityAlreadyPresentException;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.Entity;
import code.model.util.Pair;
import code.model.world.api.GameMap;
import code.model.world.api.Position2D;
import code.model.world.api.Tile;
import code.view.Directions;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameMapImpl implements GameMap {

    String name;
    Integer size;
    List<Entity> aliveEntities;
    List<Entity> deadEntities;
    Tile[][] myGrid;
    GameChatController chatController;

    public GameMapImpl(String name, Integer size, URL mapPath, GameChatController gc) throws IOException {
        this.chatController = gc;
        this.name = name;
        this.size = size;
        this.aliveEntities = new LinkedList<>();
        this.deadEntities = new LinkedList<>();
        this.myGrid = new Tile[size][size];
        TileType[][] convertedMap = (MapReader.readMap(size, mapPath));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                myGrid[i][j] = new TileImpl(new Position2DImpl(i, j), convertedMap[i][j]);
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

    public void addEntityToWorld(Position2D position, Entity entity) throws EntityAlreadyPresentException {
        this.myGrid[position.getPosX()][position.getPosY()].setEntity(entity);
        entity.setTile(Optional.of(this.getSpecificTile(position.getPosX(), position.getPosY())));
        this.aliveEntities.add(entity);
        this.aliveEntities.sort(Comparator.comparingInt(o -> o.getType().ordinal())); // STRATEGY PATTERN HERE
    }

    public void addEntityToWorld(int x, int y, Entity entity){
        this.addEntityToWorld(new Position2DImpl(x,y), entity);
    }

    public void move(Directions direction, Entity entity) throws IllegalPositionException, EntityAlreadyPresentException {
        Tile destinationTile;
        Pair<Integer, Integer> dir = direction.toPair();

        destinationTile = myGrid[entity.getTile().getCoords().getPosX() + dir.getX()]
                [entity.getTile().getCoords().getPosY() + dir.getY()];

        if (destinationTile.getEntity().isPresent()) {
            this.interact(entity, destinationTile.getEntity().get());
        }
        if (!destinationTile.getEntity().isPresent()) {
            switch (entity.getType()) {
                case CHARACTER -> {
                    switch (destinationTile.getTileType()) {
                        case EXIT -> {
                            chatController.sendMessage(() -> "You won!");
                            System.exit(0);
                        }
                        case PASSABLE -> moveTo(entity, destinationTile);
                        case IMPASSABLE -> chatController.sendMessage(() -> "Bonk!");
                    }
                }
                case ENEMY, NPC -> {
                    if (destinationTile.getTileType().equals(TileType.PASSABLE)) {
                        moveTo(entity, destinationTile);
                    }
                }
            }
        }
    }



    public void move(Entity entity){
        this.move(Directions.fromInt(new Random().nextInt(0, 4)), entity);
    }

    @Override
    public List<Entity> getEntities() {
        return aliveEntities;
    }

    @Override
    public List<Entity> getDeadEntities() {
        return deadEntities;
    }

    private void interact(Entity currentEntity, Entity targetEntity) {
        switch(currentEntity.getType()){
            case CHARACTER -> {
                if (currentEntity.getType().equals(EntityType.CHARACTER) && targetEntity.getType().equals(EntityType.NPC)) {
                    talk(targetEntity);
                } else {
                    kill(targetEntity);
                }
            }
            case ENEMY -> {
                if(targetEntity.getType().equals(EntityType.CHARACTER)) kill(targetEntity);
            }
        }
    }

    private void talk(Entity npc) {
        NPC myNPC = (NPC)npc;
        chatController.sendMessage(() -> myNPC.getDialogue());
    }

    private void kill(Entity entityToKill) {
        if(entityToKill.isAlive()){
            entityToKill.getTile().resetTile();
            entityToKill.setTile(Optional.empty());
            entityToKill.setLifeTo(false);
            this.deadEntities.add(entityToKill);
        }
    }

    private void moveTo(Entity entity, Tile destination) throws IllegalPositionException, EntityAlreadyPresentException {
        if(entity.canMove() && entity.getTile().isAdjacentTo(destination)){
            //TODO change the grid to show the surrounding area, so you may call the mapReader onto a 8x8 instead of the full map
            //helpful tip: give mapreader a fixed radius around which you want to show your stuff
            //mind you, this is all optional!
            entity.getTile().resetTile();
            entity.setTile(Optional.of(destination));
            destination.setEntity(entity);
        }
    }

}
