package code.model.actor.impl;

import static org.junit.Assert.*;

import code.model.world.impl.Position2DImpl;
import code.model.world.impl.TileImpl;
import code.model.world.impl.TileType;
import code.util.Pair;
import org.junit.BeforeClass;
import org.junit.Test;

public class NPCTest {

    static NPC dummy;
    @BeforeClass
    public static void setUp(){
        dummy = EntityFactory.createNPC(new Pair<>("Dummy", true));
    }
    @Test
    public void canMove() {
        assertFalse(dummy.canMove());
        dummy.setTile(new TileImpl(new Position2DImpl(2,2), TileType.ACCESSIBLE));
        //No movement test needed as it can't be cast to active entity
    }

    @Test
    public void isAlive() {
        assertTrue(dummy.isAlive());
    }

    @Test
    public void getDialogue() {
        dummy.onInteract(EntityType.CHARACTER);
    }
}