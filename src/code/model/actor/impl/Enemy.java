package code.model.actor.impl;

import code.model.actor.api.Entity;
import code.model.gameLogic.GameLogic;
import code.model.world.api.Tile;
import code.model.world.impl.TileType;
import code.view.GameOverState;

class Enemy extends ActiveEntityTemplate{

    Enemy() {
        super(EntityType.ENEMY);
    }

    @Override
    public void interact(Entity interactedEntity) {
        if (interactedEntity.isAlive() && interactedEntity instanceof Character) {
            interactedEntity.setLifeTo(false);
            gc.finishGame(GameOverState.LOSE, GameLogic.getLevelCounter().getValue());
        }
    }

    @Override
    public void move(Tile destinationTile) {
        if (destinationTile.getTileType().equals(TileType.ACCESSIBLE)) {
            moveTo(destinationTile);
        }
    }

    @Override
    public boolean canMove() {
        return true;
    }

}
