package code.model.actor.api;

import code.model.world.api.Tile;

public interface ActiveEntity {

    void interact(Entity interactionTile);
    void move(Tile movementTile);
}
