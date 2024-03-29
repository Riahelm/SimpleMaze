package code.model.actor.impl;

import code.util.Pair;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntityFactoryTest {

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
    }

    @Test
    public void createSmartEnemy(){
        assertNotNull(EntityFactory.createSmartEnemy());
    }

    @Test
    public void createPhantom(){
        assertNotNull(EntityFactory.createPhantom());
    }

}