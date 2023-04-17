package code.model.world.impl;

import code.model.world.api.Position2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Position2DImplTest {

    Position2D testPosition;
    @Before
    public void setUp(){
        testPosition = new Position2DImpl(2,5);
    }
    @Test
    public void testCoordinates(){

        assertEquals(2, testPosition.getPosX());
        assertEquals(5, testPosition.getPosY());

        assertNotEquals(4, testPosition.getPosX());
        assertNotEquals(4, testPosition.getPosY());
    }
}