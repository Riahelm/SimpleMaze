package code.model.gameLogic;


import code.controller.GameChatController;
import code.controller.GameController;
import code.model.actor.api.Entity;
import code.model.actor.impl.*;
import code.model.actor.impl.Character;
import code.model.world.api.GameMap;
import code.model.world.impl.GameMapImpl;
import code.model.world.impl.TileType;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;


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

        this.Init();

        this.addEntities();

    }

    private void addEntities() {

        myWorld.addEntityToWorld(1,1, EntityFactory.createEntity(EntityType.CHARACTER));
        myWorld.addEntityToWorld(2,2,EntityFactory.createNPC("I heard there was an exit..."));
        for (int i = 0; i < 20; i++) {
            int x = new Random().nextInt(1, 16);
            int y = new Random().nextInt(1,16);
            if (!(myWorld.getSpecificTile(x,y).getTileType().equals(TileType.IMPASSABLE) ||
                myWorld.getSpecificTile(x,y).getEntity().isPresent())) myWorld.addEntityToWorld(x,y, EntityFactory.createEntity(EntityType.ENEMY));
        }

    }

    private void Init() {

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
            System.out.println(myWorld.getEntities());
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

