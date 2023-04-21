package code.controller;

import code.controller.listeners.GameAreaListener;
import code.controller.listeners.GameLogicListener;
import code.controller.listeners.GamePanelListener;
import code.model.gameLogic.GameLogic;
import code.util.Pair;
import code.view.Direction;
import code.view.GameOverState;

public class GameController {

    private static GameController instance;
    private GameAreaListener myGameAreaInstructions;
    private GameLogicListener myGameLogicInstructions;
    private GamePanelListener gamePanelStateListener; //GamePanelState, checks if it's won or lost

    private GameController(){}

    public static GameController getInstance(){
        if(instance == null){
            instance = new GameController();
        }
        return instance;
    }

    public void setGameAreaListener(GameAreaListener l){
        myGameAreaInstructions = l;
    }
    public void setGameLogicListener(GameLogicListener l){
        myGameLogicInstructions = l;
    }
    public void setGameOverListener(GamePanelListener l){
        gamePanelStateListener = l;
    }
    public void computeTurn(Direction key){
        myGameLogicInstructions.computeTurn(key);
        myGameAreaInstructions.useUpdatedState(myGameLogicInstructions.getGameState());
    }

    public void askAQuestion(Pair<String, Boolean> question){
        gamePanelStateListener.askAQuestion(question);
    }
    public void forceRefresh(){
        myGameAreaInstructions.useUpdatedState(myGameLogicInstructions.getGameState());
    }

    public void finishGame(GameOverState state, int score){
        gamePanelStateListener.setToGameOver(state, score);
    }

    public void restartGame() {
        GameLogic.resetToFirstWorld();
    }
}
