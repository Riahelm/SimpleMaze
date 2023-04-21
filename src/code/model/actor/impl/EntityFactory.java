package code.model.actor.impl;

import code.model.actor.api.Entity;
import code.util.Pair;

public class EntityFactory {

    public static Entity createEntity(EntityType type){
        return switch(type){
            case CHARACTER -> new Character();
            case NPC -> new Npc();
            case ENEMY ->  new Enemy();
        };
    }
    public static Character createCharacter(){
        return new Character();
    }

    public static Enemy createEnemy(){
        return new Enemy();
    }
    public static Npc createNPC(Pair<String, Boolean> dialogue){
            return new Npc(dialogue);
    }
}
