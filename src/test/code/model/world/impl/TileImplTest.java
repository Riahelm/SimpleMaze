package code.model.world.impl;

import code.model.actor.impl.EntityFactory;
import code.model.world.api.Tile;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class TileImplTest {
    Tile tile;
    Tile aboveTile;
    Tile leftTile;
    Tile rightTile;
    Tile belowTile;
    Tile upperLeftTile;
    Tile lowerLeftTile;
    Tile lowerRightTile;
    Tile upperRightTile;

    Tile veryFarTile;
    @Before
    public void setUp(){
        tile           = new TileImpl(new Position2DImpl(1,1), TileType.ACCESSIBLE);
        aboveTile      = new TileImpl(new Position2DImpl(1,2), TileType.ACCESSIBLE);
        leftTile       = new TileImpl(new Position2DImpl(0,1), TileType.ACCESSIBLE);
        rightTile      = new TileImpl(new Position2DImpl(2,1), TileType.ACCESSIBLE);
        belowTile      = new TileImpl(new Position2DImpl(1,0), TileType.ACCESSIBLE);
        upperLeftTile  = new TileImpl(new Position2DImpl(0,2), TileType.ACCESSIBLE);
        lowerLeftTile  = new TileImpl(new Position2DImpl(0,0), TileType.ACCESSIBLE);
        upperRightTile = new TileImpl(new Position2DImpl(2,2), TileType.ACCESSIBLE);
        lowerRightTile = new TileImpl(new Position2DImpl(2,0), TileType.ACCESSIBLE);
        veryFarTile    = new TileImpl(new Position2DImpl(3,1), TileType.ACCESSIBLE);
    }
    @Test
    public void testIsAdjacentTo() {
        assertTrue(tile.isAdjacentTo(tile));
        assertTrue(tile.isAdjacentTo(aboveTile));
        assertTrue(tile.isAdjacentTo(leftTile));
        assertTrue(tile.isAdjacentTo(rightTile));
        assertTrue(tile.isAdjacentTo(belowTile));

        assertFalse(tile.isAdjacentTo(upperLeftTile));
        assertFalse(tile.isAdjacentTo(lowerLeftTile));
        assertFalse(tile.isAdjacentTo(upperRightTile));
        assertFalse(tile.isAdjacentTo(lowerRightTile));
    }

    @Test
    public void resetTile() {

        assertFalse(tile.getEntity().isPresent());

        tile.setEntity(EntityFactory.createCharacter());

        assertTrue(tile.getEntity().isPresent());

        tile.resetTile();

        assertFalse(tile.getEntity().isPresent());

        assertThrows(NoSuchElementException.class, () -> tile.getEntity().get());
    }
}