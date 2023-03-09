package code.controller;

import code.view.listener.GameStateListener;
import code.view.listener.OnKeyPressedListener;
import code.view.listener.OnNewStateListener;

import javax.swing.*;

public class GameController {

    private Icon[][] screenToShow;
    private OnNewStateListener gamePListener;
    private OnKeyPressedListener gameLListener;
    private GameStateListener gameSListener;

    public GameController(){
        screenToShow = new Icon[16][16];
    }
    public void refresh(OnNewStateListener l){
        gamePListener = l;
        gamePListener.useUpdatedState(screenToShow);
    }
    public void updateState(OnKeyPressedListener l){
        gameLListener = l;
    }

    public void onKeyPressed(String key){
        gameLListener.useKeyPressed(key);
        screenToShow = gameSListener.getNewState();
        gamePListener.useUpdatedState(screenToShow);
    }

    public void getNewState(GameStateListener l){
        gameSListener = l;
        screenToShow = gameSListener.getNewState();
    }
    // public
}
