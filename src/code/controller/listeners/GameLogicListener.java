package code.controller.listeners;

import code.util.api.Counter;
import code.view.Direction;

import javax.swing.*;

public interface GameLogicListener {
    void computeTurn(Direction keyPressed);

    Icon[][] getGameState();

    void switchToNextWorld();

    void resetPlayerStatus();

    void incrementScore();

    int getScore();
}
