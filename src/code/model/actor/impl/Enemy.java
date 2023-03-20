package code.model.actor.impl;

import code.model.world.api.Tile;

import java.util.Optional;

public class Enemy extends EntityAb{

    public Enemy() {
        super(EntityType.ENEMY);
    }

    @Override
    public boolean canMove() {
        return true;
    }

}
