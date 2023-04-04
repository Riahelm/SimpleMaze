package code.view;

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

        //not using o because it requires a bunch of reordering that's easily skipped
        OperateOnMatrix.operateOnEachElement(screenPixels, (o, i, j) -> {
            screenPixels[i][j] = new JLabel();
            add(screenPixels[i][j]);
        });

        gc.refresh(e -> OperateOnMatrix.operateOnEachElement(e, (element, i, j) -> {
            screenPixels[i][j].setIcon((Icon)element);
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
