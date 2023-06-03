package code.model.gameLogic;

import code.viewModel.GameViewModel;
import code.viewModel.observers.GameLogicObserver;
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
import code.view.GameOverState;

import javax.swing.*;
import java.io.IOException;


public class GameLogic {
    private final GameViewModel gVM;
    private GameMap currentWorld;
    private Pair<? extends Counter, ? extends Counter> playerInfo;

    public GameLogic(){
                                //Level                       Score
        playerInfo = new Pair<>(new CounterImpl(1), new CounterImpl());
        try {
            currentWorld = new GameMapImpl(this.getClass().getResource("../../../resources/worlds/Map_" + playerInfo.first().getValue()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gVM = GameViewModel.getInstance();
        gVM.setGameLogicObserver(new GameLogicObserver() {
            @Override
            public void computeTurn(Direction keyPressed) {
                //Has every entity do its turn, if active, and then removes the entities at the end of the turn
                for (Entity myEnt : currentWorld.getEntities()) {
                    //After instanceof you can automatically declare a variable of that type, this is really handy
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
                //First it takes "a picture" of the map, and then it checks for all entities
                Icon[][] myRes = new Icon[mapSize][mapSize];
                OperateOnMatrix.operateOnEachElement(myRes, (i, j) -> myRes[i][j] = currentWorld.getGrid()[i][j].getImage());
                currentWorld.getEntities().forEach(e -> myRes[e.getTile().getCoords().getPosX()][e.getTile().getCoords().getPosY()] = e.getSprite());
                return myRes;

            }

            @Override
            public void switchToNextWorld() {
                try{
                    currentWorld.getEntities().forEach(e -> e.setLifeTo(false));
                    playerInfo.first().increment();
                    currentWorld = new GameMapImpl(GameLogic.class.getResource("../../../resources/worlds/Map_" + playerInfo.first().getValue()));
                }catch (IOException e) {
                    throw new RuntimeException(e);
                }catch (NullPointerException e){
                    //This was done because stairs are different from exits. The difference is because that way you can have more versatility
                    //during map creation.
                    //The reason for saying you "cheat" is that you can skip levels by pressing p.
                    gVM.finishGame(GameOverState.WIN);
                    throw new NullPointerException("You either cheat or put stairs where an exit is meant to be, but you've won (technically)");
                }
            }

            @Override
            public void resetPlayerStatus() {
                try{
                    //Of course, you start at the first level.
                    playerInfo = new Pair<>(new CounterImpl(1), new CounterImpl(0));
                    currentWorld = new GameMapImpl(GameLogic.class.getResource("../../../resources/worlds/Map_" + playerInfo.first().getValue()));
                }catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public Pair<? extends Counter,? extends Counter> getPlayerInfo() {
                return playerInfo;
            }

            @Override
            public Integer[][] getTerminalGameState() {
                //If a different view were to be added, it could be implemented better, however this is not worth the effort
                //for the case of the terminal view
                int mapSize = currentWorld.getMapSize();
                Integer[][] res = new Integer[mapSize][mapSize];
                OperateOnMatrix.operateOnEachElement(res, (i,j) -> res[i][j] = currentWorld.getGrid()[i][j].getTileType().ordinal());
                currentWorld.getEntities().forEach(e -> res[e.getTile().getCoords().getPosX()][e.getTile().getCoords().getPosY()] = -1-e.getType().ordinal());
                return res;
            }
        });
    }
}

