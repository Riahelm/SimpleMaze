package code.view;

import code.viewModel.MainFrameViewModel;
import code.view.game.GamePanel;
import code.view.instructions.InstructionsPanel;
import code.view.menu.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private MenuPanel menuPanel;
    private InstructionsPanel instructionsPanel;
    private GamePanel gamePanel;
    public MainFrame(){

        MainFrameViewModel.getInstance().setMainFrameObserver(s -> {
            switch (s) {
                case MENU         -> {
                    menuPanel = new MenuPanel();
                    this.setContentPane(menuPanel);
                }
                case INSTRUCTIONS -> {
                    instructionsPanel = new InstructionsPanel();
                    this.setContentPane(instructionsPanel);
                }
                case GAME         -> {
                    gamePanel = new GamePanel();
                    this.setContentPane(gamePanel);
                }
            }
            this.revalidate();
            this.repaint();
        });

        this.getContentPane().setBackground(Color.BLACK);
        this.setBounds(new Rectangle(1366,768));
        this.setTitle("Simple Maze");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


}
