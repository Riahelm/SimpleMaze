package code.model.actor.impl;

import code.model.actor.api.Entity;
import code.model.gameLogic.GameLogic;
import code.model.world.api.Tile;
import code.view.GameOverState;

public class Character extends ActiveEntityTemplate{
    Character() {
        super(EntityType.CHARACTER);
    }

    @Override
    public void interact(Entity interactedEntity) {

        if (interactedEntity.getType().equals(EntityType.NPC)) {
            NPC myNPC = (NPC) interactedEntity;
            gCC.sendMessage(myNPC.getDialogue());
        } else if (interactedEntity.isAlive()) {
            GameLogic.getScoreCounter().increment();
            gCC.updateScore(GameLogic.getScoreCounter().getValue());
            interactedEntity.setLifeTo(false);
        }
    }

    @Override
    public void move(Tile destinationTile) {
        switch (destinationTile.getTileType()) {
            case ACCESSIBLE -> moveTo(destinationTile);
            case NON_ACCESSIBLE -> gCC.sendMessage("Bonk!");
            case STAIRS -> {
                gCC.sendMessage("You go to the next level...");
                GameLogic.getScoreCounter().increment();
                GameLogic.switchToNextWorld();
            }
            case EXIT -> {
                this.isAlive = false; // This is so that no more movement is made, and no more messages are sent
                gc.finishGame(GameOverState.WIN, GameLogic.getScoreCounter().getValue());
            }
        }
    }

    @Override
    public boolean canMove() {
        return true;
    }

}
