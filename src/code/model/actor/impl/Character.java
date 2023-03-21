package code.model.actor.impl;

public class Character extends EntityAb{


    Character() {
        super(EntityType.CHARACTER);
    }

    @Override
    public boolean canMove() {
        return true;
    }

}
