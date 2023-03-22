package code.controller;

import code.view.Direction;
import code.view.listener.GameStateListener;
import code.view.listener.OnKeyPressedListener;
import code.view.listener.OnNewStateListener;

public class GameController {
    private OnNewStateListener gamePListener;
    private OnKeyPressedListener gameLListener;
    private GameStateListener gameSListener;

    public GameController(){}
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

    public void onWinConditionMet(){

    }

    public void onLossConditionMet(){

    }

}
