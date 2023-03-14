package code.model.actor.impl;

import code.model.world.api.Tile;

public class Character extends EntityAb {
    public Character(String name) {
        super(name);
    }

    public Character(String name, Tile startingTile) {
        super(name, startingTile);
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canDie() {
        return true;
    }
}
