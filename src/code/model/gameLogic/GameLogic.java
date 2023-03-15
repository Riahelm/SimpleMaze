package code.model.gameLogic;


import code.controller.GameController;
import code.model.actor.api.Entity;
import code.model.actor.impl.Character;
import code.model.actor.impl.Enemy;
import code.model.actor.impl.NPC;
import code.model.world.api.GameMap;
import code.model.world.impl.GameMapImpl;
import code.model.world.impl.Position2DImpl;

import javax.swing.*;
import java.io.IOException;


public class GameLogic {
        GameMap myWorld;
        Entity myChar;
        Entity myEnemy;
        Entity myNPC;
        GameController gc;
    public GameLogic(GameController gc){
        try {
            myWorld = new GameMapImpl("World", 16, this.getClass().getResource("../../../resources/worlds/SecondMap"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.gc = gc;

        myChar = new Character("CHARACTER");
        myWorld.addEntity(myChar);
        myEnemy = new Enemy("ENEMY");
        myWorld.addEntity(myEnemy);
        myNPC = new NPC("NPC");
        myWorld.addEntity(myNPC);

        myWorld.setEntityOnPosition(new Position2DImpl(2,2), myChar);
        myWorld.setEntityOnPosition(new Position2DImpl(4,4), myEnemy);
        myWorld.setEntityOnPosition(new Position2DImpl(1,1), myNPC);

        gc.getNewState(() -> {
            Icon[][] myRes = new Icon[16][16];
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if(myWorld.getSpecificTile(i, j).getEntity().isPresent()){
                        myRes[i][j] = myWorld.getSpecificTile(i, j).getEntity().get().getSprite();
                    }else{
                        myRes[i][j] = new ImageIcon(myWorld.getGrid()[i][j].getImage());
                    }
                }
            }
            return myRes;
        });

        gc.updateState(keyPressed -> {
            for (Entity myEnt : myWorld.getEntities()) {
                if(myEnt instanceof Character){
                    myWorld.move(keyPressed, myEnt);
                }else{
                    myWorld.move(myEnt);
                }
            }
        });
    }
}

