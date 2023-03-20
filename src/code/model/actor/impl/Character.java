package code.model.actor.impl;

import code.model.world.api.Tile;

import java.util.Optional;

public class Character extends EntityAb{


    public Character() {
        super(EntityType.CHARACTER);
    }

    public Character(Tile startingTile) {
        this();
        this.setTile(Optional.of(startingTile));
    }

    @Override
    public boolean canMove() {
        return true;
    }

}
