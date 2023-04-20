package code.model.actor.impl;

import static org.junit.Assert.*;
import org.junit.Test;

public class NpcTest {

    @Test
    public void canMove() {
        assertFalse(EntityFactory.createNPC("Dummy").canMove());
    }

    @Test
    public void isAlive() {
        assertTrue(EntityFactory.createNPC("Dummy").isAlive);
    }

    @Test
    public void getDialogue() {
        assertEquals("Dummy", EntityFactory.createNPC("Dummy").getDialogue());
    }
}