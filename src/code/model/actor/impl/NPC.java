package code.model.actor.impl;

public class NPC extends EntityAb{
    private String personalDialogue;

    NPC() {
        super(EntityType.NPC);
        this.personalDialogue = "My creator didn't give me any dialogue!!";
    }
    NPC(String dialogue) {
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
