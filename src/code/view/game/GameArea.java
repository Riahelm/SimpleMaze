package code.view.game;

import code.controller.GameController;
import code.util.OperateOnMatrix;

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

        OperateOnMatrix.operateOnEachElement(screenPixels, (i, j) -> {
            screenPixels[i][j] = new JLabel();
            add(screenPixels[i][j]);
        });

        gc.refresh(updatedState -> OperateOnMatrix.operateOnEachElement(updatedState, (i, j) -> {
            screenPixels[i][j].setIcon(updatedState[i][j]);
            revalidate();
            repaint();
        }));
    }
    private void setFixedSize(Component component, Dimension dimension){
        component.setMaximumSize(dimension);
        component.setMinimumSize(dimension);
        component.setPreferredSize(dimension);
    }
}
