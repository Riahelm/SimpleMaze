package code.controller;

import code.controller.listeners.OnMessageSentListener;
import code.util.Pair;

public class GameChatController {
    private static GameChatController instance;
    private OnMessageSentListener msgListener;

    public static GameChatController getInstance(){
        if(instance == null){
            instance = new GameChatController();
        }
        return instance;
    }

    public void setMessageListener(OnMessageSentListener l){
        msgListener = l;
    }

    public void sendMessage(String message){
        msgListener.receiveMessage(message);
    }
    public void updateScore(int score){
        msgListener.receiveScore(score);
    }
}
