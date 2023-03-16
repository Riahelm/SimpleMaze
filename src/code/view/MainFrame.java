package code.view;

import code.controller.Controller;
import code.controller.GameChatController;
import code.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private MainPanel mainPanel;
    private GamePanel gamePanel;
    private SettingsPanel settingsPanel;
    public MainFrame(Controller c, GameController gc, GameChatController gCC){

        mainPanel = new MainPanel(c);
        gamePanel = new GamePanel(gc, gCC);
        //settingsPanel = new SettingsPanel();

        c.setOnNewPage(s -> {
            switch (s) {
                case MENU     -> this.setContentPane(mainPanel);
                case GAME     -> this.setContentPane(gamePanel);
                //case SETTINGS -> this.setContentPane(settingsPanel);
            }
            this.revalidate();
            this.repaint();
        });

        this.setBounds(new Rectangle(1400, 1000));
        this.setResizable(false);
        this.setTitle("Testing Swing for Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


}
