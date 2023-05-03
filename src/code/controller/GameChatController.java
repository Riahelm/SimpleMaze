package code.controller;

import code.controller.listeners.ChatAreaListener;

public class GameChatController {
    private static GameChatController instance;
    private ChatAreaListener msgListener;

    public static GameChatController getInstance(){
        if(instance == null){
            instance = new GameChatController();
        }
        return instance;
    }

    public void setChatAreaListener(ChatAreaListener l){
        msgListener = l;
    }

    public void sendMessage(String message){
        msgListener.receiveMessage(message);
    }
    public void updateScore(int score){
        msgListener.receiveScore(score);
    }
}
