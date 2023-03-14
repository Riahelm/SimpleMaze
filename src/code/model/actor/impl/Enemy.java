package code.model.actor.impl;

import code.model.world.api.Tile;

public class Enemy extends EntityAb{
    public Enemy(String name) {
        super(name);
    }

    public Enemy(String name, Tile startingTile) {
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
