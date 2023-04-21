package code.view.game;

import code.controller.GameController;
import code.util.OperateOnMatrix;
import code.view.Direction;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {
    private JLabel[][] screenPixels;

    public GameArea(GameController gc){
        this.setBackground(Color.BLACK);
        setFixedSize(this, new Dimension(700,700));

        gc.setGameAreaListener(updatedState -> {
            if(screenPixels == null || updatedState.length != screenPixels.length){
                int mapSize = updatedState.length;
                removeAll();

                setLayout(new GridLayout(mapSize,mapSize));


                screenPixels = new JLabel[mapSize][mapSize];

                OperateOnMatrix.operateOnEachElement(screenPixels, (i, j) -> {
                    screenPixels[i][j] = new JLabel();
                    add(screenPixels[i][j]);
                });
            }
            OperateOnMatrix.operateOnEachElement(updatedState, (i, j) -> {
                screenPixels[i][j].setIcon(updatedState[i][j]);
                revalidate();
                repaint();
            });
        });

        gc.forceRefresh();

        this.revalidate();
        this.repaint();
    }
    private void setFixedSize(Component component, Dimension dimension){
        component.setMaximumSize(dimension);
        component.setMinimumSize(dimension);
        component.setPreferredSize(dimension);
    }
}
