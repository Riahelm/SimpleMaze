package code.model.actor.impl;

import code.exceptions.EntityAlreadyPresentException;
import code.exceptions.IllegalPositionException;
import code.model.actor.api.ActiveEntity;
import code.model.actor.api.Entity;
import code.model.world.api.Tile;
import code.view.GameOverState;

import java.util.Optional;

public class Character extends EntityAb implements ActiveEntity {
    Character() {
        super(EntityType.CHARACTER);
    }

    @Override
    public void interact(Tile interactionTile) {
        Entity interactedEntity = interactionTile.getEntity().get();
        if (interactedEntity.getType().equals(EntityType.NPC)) {
            NPC myNPC = (NPC) interactionTile.getEntity().get();
            gCC.sendMessage(myNPC.getDialogue());
        } else if (interactedEntity.isAlive()) {
            interactedEntity.setLifeTo(false);
        }
    }

    @Override
    public void move(Tile destinationTile) {
        switch (destinationTile.getTileType()) {
            case ACCESSIBLE -> moveTo(destinationTile);
            case NON_ACCESSIBLE -> gCC.sendMessage("Bonk!");
            case STAIRS -> {
                gCC.sendMessage("You ascend the stairs...");
            }
            case EXIT -> {
                this.isAlive = false; // This is so that no more movement is made, and no more messages are sent
                gc.finishGame(GameOverState.WIN);
            }
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
