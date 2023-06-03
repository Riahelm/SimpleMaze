package code.model.world.impl;

import code.model.actor.api.ActiveEntity;
import code.view.Direction;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GameMapImplTest {
    GameMapImpl testMap;
    @Before
    public void setUp(){
        try {
            testMap = new GameMapImpl(GameMapImplTest.class.getResource("../../../../resources/worlds/Test_Map"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void getMapSize() {
        assertEquals(5, testMap.getMapSize());
        try {
            testMap = new GameMapImpl(GameMapImplTest.class.getResource("../../../../resources/worlds/Map_4"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(8, testMap.getEntities().size());
    }

    @Test
    public void performTurn() {

        ActiveEntity testEntity = (ActiveEntity) testMap.getEntities().get(0);
        assertEquals(testMap.getGrid()[0][2], testEntity.getTile());
        testMap.performTurn(Direction.UP, testEntity);
        assertEquals(testMap.getGrid()[0][3], testEntity.getTile());
        testMap.performTurn(Direction.DOWN, testEntity);

        assertEquals(testMap.getGrid()[0][2], testEntity.getTile());

        testMap.performTurn(Direction.RIGHT, testEntity);

        assertEquals(testMap.getGrid()[1][2], testEntity.getTile());

    }
    @Test
    public void getEntities() {
        assertEquals(1, testMap.getEntities().size());
    }
}