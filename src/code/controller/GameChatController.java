package code.controller;

import code.controller.listeners.ChatAreaListener;

public class GameChatController {
    private static GameChatController instance;
    private ChatAreaListener chatAreaInstructions;

    public static GameChatController getInstance(){
        if(instance == null){
            instance = new GameChatController();
        }
        return instance;
    }

    public void setChatAreaInstructions(ChatAreaListener l){
        chatAreaInstructions = l;
    }

    public void sendMessage(String message){
        chatAreaInstructions.receiveMessage(message);
    }
    public void updateScore(int score){
        chatAreaInstructions.receiveScore(score);
    }

    public void resetChat(){ chatAreaInstructions.resetChat();}
}
