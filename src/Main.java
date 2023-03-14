import code.controller.Controller;
import code.controller.GameController;
import code.exceptions.EntityAlreadyPresentException;
import code.model.gameLogic.GameLogic;
import code.view.MainFrame;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                StartGame();
            } catch (IOException | EntityAlreadyPresentException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private static void StartGame() throws IOException, EntityAlreadyPresentException {
        Controller mainController = new Controller();
        GameController gameController = new GameController();
        GameLogic gameLogic = new GameLogic(gameController);
        MainFrame myMainFrame = new MainFrame(mainController,
                gameController);
        myMainFrame.setVisible(true);
    }
}
