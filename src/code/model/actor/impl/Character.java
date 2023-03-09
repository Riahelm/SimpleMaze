package code.model.actor.impl;

import code.model.world.api.Tile;

/**
 * @author Nicolas
 */
public class Character extends EntityAb {


    public Character(String name, Tile startingTile, Integer id) {
        super(name, startingTile, id);
        //Space for different stats, such as health
    }


    @Override
    public boolean canMove() {
        return this.canMove;
    }

    @Override
    public boolean canDie() {
        return this.canDie;
    }
}
