package code.model.gameLogic;


import code.controller.GameChatController;
import code.controller.GameController;
import code.model.actor.impl.Character;
import code.model.actor.api.Entity;
import code.model.util.api.Counter;
import code.model.util.impl.CounterImpl;
import code.model.world.api.GameMap;
import code.model.world.impl.GameMapImpl;

import javax.swing.*;
import java.io.IOException;


public class GameLogic {

        static GameMap currentWorld;
        GameController gc;
        GameChatController gCC;
        private static int levelCounter;
        public static Counter playerScore;

    public GameLogic(){
        levelCounter = 1;
        playerScore = new CounterImpl();
        try {
            currentWorld = new GameMapImpl(this.getClass().getResource("../../../resources/worlds/Map_" + levelCounter));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.gc = GameController.getInstance();
        this.gCC = GameChatController.getInstance();

        this.init();

    }

    public static Counter getLevelCounter() {
        return playerScore;
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

    public static void switchToNextWorld(){
        try {
            levelCounter += 1;
            currentWorld = new GameMapImpl(GameLogic.class.getResource("../../../resources/worlds/Map_" + levelCounter));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

