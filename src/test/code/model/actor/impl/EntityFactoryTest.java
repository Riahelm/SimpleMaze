package code.model.actor.impl;

import code.util.Pair;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntityFactoryTest {

    @Test
    public void createEntity() {
        assertNotNull(EntityFactory.createEntity(EntityType.CHARACTER));
        assertNotNull(EntityFactory.createEntity(EntityType.ENEMY));
        assertNotNull(EntityFactory.createEntity(EntityType.NPC));
    }

    @Test
    public void createCharacter() {
        assertNotNull(EntityFactory.createCharacter());
    }

    @Test
    public void createEnemy() {
        assertNotNull(EntityFactory.createEnemy());
    }

    @Test
    public void createNPC() {
        assertNotNull(EntityFactory.createNPC(new Pair<>("Dummy", true)));
        assertEquals("Dummy", EntityFactory.createNPC(new Pair<>("Dummy", true)).getQuestion());
    }
}