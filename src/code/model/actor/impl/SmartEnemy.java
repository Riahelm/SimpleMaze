package code.model.actor.impl;

import code.model.actor.api.Entity;
import code.model.world.api.Position2D;
import code.model.world.impl.Position2DImpl;
import code.view.Direction;

import java.util.Random;

public class SmartEnemy extends Enemy{
    Entity character;

    @Override
    public EntityType getType(){
        return EntityType.SMART_ENEMY;
    }
    public SmartEnemy(){
        super();
    }
    @Override
    public Direction findADirection(){
    //This is a simple method to have the enemy move towards the Character, not checking for walls.
    //In case there's no Character it'll move randomly.
        if(character != null) {
            Position2D myPosition = getTile().getCoords();
            Position2D characterPosition = character.getTile().getCoords();
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

    public void setCharacterToFollow(Entity character){
        this.character = character;
    }
}
