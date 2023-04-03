package code.view;

import code.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {
    private JLabel[][] screenPixels;

    public GameArea(GameController gc){
        this.setBackground(Color.BLACK);
        setFixedSize(this, new Dimension(700,700));

        init(gc, gc.getMapSize());

        gc.onMapChanged(size -> init(gc, size));

        this.revalidate();
        this.repaint();
    }

    private void init(GameController gc, int mapSize){

        removeAll();

        this.setLayout(new GridLayout(mapSize,mapSize));

        screenPixels = new JLabel[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                screenPixels[i][j] = new JLabel();
                this.add(screenPixels[i][j]);
            }
        }

        gc.refresh(e -> {
            for (int i = 0; i < e.length; i++) {
                for (int j = 0; j < e.length; j++) {
                    screenPixels[i][j].setIcon(e[i][j]);
                    this.revalidate();
                    this.repaint();
                }
            }
        });
    }
    private void setFixedSize(Component component, Dimension dimension){
        component.setMaximumSize(dimension);
        component.setMinimumSize(dimension);
        component.setPreferredSize(dimension);
    }
}
