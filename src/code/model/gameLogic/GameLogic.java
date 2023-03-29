package code.model.gameLogic;


import code.controller.GameChatController;
import code.controller.GameController;
import code.model.actor.impl.Character;
import code.model.actor.impl.EntityFactory;
import code.model.actor.api.Entity;
import code.model.world.api.GameMap;
import code.model.world.impl.GameMapImpl;
import code.model.world.impl.TileType;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;


public class GameLogic {

        GameMap currentWorld;
        GameController gc;
        GameChatController gCC;

    public GameLogic(){

        try {
            currentWorld = new GameMapImpl(this.getClass().getResource("../../../resources/worlds/FirstMap"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.gc = GameController.getInstance();
        this.gCC = GameChatController.getInstance();

        this.init();

    }

    private void init() {

        gc.getNewState(() -> {
            Icon[][] myRes = new Icon[16][16];
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if(currentWorld.getSpecificTile(i, j).getEntity().isPresent()){
                        myRes[i][j] = currentWorld.getSpecificTile(i, j).getEntity().get().getSprite();
                    }else{
                        myRes[i][j] = currentWorld.getGrid()[i][j].getImage();
                    }
                }
            }
            return myRes;
        });

        gc.updateState(keyPressed -> {
            for (Entity myEnt : currentWorld.getEntities()) {
                if (myEnt.isAlive()) {
                    if (myEnt instanceof Character) {
                        currentWorld.move(keyPressed, myEnt);
                    } else {
                        currentWorld.move(myEnt);
                    }
                }
            }

            for (Entity entityToDelete: currentWorld.getDeadEntities()) {
                currentWorld.getEntities().remove(entityToDelete);
            }

        });

    }
}

