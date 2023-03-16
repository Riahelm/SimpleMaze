package code.controller;

import code.view.listener.MessageSenderListener;
import code.view.listener.OnMessageSentListener;

public class GameChatController {

    OnMessageSentListener msgListener;

    public GameChatController(){}

    public void receiveMessage(OnMessageSentListener l){
        msgListener = l;
    }
    public void sendMessage(MessageSenderListener l){
        msgListener.receiveMessage(l.sendAMessage());
    }
}
