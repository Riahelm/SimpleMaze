package code.model.world.impl;

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

public class GameMapImpl implements GameMap {

    String name;
    Integer size;
    Tile[][] myGrid;




    public GameMapImpl(String name, Integer size, URL mapPath) throws IOException {
        this.size = size;

        myGrid = new Tile[size][size];
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
        if(x < this.size && y < this.size){
            return myGrid[x][y];
        } else{
            throw new IllegalPositionException();
        }
    }

    public void setEntityOnPosition(Position2D position, Entity entity) throws EntityAlreadyPresentException {
        this.myGrid[position.getPosX()][position.getPosY()].setEntity(entity);
        entity.setTile(this.getSpecificTile(position.getPosX(), position.getPosY()));
    }

    public void move(Directions direction, Entity entity) throws IllegalPositionException, EntityAlreadyPresentException {
        Tile destinationTile;
        Pair<Integer, Integer> dir;
        switch (direction.ordinal()) {
            case (0) -> dir = new Pair<>(0, 1);

            case (1) -> dir = new Pair<>(0, -1);

            case (2) -> dir = new Pair<>(-1, 0);

            case (3) -> dir = new Pair<>(1, 0);

            default -> dir = new Pair<>(0, 0);
        }
        destinationTile = myGrid[entity.getTile().getCoords().getPosX() + dir.getX()]
                [entity.getTile().getCoords().getPosY() + dir.getY()];
        //TODO finish this.
        if(destinationTile.getEntity().isPresent()){
            Entity destinationEntity = destinationTile.getEntity().get();
            switch (destinationEntity.getType()){
                case ENEMY -> kill(destinationEntity);
                case NPC -> talk(destinationEntity);
            }
        }

        switch(destinationTile.getTileType()){
            case EXIT ->{
                moveTo(entity, destinationTile);
            }
            case PASSABLE -> moveTo(entity, destinationTile);
            case IMPASSABLE ->{} //Effectively does nothing, may play the sound of hitting the wall though.
        }
    }

    private void talk(Entity npc) {
        //TODO add dialogue here
    }

    private void kill(Entity entityToKill) {
        entityToKill.getTile().resetTile();
        entityToKill.setTile(null);
    }

    private void moveTo(Entity entity, Tile destination) throws IllegalPositionException, EntityAlreadyPresentException {
        if(entity.canMove() && entity.getTile().isAdjacentTo(destination)){
            //TODO change the grid to show the surrounding area, so you may call the mapreader onto a 8x8 instead of the full map
            //helpful tip: give mapreader a fixed radius around which you want to show your stuff
            //mind you, this is all optional!
            entity.getTile().resetTile();
            entity.setTile(destination);
            destination.setEntity(entity);
        }
    }

}
