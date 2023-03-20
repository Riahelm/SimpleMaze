package code.model.actor.impl;

import code.model.actor.api.Entity;

public class EntityFactory {

    public static Entity createEntity(EntityType type){
        return switch(type){
            case NPC -> new NPC();
            case CHARACTER -> new Character();
            case ENEMY ->  new Enemy();
        };
    }
    public static Entity createNPC(String dialogue){
            return new NPC(dialogue);
    }
}
