package code.model.actor.impl;

import code.model.actor.api.Entity;

import code.util.Pair;

public class EntityFactory {
    public static Character createCharacter(){
        return new Character();
    }
    public static NPC createNPC(Pair<String, Boolean> dialogue){
        return new NPC(dialogue);
    }
    public static Enemy createEnemy(){
        return new Enemy();
    }
    public static SmartEnemy createSmartEnemy(){
        return new SmartEnemy();
    }
    public static Entity createPhantom() {
        return new Phantom();
    }
}
