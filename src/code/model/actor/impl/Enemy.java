package code.model.actor.impl;

public class Enemy extends EntityAb{

    Enemy() {
        super(EntityType.ENEMY);
    }

    @Override
    public boolean canMove() {
        return true;
    }

}
