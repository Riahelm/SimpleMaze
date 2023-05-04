package code.model.actor.impl;

import code.model.world.api.Tile;
import code.model.world.impl.TileType;

public class Phantom extends SmartEnemy{
    Phantom() {
        super();
    }

    @Override
    public EntityType getType() {
        return EntityType.PHANTOM;
    }

    @Override
    public void move(Tile destinationTile) {
        if (destinationTile.getTileType().equals(TileType.ACCESSIBLE) || destinationTile.getTileType().equals(TileType.NON_ACCESSIBLE)) {
            moveTo(destinationTile);
        }
    }
}
