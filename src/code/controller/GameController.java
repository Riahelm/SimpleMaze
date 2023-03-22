package code.controller;

import code.view.Direction;
import code.view.GameOverState;
import code.view.listener.*;

public class GameController {

    private static GameController instance;
    private OnNewStateListener gamePListener;
    private OnKeyPressedListener gameLListener;
    private GameStateListener gameSListener; //GameState, is the game screen itself
    private GameOverListener gamePSListener; //GamePanelState, checks if it's won or lost

    private GameController(){}

    public static GameController getInstance(){
        if(instance == null){
            instance = new GameController();
        }
        return instance;
    }
    public void refresh(OnNewStateListener l){
        gamePListener = l;
        gamePListener.useUpdatedState(gameSListener.getNewState());
    }
    public void updateState(OnKeyPressedListener l){
        gameLListener = l;
    }

    public void onKeyPressed(Direction key){
        gameLListener.useKeyPressed(key);
        gamePListener.useUpdatedState(gameSListener.getNewState());
    }

    public void getNewState(GameStateListener l){
        gameSListener = l;
    }

    public void setGameOverListener(GameOverListener l){
        gamePSListener = l;
    }

    public void finishGame(GameOverState state){
        gamePSListener.setToGameOver(state);
    }
}
