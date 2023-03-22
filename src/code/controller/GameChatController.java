package code.controller;

import code.view.listener.OnMessageSentListener;

public class GameChatController {
    private static GameChatController instance;
    private OnMessageSentListener msgListener;
    private GameChatController(){}

    public static GameChatController getInstance(){
        if(instance == null){
            instance = new GameChatController();
        }
        return instance;
    }

    public void receiveMessage(OnMessageSentListener l){
        msgListener = l;
    }
    public void sendMessage(String message){
        msgListener.receiveMessage(message);
    }
}
