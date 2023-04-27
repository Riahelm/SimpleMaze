package code.model.actor.impl;

import code.model.actor.api.Entity;
import code.model.world.api.Tile;
import code.util.Pair;

public class EntityFactory {
    public static Character createCharacter(){
        return new Character();
    }
    public static Npc createNPC(Pair<String, Boolean> dialogue){
        return new Npc(dialogue);
    }
    public static Enemy createEnemy(){
        return new Enemy();
    }
    public static SmartEnemy createSmartEnemy(Tile[][] grid){
        return new SmartEnemy(grid);
    }
    public static Entity createPhantom(Tile[][] grid) {
        return new Phantom(grid);
    }
}
