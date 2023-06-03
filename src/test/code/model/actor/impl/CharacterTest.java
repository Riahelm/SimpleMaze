package code.model.actor.impl;

import code.exceptions.IllegalPositionException;
import code.model.actor.api.ActiveEntity;
import code.model.actor.api.Entity;
import code.model.gameLogic.GameLogic;
import code.model.world.api.Tile;
import code.model.world.impl.Position2DImpl;
import code.model.world.impl.TileImpl;
import code.model.world.impl.TileType;
import code.viewModel.GameViewModel;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    static GameLogic logic;
    static Entity testCharacter;
    @BeforeClass
    public static void setUp(){
        logic = new GameLogic();
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

        assertEquals(2, GameViewModel.getInstance().getPlayerInfo().first().getValue());

        accessTile = new TileImpl(new Position2DImpl(1,2), TileType.EXIT);
        ((ActiveEntity)testCharacter).move(accessTile);

        assertFalse(testCharacter.isAlive());
        assertEquals(0, GameViewModel.getInstance().getPlayerInfo().second().getValue());
    }
}
