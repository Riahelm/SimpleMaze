package code.model.actor.impl;

import code.controller.GameChatController;
import code.controller.GameController;
import code.model.world.api.Tile;
import code.model.actor.api.Entity;

import javax.swing.*;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


public abstract class EntityTemplate implements Entity {
    final private Icon eSprite;
    private Tile eTile;
    protected boolean isAlive;

    //These are here for the sake of not having verbose code
    protected GameChatController gCC;
    protected GameController gc;

    protected EntityTemplate(){
        this.eSprite = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../../../resources/entities/" + this.getType().toString() + ".JPG")));
        this.isAlive = true;
        gCC = GameChatController.getInstance();
        gc  = GameController.getInstance();
    }

    public void setTile(Tile tile){
        this.eTile = tile;
    }

    public Icon getSprite(){
        return this.eSprite;
    }

    public abstract EntityType getType();

    public Tile getTile() throws NoSuchElementException{
        return Optional.of(this.eTile).get();
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
