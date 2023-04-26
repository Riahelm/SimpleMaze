package code.model.actor.impl;

import code.model.actor.api.InteractableEntity;
import code.model.gameLogic.GameLogic;
import code.model.world.api.Tile;
import code.view.GameOverState;

public class Character extends ActiveEntityTemplate implements InteractableEntity {
    Character() {
        super(EntityType.CHARACTER);
    }

    @Override
    public void move(Tile destinationTile) {
        switch (destinationTile.getTileType()) {
            case ACCESSIBLE -> moveTo(destinationTile);
            case NON_ACCESSIBLE -> gCC.sendMessage("Bonk!");
            case STAIRS -> {
                gCC.sendMessage("You go to the next level...");
                gc.increaseScore();
                GameLogic.switchToNextWorld();
            }
            case EXIT -> {
                this.isAlive = false; // This is so that no more movement is made, and no more messages are sent
                gc.increaseScore();
                gc.finishGame(GameOverState.WIN, GameLogic.getScoreCounter().getValue());
            }
        }
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public void onInteract(EntityType type) {
        if(type.equals(EntityType.ENEMY)){
            this.setLifeTo(false);
            gc.finishGame(GameOverState.LOSE, GameLogic.getScoreCounter().getValue());
        }
    }
}
