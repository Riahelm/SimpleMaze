package code.model.actor.impl;

import code.controller.GameChatController;
import code.model.actor.api.Entity;

public class EntityFactory {

    public static Entity createEntity(EntityType type, GameChatController gCC){
        return switch(type){
            case CHARACTER -> new Character(gCC);
            case NPC -> new NPC(gCC);
            case ENEMY ->  new Enemy(gCC);
        };
    }
    public static Entity createCharacter(GameChatController gCC){
        return new Character(gCC);
    }

    public static Entity createEnemy(GameChatController gCC){
        return new Enemy(gCC);
    }
    public static Entity createNPC(String dialogue, GameChatController gCC){
            return new NPC(dialogue, gCC);
    }
}
