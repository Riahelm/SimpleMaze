package code.model.actor.impl;

import code.model.world.api.Tile;

public class NPC extends EntityAb{

    private String personalDialogue;
    public NPC(String name) {
        super(name);
    }

    public NPC(String name, String dialogue){
        super (name);
        this.personalDialogue = dialogue;
    }

    public NPC(String name, Tile startingTile) {
        super(name, startingTile);
    }

    public NPC(String name, Tile startingTile, String dialogue){
        super(name, startingTile);
        this.personalDialogue = dialogue;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean canDie() {
        return false;
    }

    public String getDialogue(){
        return this.personalDialogue;
    }
}
