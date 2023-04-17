package code.model.actor.impl;

class Npc extends EntityTemplate {
    private String personalDialogue;

    Npc() {
        super(EntityType.NPC);
        this.personalDialogue = "My creator didn't give me any dialogue!!";
    }
    Npc(String dialogue) {
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
