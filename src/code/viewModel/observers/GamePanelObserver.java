package code.viewModel.observers;

import code.util.Pair;
import code.view.GameOverState;

public interface GamePanelObserver {
    void setToGameOver(GameOverState state);

    void askAQuestion(Pair<String, Boolean> question);
}
