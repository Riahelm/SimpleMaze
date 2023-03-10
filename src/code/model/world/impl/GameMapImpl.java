package code.model.world.impl;

import code.model.util.MapReader;
import code.exceptions.AbsentEntityException;
import code.exceptions.EntityAlreadyPresentException;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.Entity;
import code.model.util.Pair;
import code.model.world.api.GameMap;
import code.model.world.api.Position2D;
import code.model.world.api.Tile;

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

    @Override
    //TODO actually put checks here
    public Tile getSpecificTile(int x, int y) throws AbsentEntityException {
        return myGrid[x][y];
    }

    public Entity getEntityOnTile(Position2D position) throws AbsentEntityException {
        return this.myGrid[position.getPosX()][position.getPosY()].getEntity().get();
    }

    public void setEntityOnTile(Position2D position, Entity entity) throws EntityAlreadyPresentException {
        this.myGrid[position.getPosX()][position.getPosY()].setEntity(entity);
    }

    //TODO Change this to be more flexible
    public void move(String direction, Entity entity) throws IllegalPositionException, EntityAlreadyPresentException {
        Tile destinationTile;
        Pair<Integer, Integer> dir;
        switch (direction) {
            case ("VK_W") -> dir = new Pair<>(0, 1);

            case ("VK_S") -> dir = new Pair<>(0, -1);

            case ("VK_A") -> dir = new Pair<>(-1, 0);

            case ("VK_D") -> dir = new Pair<>(1, 0);
            default -> dir = new Pair<>(0, 0);
        }
        moveTo(entity, myGrid[entity.getTile().getCoords().getPosX() + dir.getX()]
                             [entity.getTile().getCoords().getPosY() + dir.getY()]);
    }

    private void moveTo(Entity entity, Tile destination) throws IllegalPositionException, EntityAlreadyPresentException {
        if(entity.canMove() && canMoveTo(entity, destination)){
            entity.getTile().resetTile();
            entity.setTile(destination);
            destination.setEntity(entity);
        }
    }

    //TODO NOTA LA RIPETIZIONE DI CODICE, POSSIBILE USO DELLE LAMBDA QUI
    private void moveUp(Entity entity) throws IllegalPositionException, EntityAlreadyPresentException {
        Tile destinationTile = myGrid[entity.getTile().getCoords().getPosX()][entity.getTile().getCoords().getPosY() + 1];
        if (entity.canMove() && this.canMoveTo(entity, destinationTile)) {
            //TODO Finish this
            //if(destinationTile.getEntity().get().
            entity.getTile().resetTile();
            entity.setTile(destinationTile);
            destinationTile.setEntity(entity);
        }

    }

    private void moveDown(Entity entity) throws IllegalPositionException, EntityAlreadyPresentException {
        Tile destinationTile = myGrid[entity.getTile().getCoords().getPosX()][entity.getTile().getCoords().getPosY() - 1];
        if (entity.canMove() && this.canMoveTo(entity, destinationTile)) {
            entity.getTile().resetTile();
            entity.setTile(destinationTile);
            destinationTile.setEntity(entity);
        }
    }

    private void moveLeft(Entity entity) throws IllegalPositionException, EntityAlreadyPresentException {
        Tile destinationTile = myGrid[entity.getTile().getCoords().getPosX() - 1][entity.getTile().getCoords().getPosY()];
        if (entity.canMove() && this.canMoveTo(entity, destinationTile)) {
            entity.getTile().resetTile();
            entity.setTile(destinationTile);
            destinationTile.setEntity(entity);
        }
    }

    private void moveRight(Entity entity) throws IllegalPositionException, EntityAlreadyPresentException {
        Tile destinationTile = myGrid[entity.getTile().getCoords().getPosX() + 1][entity.getTile().getCoords().getPosY()];
        if (entity.canMove() && this.canMoveTo(entity, destinationTile)) {
            entity.getTile().resetTile();
            entity.setTile(destinationTile);
            destinationTile.setEntity(entity);
        }
    }

    private boolean canMoveTo(Entity entity, Tile tile) {
        return (!(tile.getTileType().equals(TileType.IMPASSABLE)) && entity.getTile().isAdjacentTo(tile));
    }

}
