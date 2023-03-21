package code.model.actor.impl;

import code.controller.GameChatController;
import code.exceptions.EntityAlreadyPresentException;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.ActiveEntity;
import code.model.actor.api.Entity;
import code.model.world.api.Tile;
import code.model.world.impl.TileType;

import java.util.Optional;

public class Enemy extends EntityAb implements ActiveEntity {

    Enemy(GameChatController gCC) {
        super(EntityType.ENEMY, gCC);
    }

    @Override
    public void interact(Tile interactionTile) {
        Entity interactedEntity = interactionTile.getEntity().get();
        if (interactedEntity.isAlive() && interactedEntity instanceof Character     ) {
            interactedEntity.setLifeTo(false);
        }
    }

    @Override
    public void move(Tile destinationTile) {
        if (destinationTile.getTileType().equals(TileType.PASSABLE)) {
            moveTo(destinationTile);
        }
    }

    @Override
    public boolean canMove() {
        return true;
    }

    private void moveTo(Tile destination) throws IllegalPositionException, EntityAlreadyPresentException {
        if(this.canMove() && this.getTile().isAdjacentTo(destination)){
            //TODO change the grid to show the surrounding area, so you may call the mapReader onto a 8x8 instead of the full map
            //helpful tip: give mapreader a fixed radius around which you want to show your stuff
            //mind you, this is all optional!
            this.getTile().resetTile();
            this.setTile(Optional.of(destination));
            destination.setEntity(this);
        }
    }

}
