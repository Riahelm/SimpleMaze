package code.model.actor.api;

import code.model.world.api.Tile;

public interface ActiveEntity extends Entity{

    void move(Tile movementTile);
}
