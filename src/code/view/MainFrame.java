package code.view;

import code.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private final MainPanel mainPanel;
    private final GamePanel gamePanel;
    public MainFrame(){

        mainPanel = new MainPanel();
        gamePanel = new GamePanel();

        Controller.getInstance().setOnNewPage(s -> {
            switch (s) {
                case MENU     -> this.setContentPane(mainPanel);
                case GAME     -> this.setContentPane(gamePanel);
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
