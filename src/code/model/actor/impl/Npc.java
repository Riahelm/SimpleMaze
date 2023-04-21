package code.model.actor.impl;

import code.util.Pair;

class Npc extends EntityTemplate {


    private Pair<String, Boolean> personalQuestion;

    Npc() {
        super(EntityType.NPC);
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
        return true;
    }

    public Pair<String, Boolean> getQuestion(){
        return this.personalQuestion;
    }

}
