package code.model;

import code.exceptions.IllegalPositionException;
import code.model.actor.api.ActiveEntity;
import code.model.actor.api.Entity;
import code.model.actor.api.InteractableEntity;
import code.model.actor.impl.Character;
import code.model.actor.impl.EntityFactory;
import code.model.world.api.Tile;
import code.model.world.impl.GameMapImpl;
import code.model.world.impl.Position2DImpl;
import code.model.world.impl.TileImpl;
import code.model.world.impl.TileType;
import code.util.OperateOnMatrix;
import code.view.Direction;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GenericMovementTests {

    @Test
    public void testMovement(){
        Tile[][] map = new Tile[4][4];

        OperateOnMatrix.operateOnEachElement(map, (i,j) -> map[i][j] = new TileImpl(new Position2DImpl(i,j), TileType.ACCESSIBLE));
        Character testChar1 = EntityFactory.createCharacter();
        Character testChar2 = EntityFactory.createCharacter();
        Entity enemy1 = EntityFactory.createEnemy();
        Entity enemy2 = EntityFactory.createEnemy();

        testChar1.setTile(map[2][2]);
        testChar2.setTile(map[2][3]);
        enemy1.setTile(map[1][3]);
        enemy2.setTile(map[0][0]);

        assertThrows(RuntimeException.class, testChar1::findADirection);
        assertThrows(IllegalPositionException.class, () -> testChar1.move(map[0][0]));

        testChar1.move(map[2][3]);

        assertTrue(testChar2.isAlive());
        assertEquals(map[2][3], testChar2.getTile());
        assertSame(testChar1.getTile(), testChar2.getTile());
        testChar1.move(map[1][3]);
        assertEquals(testChar1.getTile(), enemy1.getTile());
        //These are good as is, the move method hasn't got the concern of checking Tile availability, it only checks if it's close.
    }

    @Test
    public void testInteractions() throws IOException {
        GameMapImpl map= new GameMapImpl(this.getClass().getResource("../../resources/worlds/Test_Map_2"));
        Character testChar = (Character) map.getEntities().get(0);
        ActiveEntity myEnt = (ActiveEntity) map.getEntities().get(2);
        InteractableEntity myNPC = (InteractableEntity) map.getEntities().get(1);
        assertEquals(map.getGrid()[1][1], testChar.getTile());
        assertEquals(map.getGrid()[2][1], myNPC.getTile());
        assertEquals(map.getGrid()[1][2], myEnt.getTile());

        map.performTurn(Direction.DOWN, testChar);
        map.performTurn(Direction.LEFT, testChar);

        assertEquals(map.getGrid()[1][1], testChar.getTile());
        //The test must not have the character move as long as he can't answer the question
    }

}
