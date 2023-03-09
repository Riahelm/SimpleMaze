package code.model.actor.impl;

import code.model.world.api.Tile;

import javax.swing.*;
import java.net.URL;

/**
 * @author Nicolas
 */
public class Character extends EntityAb {


    public Character(String name, Tile startingTile) {
        super(name, startingTile);
        //Space for different stats, such as health
    }


    @Override
    public Icon getSprite() {
        return null;
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
