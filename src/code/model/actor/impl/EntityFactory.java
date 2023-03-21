package code.model.actor.impl;

import code.controller.GameChatController;
import code.model.actor.api.Entity;

public class EntityFactory {

    public static Entity createEntity(EntityType type, GameChatController gcc){
        return switch(type){
            case NPC -> new NPC(gcc);
            case CHARACTER -> new Character(gcc);
            case ENEMY ->  new Enemy(gcc);
        };
    }
    public static Entity createNPC(String dialogue, GameChatController gCC){
            return new NPC(dialogue, gCC);
    }
}
