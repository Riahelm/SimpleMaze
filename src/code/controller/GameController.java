package code.controller;

import code.view.Direction;
import code.view.GameOverState;
import code.view.listener.*;

public class GameController {

    private static GameController instance;
    private int mapSize;

    private MapChangedListener mapChangedListener;
    private OnNewStateListener gamePanelListener;
    private OnKeyPressedListener gameKeyListener;
    private GameStateListener gameStateListener; //GameState, is the game screen itself
    private GameOverListener gamePanelStateListener; //GamePanelState, checks if it's won or lost

    private GameController(){}

    public static GameController getInstance(){
        if(instance == null){
            instance = new GameController();
        }
        return instance;
    }
    public void refresh(OnNewStateListener l){
        gamePanelListener = l;
        gamePanelListener.useUpdatedState(gameStateListener.getNewState());
    }
    public void updateState(OnKeyPressedListener l){
        gameKeyListener = l;
    }

    public void onKeyPressed(Direction key){
        gameKeyListener.useKeyPressed(key);
        gamePanelListener.useUpdatedState(gameStateListener.getNewState());
    }

    public void getNewState(GameStateListener l){
        gameStateListener = l;
    }

    public void onGameOver(GameOverListener l){
        gamePanelStateListener = l;
    }

    public void finishGame(GameOverState state, int score){
        gamePanelStateListener.setToGameOver(state, score);
    }

    public void onMapChanged(MapChangedListener l){
        mapChangedListener = l;
    }

    public void setMapSize(int mapSize){
        this.mapSize = mapSize;
    }
    public int getMapSize(){
        return this.mapSize;
    }

    public void changeMap(int mapSize){
        mapChangedListener.onMapChanged(mapSize);
    }
}
