package code.model.gameLogic;


import code.controller.GameChatController;
import code.controller.GameController;
import code.model.actor.api.Entity;
import code.model.actor.impl.Character;
import code.model.actor.impl.Enemy;
import code.model.actor.impl.NPC;
import code.model.world.api.GameMap;
import code.model.world.impl.GameMapImpl;

import javax.swing.*;
import java.io.IOException;


public class GameLogic {
        GameMap myWorld;
        GameController gc;
    public GameLogic(GameController gc, GameChatController gcc){
        try {
            myWorld = new GameMapImpl("World", 16, this.getClass().getResource("../../../resources/worlds/SecondMap"), gcc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.gc = gc;

        myWorld.addEntityToWorld(2,2, new Character());
        myWorld.addEntityToWorld(4,4, new Enemy());
        myWorld.addEntityToWorld(1,1, new NPC("Dialogue here!"));

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
                if (myEnt.isAlive()) {
                    if (myEnt instanceof Character) {
                        myWorld.move(keyPressed, myEnt);
                    } else {
                        myWorld.move(myEnt);
                    }
                }
            }
            for (Entity entityToDelete: myWorld.getDeadEntities()) {
                myWorld.getEntities().remove(entityToDelete);
            }
        });
    }
}

