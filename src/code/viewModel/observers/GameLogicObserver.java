package code.viewModel.observers;

import code.util.Pair;
import code.util.api.Counter;
import code.view.Direction;

import javax.swing.*;

public interface GameLogicObserver {
    void computeTurn(Direction keyPressed);
    void switchToNextWorld();
    void resetPlayerStatus();
    Icon[][] getGameState();
    Pair<? extends Counter,? extends Counter> getPlayerInfo();

    Integer[][] getTerminalGameState();
}
