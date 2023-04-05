package code.view;

import code.controller.Controller;
import code.view.game.GamePanel;
import code.view.instructions.InstructionsPanel;
import code.view.menu.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private final MenuPanel menuPanel;

    private final InstructionsPanel instructionsPanel;
    private final GamePanel gamePanel;
    public MainFrame(){

        menuPanel = new MenuPanel();
        instructionsPanel = new InstructionsPanel();
        gamePanel = new GamePanel();

        Controller.getInstance().setOnNewPage(s -> {
            switch (s) {
                case MENU         -> this.setContentPane(menuPanel);
                case INSTRUCTIONS -> this.setContentPane(instructionsPanel);
                case GAME         -> this.setContentPane(gamePanel);
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
