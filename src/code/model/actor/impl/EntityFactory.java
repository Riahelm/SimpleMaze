package code.model.actor.impl;

import code.controller.GameChatController;
import code.model.actor.api.Entity;

public class EntityFactory {

    public static Entity createEntity(EntityType type){
        return switch(type){
            case CHARACTER -> new Character();
            case NPC -> new NPC();
            case ENEMY ->  new Enemy();
        };
    }
    public static Entity createCharacter(){
        return new Character();
    }

    public static Entity createEnemy(){
        return new Enemy();
    }
    public static Entity createNPC(String dialogue){
            return new NPC(dialogue);
    }
}
