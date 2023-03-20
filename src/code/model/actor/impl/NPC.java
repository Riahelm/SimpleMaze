package code.model.actor.impl;

import code.model.world.api.Tile;

import java.util.Optional;

public class NPC extends EntityAb{
    private String personalDialogue;

    public NPC() {
        super(EntityType.NPC);
        this.personalDialogue = "My creator didn't give me any dialogue!!";
    }
    public NPC(String dialogue) {
        this();
        this.personalDialogue = dialogue;
    }



    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    public String getDialogue(){
        return this.personalDialogue;
    }
}
