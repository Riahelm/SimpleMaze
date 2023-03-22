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
    public MainFrame(){

        mainPanel = new MainPanel();
        gamePanel = new GamePanel();
        //settingsPanel = new SettingsPanel();

        Controller.getInstance().setOnNewPage(s -> {
            switch (s) {
                case MENU     -> this.setContentPane(mainPanel);
                case GAME     -> this.setContentPane(gamePanel);
                //case SETTINGS -> this.setContentPane(settingsPanel);
            }
            this.revalidate();
            this.repaint();
        });

        this.getContentPane().setBackground(Color.BLACK);
        this.setBounds(new Rectangle(1366,768));
        this.setTitle("The maze game!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


}
