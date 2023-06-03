package code.model.actor.impl;

import code.model.actor.api.InteractableEntity;
import code.model.world.api.Tile;
import code.model.world.impl.TileType;
import code.view.Direction;
import java.util.Random;

class Enemy extends ActiveEntityTemplate implements InteractableEntity{
    Enemy(){
        super();
    }

    @Override
    public EntityType getType() {
        return EntityType.ENEMY;
    }


    //Moves randomly
    @Override
    public Direction findADirection() {
        return Direction.fromInt(new Random().nextInt(0,100)% 4);
    }

    @Override
    public void move(Tile destinationTile) {
        if (destinationTile.getTileType().equals(TileType.ACCESSIBLE)) {
            moveTo(destinationTile);
        }
    }

    @Override
    public void onInteract(EntityType type) {
        if(type.equals(EntityType.CHARACTER)){
            gVM.increaseScore();
            this.setLifeTo(false);
        }
    }
}
