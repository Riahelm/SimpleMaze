package code.controller.listeners;

import code.view.Direction;

import javax.swing.*;

public interface GameLogicListener {
    void computeTurn(Direction keyPressed);

    Icon[][] getGameState();
}
