package code.controller.listeners;

import code.util.Pair;
import code.view.GameOverState;

public interface GamePanelListener {
    void setToGameOver(GameOverState state, int score);

    void askAQuestion(Pair<String, Boolean> question);
}
