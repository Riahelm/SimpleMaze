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
        this.addEntities();

    }

    private void addEntities() {

        currentWorld.addEntityToWorld(1,1, EntityFactory.createCharacter());
        currentWorld.addEntityToWorld(2,2,EntityFactory.createNPC("I heard there was an exit..."));
        for (int i = 0; i < 20; i++) {
            int x = new Random().nextInt(1, 16);
            int y = new Random().nextInt(1,16);
            if (!(currentWorld.getSpecificTile(x,y).getTileType().equals(TileType.IMPASSABLE) ||
                currentWorld.getSpecificTile(x,y).getEntity().isPresent())) currentWorld.addEntityToWorld(x,y, EntityFactory.createEnemy());
        }

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

