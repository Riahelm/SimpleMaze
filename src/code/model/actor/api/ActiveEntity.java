package code.model.actor.api;

import code.model.world.api.Tile;
import code.view.Direction;

public interface ActiveEntity extends Entity{

    Direction findADirection();
    void move(Tile movementTile);
}
