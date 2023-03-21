package code.model.actor.impl;

import code.controller.GameChatController;
import code.model.world.api.Tile;

public class NPC extends EntityAb{
    private String personalDialogue;

    NPC(GameChatController gCC) {
        super(EntityType.NPC, gCC);
        this.personalDialogue = "My creator didn't give me any dialogue!!";
    }
    NPC(String dialogue, GameChatController gCC) {
        this(gCC);
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
