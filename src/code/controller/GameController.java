package code.controller;

import code.view.listener.GameStateListener;
import code.view.listener.OnKeyPressedListener;
import code.view.listener.OnNewStateListener;

import javax.swing.*;

public class GameController {

    private Icon[][] gScrnToShow;
    private OnNewStateListener gamePListener;
    private OnKeyPressedListener gameLListener;
    private GameStateListener gameSListener;

    public GameController(){
        gScrnToShow = new Icon[16][16];
    }
    public void refresh(OnNewStateListener l){
        gamePListener = l;
        gamePListener.useUpdatedState(gScrnToShow);
    }
    public void updateState(OnKeyPressedListener l){
        gameLListener = l;
    }

    public void onKeyPressed(String key){
        gameLListener.useKeyPressed(key);
        gScrnToShow = gameSListener.getNewState();
        gamePListener.useUpdatedState(gScrnToShow);
    }

    public void getNewState(GameStateListener l){
        gameSListener = l;
        gScrnToShow = gameSListener.getNewState();
    }
    // public
}
