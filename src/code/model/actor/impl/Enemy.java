package code.model.actor.impl;

import code.model.actor.api.InteractableEntity;
import code.model.world.api.Tile;
import code.model.world.impl.TileType;
class Enemy extends ActiveEntityTemplate implements InteractableEntity{

    Enemy() {
        super(EntityType.ENEMY);
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

    @Override
    public void onInteract(EntityType type) {
        if(type.equals(EntityType.CHARACTER)){
            gc.increaseScore();
            this.setLifeTo(false);
        }
    }
}
