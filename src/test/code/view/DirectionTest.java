package code.view;

import code.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    @Test
    public void fromInt() {

        assertEquals(Direction.fromInt(0), Direction.UP);
        assertEquals(Direction.fromInt(1), Direction.DOWN);
        assertEquals(Direction.fromInt(2), Direction.LEFT);
        assertEquals(Direction.fromInt(3), Direction.RIGHT);
        assertEquals(Direction.fromInt(4), Direction.SPACE);
        Assert.assertThrows(IllegalStateException.class, () -> Direction.fromInt(5));

    }

    @Test
    public void toPair() {
        assertEquals(new Pair<>(0,1), Direction.UP.toPair());
        assertEquals(new Pair<>(0,-1), Direction.DOWN.toPair());
        assertEquals(new Pair<>(-1,0), Direction.LEFT.toPair());
        assertEquals(new Pair<>(1,0), Direction.RIGHT.toPair());
        assertEquals(new Pair<>(0,0), Direction.SPACE.toPair());
    }
}