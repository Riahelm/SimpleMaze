package code.model.actor.impl;

import code.model.world.api.Position2D;
import code.model.world.api.Tile;
import code.model.world.impl.Position2DImpl;
import code.util.OperateOnMatrix;
import code.view.Direction;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class SmartEnemy extends Enemy{
    Tile[][] worldMap;

    @Override
    public EntityType getType(){
        return EntityType.SMART_ENEMY;
    }
    public SmartEnemy(Tile[][] grid){
        super();
        worldMap = grid;
    }
    @Override
    public Direction findADirection(){

        Optional<Tile> characterTile = findACharacter();

        if(characterTile.isPresent()) {
            Position2D myPosition = getTile().getCoords();
            Position2D characterPosition = characterTile.get().getCoords();
            Position2D distance = new Position2DImpl(myPosition.getPosX() - characterPosition.getPosX(),
                                                     myPosition.getPosY() - characterPosition.getPosY());

            if(new Random().nextInt(100) <= 25){
                return Direction.SPACE;
            }
            if(Math.abs(distance.getPosX()) > Math.abs(distance.getPosY())){
                return distance.getPosX() < 0? Direction.RIGHT: Direction.LEFT;
            }else
                return distance.getPosY() < 0? Direction.UP: Direction.DOWN;
        }else return Direction.fromInt(new Random().nextInt(100)% 4);


    }

    private Optional<Tile> findACharacter() {
        AtomicReference<Optional<Tile>> result = new AtomicReference<>();
        OperateOnMatrix.operateOnEachElement(worldMap, (i, j) -> {
            if(worldMap[i][j].getEntity().isPresent() && worldMap[i][j].getEntity().get() instanceof Character){
                result.set(Optional.of(worldMap[i][j]));
            }
        });

        return result.get();
    }
}
