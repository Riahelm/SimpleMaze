package code.model.actor.impl;

import code.exceptions.IllegalPositionException;
import code.model.actor.api.ActiveEntity;
import code.model.world.api.Tile;

public abstract class ActiveEntityTemplate extends EntityTemplate implements ActiveEntity {

    protected ActiveEntityTemplate(EntityType type) {
        super(type);
    }

    protected void moveTo(Tile destination) throws IllegalPositionException {
        if(this.canMove() && this.getTile().isAdjacentTo(destination)){
            //TODO change the grid to show the surrounding area, so you may call the mapReader onto a 8x8 instead of the full map
            //helpful tip: give MapReader a fixed radius around which you want to show your stuff
            //mind you, this is all optional!
            this.getTile().resetTile();
            this.setTile(destination);
            destination.setEntity(this);
        }else throw new IllegalPositionException();
    }
    public boolean canMove(){
        return true;
    }
}
