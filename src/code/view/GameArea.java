package code.view;

import code.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {
    private JLabel[][] screenPixels;
    public GameArea(GameController gc){
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(16,16));
        this.setPreferredSize(new Dimension(700,700));
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
