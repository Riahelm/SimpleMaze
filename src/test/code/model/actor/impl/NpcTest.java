package code.model.actor.impl;

import static org.junit.Assert.*;

import code.util.Pair;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NpcTest {

    Npc dummy;
    @BeforeClass
    public void setUp(){
        dummy = EntityFactory.createNPC(new Pair<>("Dummy", true));
    }
    @Test
    public void canMove() {
        assertFalse(dummy.canMove());
    }

    @Test
    public void isAlive() {
        assertTrue(dummy.isAlive);
    }

    @Test
    public void getDialogue() {
        assertEquals("Dummy", dummy.getQuestion());
    }
}