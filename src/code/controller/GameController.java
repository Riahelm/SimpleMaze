package code.controller;

import code.view.Directions;
import code.view.listener.GameStateListener;
import code.view.listener.OnKeyPressedListener;
import code.view.listener.OnNewStateListener;

import javax.swing.*;

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

    public void onKeyPressed(Directions key){
        gameLListener.useKeyPressed(key);
        gamePListener.useUpdatedState(gameSListener.getNewState());
    }

    public void getNewState(GameStateListener l){
        gameSListener = l;
    }

}
