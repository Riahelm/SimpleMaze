package code.model.actor.impl;

import code.model.world.api.Tile;

import java.util.Optional;

public class NPC extends EntityAb{

    public NPC(String dialogue) {
        super(EntityType.NPC);
        this.personalDialogue = dialogue;
    }

    public NPC(String dialogue, Tile startingTile) {
        this(dialogue);
        this.setTile(Optional.of(startingTile));
    }
    private String personalDialogue;


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
