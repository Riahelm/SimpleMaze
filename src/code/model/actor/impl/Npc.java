package code.model.actor.impl;

import code.model.actor.api.InteractableEntity;
import code.util.Pair;

class Npc extends EntityTemplate implements InteractableEntity {


    private Pair<String, Boolean> personalQuestion;

    Npc() {
        super();
    }

    @Override
    public EntityType getType() {
        return EntityType.NPC;
    }

    Npc(Pair<String, Boolean> question) {
        this();
        this.personalQuestion = question;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    public Pair<String, Boolean> getQuestion(){
        return this.personalQuestion;
    }

    @Override
    public void onInteract(EntityType type) {
        if(type.equals(EntityType.CHARACTER)){
            gCC.sendMessage("The NPC asks you a question...");
            gc.askAQuestion(this.personalQuestion);
            gc.increaseScore();
            this.getTile().resetTile();
            this.setLifeTo(false); //This says a lot about society
        }
    }
}
