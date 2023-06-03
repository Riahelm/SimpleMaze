package code.model.actor.impl;

import code.exceptions.IllegalPositionException;
import code.model.actor.api.ActiveEntity;
import code.model.world.api.Tile;

public abstract class ActiveEntityTemplate extends EntityTemplate implements ActiveEntity {

    protected ActiveEntityTemplate() {
        super();
    }

    protected void moveTo(Tile destination) throws IllegalPositionException {
        if(this.canMove() && this.getTile().isAdjacentTo(destination)){
            this.setTile(destination);
        }else throw new IllegalPositionException();
    }
    public boolean canMove(){
        return true;
    }
}
