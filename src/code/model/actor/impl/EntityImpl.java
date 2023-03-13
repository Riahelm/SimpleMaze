package code.model.actor.impl;

import code.model.world.api.Tile;

public class EntityImpl extends EntityAb{
    public EntityImpl(String name) {
        super(name);
    }

    public EntityImpl(String name, Tile startingTile) {
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
