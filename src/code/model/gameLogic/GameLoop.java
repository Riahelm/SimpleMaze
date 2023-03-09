package code.model.gameLogic;


import code.model.world.api.GameMap;
import code.model.world.impl.GameMapImpl;
import code.model.world.impl.Position2DImpl;
import code.controller.GameController;
import code.model.actor.api.Entity;
import code.model.actor.impl.Character;

import javax.swing.*;
import java.io.IOException;


public class GameLoop{
        GameMap myWorld;
        Entity myChar;
        GameController gc;
    public GameLoop(GameController gc){
        try {
            myWorld = new GameMapImpl("World", 16, this.getClass().getResource("../../../resources/worlds/SecondMap"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        myChar = new Character("Enemy", myWorld.getSpecificTile(new Position2DImpl(1,1)));
        this.gc = gc;

        gc.updateState(keyPressed -> {
            myWorld.move(keyPressed, myChar);
        });

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
    }
}

