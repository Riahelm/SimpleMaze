package code.model.world.impl;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TileTypeTest {

    @Test
    public void testFromInt() {

        assertEquals(TileType.NON_ACCESSIBLE, TileType.fromInt(0));
        assertEquals(TileType.ACCESSIBLE, TileType.fromInt(1));
        assertEquals(TileType.ACCESSIBLE_WITH_ENEMY, TileType.fromInt(2));
        assertEquals(TileType.SPAWN_POINT, TileType.fromInt(3));
        assertEquals(TileType.STAIRS, TileType.fromInt(4));
        assertEquals(TileType.EXIT, TileType.fromInt(5));

        assertThrows(IllegalStateException.class, () -> TileType.fromInt(6));

    }
}