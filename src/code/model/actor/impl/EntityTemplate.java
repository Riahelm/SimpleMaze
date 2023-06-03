package code.model.actor.impl;

import code.viewModel.GameViewModel;
import code.model.world.api.Tile;
import code.model.actor.api.Entity;

import javax.swing.*;
import java.util.Objects;


public abstract class EntityTemplate implements Entity {
    final private Icon eSprite;
    private Tile eTile;
    private boolean isAlive;

    //These are here for the sake of not having verbose code
    protected GameViewModel gVM;
    protected GameViewModel.ChatViewModel gVMCC;

    protected EntityTemplate(){
        this.eSprite = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../../../resources/entities/" + this.getType().toString() + ".JPG")));
        this.isAlive = true;
        gVMCC = GameViewModel.ChatViewModel.getInstance();
        gVM = GameViewModel.getInstance();
    }

    public void setTile(Tile tile){
        this.eTile = tile;
    }

    public Icon getSprite(){
        return this.eSprite;
    }

    public abstract EntityType getType();

    public Tile getTile(){
        return this.eTile;
    }

    public boolean isAlive(){
        return this.isAlive;
    }

    public void setLifeTo(boolean flag){
        this.isAlive = flag;
    }

    //Logic of each subType of entity here
    public boolean canMove(){
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityTemplate entityTemplate = (EntityTemplate) o;
        return getType().equals(entityTemplate.getType()) && eTile.equals(entityTemplate.eTile);
    }
}
