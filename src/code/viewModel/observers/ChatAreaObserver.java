package code.viewModel.observers;

public interface ChatAreaObserver {
    void receiveMessage(String message);
    void receiveScore(int score);
    void resetChat();
}
