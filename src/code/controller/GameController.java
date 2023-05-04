package code.controller;

import code.controller.listeners.GameAreaListener;
import code.controller.listeners.GameLogicListener;
import code.controller.listeners.GamePanelListener;
import code.model.actor.impl.NPCQuestions;
import code.util.Pair;
import code.util.api.Counter;
import code.view.Direction;
import code.view.GameOverState;

public class GameController {

    private static GameController instance;
    private GameAreaListener myGameAreaInstructions;
    private GameLogicListener myGameLogicInstructions;
    private GamePanelListener myGamePanelInstructions;

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
    public void setGamePanelListener(GamePanelListener l){
        myGamePanelInstructions = l;
    }
    public void computeTurn(Direction key){
        myGameLogicInstructions.computeTurn(key);
        myGameAreaInstructions.useUpdatedState(myGameLogicInstructions.getGameState());
    }

    public void askAQuestion(Pair<String, Boolean> question){
        myGamePanelInstructions.askAQuestion(question);
    }
    public void forceRefresh(){
        myGameAreaInstructions.useUpdatedState(myGameLogicInstructions.getGameState());
    }

    public void increaseScore(){
        myGameLogicInstructions.incrementScore();
        GameChatController.getInstance().updateScore(myGameLogicInstructions.getPlayerInfo().y().getValue());
    }

    public void goToNextWorld(){
        this.increaseScore();
        myGameLogicInstructions.switchToNextWorld();
    }
    public void finishGame(GameOverState state){
        myGamePanelInstructions.setToGameOver(state);
    }

    public Pair<? extends Counter,? extends Counter> getPlayerInfo(){
        return myGameLogicInstructions.getPlayerInfo();
    }

    public void restartGame() {
        NPCQuestions.resetQuestions();
        myGameLogicInstructions.resetPlayerStatus();
        GameChatController.getInstance().updateScore(myGameLogicInstructions.getPlayerInfo().y().getValue());
    }
}
