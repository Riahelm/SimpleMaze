package code.model.gameLogic;

import code.controller.GameController;
import code.controller.listeners.GameLogicListener;
import code.model.actor.api.ActiveEntity;
import code.model.actor.impl.Character;
import code.model.actor.api.Entity;
import code.util.OperateOnMatrix;
import code.util.Pair;
import code.util.api.Counter;
import code.util.impl.CounterImpl;
import code.model.world.api.GameMap;
import code.model.world.impl.GameMapImpl;
import code.view.Direction;

import javax.swing.*;
import java.io.IOException;


public class GameLogic {
    private static GameController gc;
    private static GameMap currentWorld;
    private static Pair<? extends Counter, ? extends Counter> playerInfo;

    public GameLogic(){
                                //Level                       Score
        playerInfo = new Pair<>(new CounterImpl(1), new CounterImpl());
        try {
            currentWorld = new GameMapImpl(this.getClass().getResource("../../../resources/worlds/Map_" + playerInfo.x().getValue()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gc = GameController.getInstance();

        this.init();

    }

    public static Counter getScoreCounter(){return playerInfo.y();}

    private void init() {

        gc.setGameLogicListener(new GameLogicListener() {
            @Override
            public void computeTurn(Direction keyPressed) {

                for (Entity myEnt : currentWorld.getEntities()) {
                    if (myEnt.isAlive() && myEnt instanceof ActiveEntity activeEntity) {
                        if (myEnt instanceof Character character) {
                            currentWorld.performTurn(keyPressed, character);
                        } else {
                            currentWorld.performTurn(activeEntity);
                        }
                    }
                }

                currentWorld.getEntities().removeIf(ent -> !ent.isAlive());
            }

            @Override
            public Icon[][] getGameState() {
                int mapSize = currentWorld.getMapSize();
                Icon[][] myRes = new Icon[mapSize][mapSize];
                OperateOnMatrix.operateOnEachElement(myRes, (i, j) -> {
                    if(currentWorld.getSpecificTile(i, j).getEntity().isPresent()){
                        myRes[i][j] = currentWorld.getSpecificTile(i, j).getEntity().get().getSprite();
                    }else{
                        myRes[i][j] = currentWorld.getGrid()[i][j].getImage();
                    }
                });
                return myRes;
            }

            @Override
            public void switchToNextWorld() {
                try{
                    currentWorld.getEntities().forEach(e -> e.setLifeTo(false));
                    playerInfo.x().increment();
                    currentWorld = new GameMapImpl(GameLogic.class.getResource("../../../resources/worlds/Map_" + playerInfo.x().getValue()));
                }catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void resetPlayerStatus() {
                try{
                    playerInfo = new Pair<>(new CounterImpl(1), new CounterImpl(0));
                    currentWorld = new GameMapImpl(GameLogic.class.getResource("../../../resources/worlds/Map_" + playerInfo.x().getValue()));
                }catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void incrementScore(){
                playerInfo.y().increment();
            }

            @Override
            public int getScore() {
                return playerInfo.y().getValue();
            }
        });
    }

}

