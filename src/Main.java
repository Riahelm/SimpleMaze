import code.model.gameLogic.GameLogic;
import code.view.MainFrame;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                StartGame();
            } catch (IOException err) {
                throw new RuntimeException(err);
            }
        });
    }
    private static void StartGame() throws IOException{
        GameLogic gameLogic = new GameLogic();
        MainFrame myMainFrame = new MainFrame();
        myMainFrame.setVisible(true);
    }
}
