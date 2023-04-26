package code.model.actor.impl;

import code.exceptions.IllegalPositionException;
import code.model.actor.api.ActiveEntity;
import code.model.actor.api.Entity;
import code.model.gameLogic.GameLogic;
import code.model.world.api.Tile;
import code.model.world.impl.Position2DImpl;
import code.model.world.impl.TileImpl;
import code.model.world.impl.TileType;
import code.view.MainFrame;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyTest {

    static Entity testEnemy;
    static GameLogic logic;
    static MainFrame frame;
    @BeforeClass
    public static void setUp(){
        testEnemy = EntityFactory.createEnemy();
        logic = new GameLogic();
        frame = new MainFrame();
        frame.setVisible(false);
    }

    @Test
    public void move() {
        testEnemy.setTile(new TileImpl(new Position2DImpl(1, 1), TileType.ACCESSIBLE));

        assertThrows(IllegalPositionException.class, () -> ((ActiveEntity) testEnemy).move(new TileImpl(new Position2DImpl(10, 10), TileType.ACCESSIBLE)));

        Tile accessTile = new TileImpl(new Position2DImpl(1,2), TileType.NON_ACCESSIBLE);
        ((ActiveEntity)testEnemy).move(accessTile);
        assertEquals(new TileImpl(new Position2DImpl(1,1), TileType.ACCESSIBLE), testEnemy.getTile());
    }
}