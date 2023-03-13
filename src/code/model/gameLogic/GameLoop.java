package code.model.gameLogic;


import code.model.actor.impl.EntityImpl;
import code.model.util.Pair;
import code.model.world.api.GameMap;
import code.model.world.api.Tile;
import code.model.world.impl.GameMapImpl;
import code.model.world.impl.Position2DImpl;
import code.controller.GameController;
import code.model.actor.api.Entity;

import javax.swing.*;
import java.io.IOException;
import java.util.*;


public class GameLoop{
        GameMap myWorld;
        Entity myChar;
        List<Entity> myEntities;
        GameController gc;
    public GameLoop(GameController gc){
        myEntities = new LinkedList<>();
        try {
            myWorld = new GameMapImpl("World", 16, this.getClass().getResource("../../../resources/worlds/SecondMap"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.gc = gc;

        myChar = new EntityImpl("CHARACTER");
        myEntities.add(myChar);
        myWorld.setEntityOnPosition(new Position2DImpl(2,2), myChar);

        gc.getNewState(() -> {
            Icon[][] myRes = new Icon[16][16];
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if(myWorld.getSpecificTile(new Position2DImpl(i,j)).getEntity().isPresent()){
                        myRes[i][j] = myWorld.getSpecificTile(i, j).getEntity().get().getSprite();
                    }else{
                        myRes[i][j] = new ImageIcon(myWorld.getGrid()[i][j].getImage());
                    }
                }
            }
            return myRes;
        });

        gc.updateState(keyPressed -> {
            myEntities.forEach(e -> myWorld.move(keyPressed, e));
            //TODO change list to update the list of cells, and iterate through the entity list
        });
    }
}

