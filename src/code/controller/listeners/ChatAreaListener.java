package code.controller.listeners;

public interface ChatAreaListener {
    void receiveMessage(String message);

    void receiveScore(int score);
}
