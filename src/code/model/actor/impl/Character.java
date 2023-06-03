package code.model.actor.impl;

import code.model.actor.api.InteractableEntity;
import code.model.world.api.Tile;
import code.view.Direction;
import code.view.GameOverState;

public class Character extends ActiveEntityTemplate implements InteractableEntity {
    Character() {
        super();
    }

    @Override
    public EntityType getType() {
        return EntityType.CHARACTER;
    }

    @Override
    public Direction findADirection() {
        throw new RuntimeException("You must give a direction for this entity to move!");
    }

    @Override
    public void move(Tile destinationTile) {
        switch (destinationTile.getTileType()) {
            case ACCESSIBLE -> moveTo(destinationTile);
            case NON_ACCESSIBLE -> gVMCC.sendMessage("Bonk!");
            case STAIRS -> {
                gVMCC.sendMessage("You go to the next level...");
                gVM.goToNextWorld();
            }
            case EXIT -> {
                this.setLifeTo(false); // This is so that no more movement is made, and no more messages are sent
                gVM.finishGame(GameOverState.WIN);
            }
        }
    }
    @Override
    public void onInteract(EntityType type) {
        if(type.equals(EntityType.ENEMY) || type.equals(EntityType.SMART_ENEMY) || type.equals(EntityType.PHANTOM)){
            this.setLifeTo(false);
            gVM.finishGame(GameOverState.LOSE);
        }
    }
}
