package code.view;

import code.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {

    private GameController gc;
    private JLabel[][] screenPixels;
    public GameArea(GameController gc){
        this.gc = gc;
        this.setLayout(new GridLayout(16,16));
        this.setPreferredSize(new Dimension(1000,1000));
        this.setBounds(new Rectangle(0,0,1000,1000));
        screenPixels = new JLabel[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                screenPixels[i][j] = new JLabel();
                this.add(screenPixels[i][j]);
            }
        }

        gc.refresh(e -> {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    screenPixels[i][j].setIcon(e[i][j]);
                    this.revalidate();
                    this.repaint();
                }
            }
        });
        this.revalidate();
        this.repaint();
    }
}
