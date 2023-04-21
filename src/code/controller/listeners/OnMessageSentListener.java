package code.controller.listeners;

import code.util.Pair;

public interface OnMessageSentListener {
    void receiveMessage(String message);

    void receiveScore(int score);
}
