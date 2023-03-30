package code.view.listener;

public interface OnMessageSentListener {
    void receiveMessage(String message);

    void receiveScore(int score);
}
