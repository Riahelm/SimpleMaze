package code.model.world.impl;

import code.model.actor.api.Entity;
import code.model.actor.impl.EntityFactory;
import code.model.actor.impl.EntityType;
import code.view.Direction;
import org.junit.Before;
import org.junit.BeforeClass;
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
    }

    @Test
    public void getSpecificTile() {
        assertEquals(testMap.getGrid()[2][2], testMap.getSpecificTile(2,2));
        assertSame(testMap.getGrid()[4][4], testMap.getSpecificTile(new Position2DImpl(4,4)));
    }

    @Test
    public void addEntityToWorld() {
        assertEquals(0, testMap.getEntities().size());

        testMap.addEntityToWorld(testMap.myGrid[1][1], EntityFactory.createCharacter());
        testMap.addEntityToWorld(testMap.myGrid[1][1], EntityFactory.createEntity(EntityType.ENEMY));

        assertEquals(1, testMap.getEntities().size());

        assertEquals(EntityType.CHARACTER, testMap.getEntities().get(0).getType());

        testMap.addEntityToWorld(testMap.myGrid[1][2], EntityFactory.createEnemy());

        assertEquals(2, testMap.getEntities().size());

        testMap.myGrid[1][1].resetTile();

        assertEquals(2, testMap.getEntities().size());
    }

    //Null pointer expected because the gameChat has not been initialized
    @Test(expected = NullPointerException.class)
    public void performTurn() {

        testMap.addEntityToWorld(testMap.myGrid[1][1], EntityFactory.createCharacter());

        testMap.performTurn(Direction.RIGHT, testMap.aliveEntities.get(0));
        assertEquals(testMap.myGrid[2][1], testMap.aliveEntities.get(0).getTile());
        if(testMap.myGrid[2][1].getEntity().isPresent()){
            assertEquals(testMap.aliveEntities.get(0), testMap.myGrid[2][1].getEntity().get());
        }else fail("Entity was not moved properly");

        testMap.performTurn(Direction.DOWN, testMap.aliveEntities.get(0));
        assertEquals(testMap.myGrid[2][1], testMap.aliveEntities.get(0).getTile());
        if(testMap.myGrid[2][1].getEntity().isPresent()){
            assertEquals(testMap.aliveEntities.get(0), testMap.myGrid[2][1].getEntity().get());
        }else fail("Entity was not moved properly");

    }
    //Null pointer expected because the player's score has not been initialized
    @Test(expected = NullPointerException.class)
    public void getEntities() {
        assertEquals(0, testMap.getEntities().size());

        testMap.addEntityToWorld(testMap.myGrid[1][1], EntityFactory.createCharacter());

        assertEquals(1, testMap.getEntities().size());

        testMap.addEntityToWorld(testMap.myGrid[2][1], EntityFactory.createEnemy());

        assertEquals(2, testMap.getEntities().size());

        testMap.performTurn(Direction.RIGHT, testMap.aliveEntities.get(0));

        assertEquals(1, testMap.aliveEntities.size());
    }
}