package code.model.actor.impl;

import code.exceptions.IllegalPositionException;
import code.model.actor.api.ActiveEntity;
import code.model.actor.api.Entity;
import code.model.gameLogic.GameLogic;
import code.model.world.api.Tile;
import code.model.world.impl.Position2DImpl;
import code.model.world.impl.TileImpl;
import code.model.world.impl.TileType;
import code.view.Direction;
import code.view.MainFrame;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class CharacterTest {

    static GameLogic logic;
    static MainFrame frame;
    static Entity testCharacter;
    @BeforeClass
    public static void setUp(){
        logic = new GameLogic();
        frame = new MainFrame();
        frame.setVisible(false);
        testCharacter = EntityFactory.createCharacter();
    }

    @Test
    public void move() {
        testCharacter.setTile(new TileImpl(new Position2DImpl(1, 1), TileType.ACCESSIBLE));

        assertThrows(IllegalPositionException.class, () -> ((ActiveEntity) testCharacter).move(new TileImpl(new Position2DImpl(10, 10), TileType.ACCESSIBLE)));

        Tile accessTile = new TileImpl(new Position2DImpl(1,2), TileType.NON_ACCESSIBLE);
        ((ActiveEntity)testCharacter).move(accessTile);
        assertEquals(new TileImpl(new Position2DImpl(1,1), TileType.ACCESSIBLE), testCharacter.getTile());

        accessTile = new TileImpl(new Position2DImpl(1,2), TileType.STAIRS);
        ((ActiveEntity)testCharacter).move(accessTile);

        assertEquals(1, GameLogic.getScoreCounter().getValue());
        assertEquals(Optional.empty(), accessTile.getEntity());

        accessTile = new TileImpl(new Position2DImpl(1,2), TileType.EXIT);
        ((ActiveEntity)testCharacter).move(accessTile);

        assertFalse(testCharacter.isAlive());
        assertEquals(2, GameLogic.getScoreCounter().getValue());
    }
}
