package code.model.actor.impl;

import code.model.actor.api.InteractableEntity;
import code.util.Pair;

class NPC extends EntityTemplate implements InteractableEntity {


    private Pair<String, Boolean> personalQuestion;

    NPC() {
        super();
    }

    @Override
    public EntityType getType() {
        return EntityType.NPC;
    }

    NPC(Pair<String, Boolean> question) {
        this();
        this.personalQuestion = question;
    }
    @Override
    public void onInteract(EntityType type) {
        //They can't be touched by anything that isn't a Character, otherwise Enemies would kill NPCs
        if(type.equals(EntityType.CHARACTER)){
            gVMCC.sendMessage("The NPC asks you a question...");
            gVM.askAQuestion(this.personalQuestion);
            gVM.increaseScore();
            this.setLifeTo(false); //This says a lot about society
        }
    }
}
