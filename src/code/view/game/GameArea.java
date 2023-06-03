package code.view.game;

import code.viewModel.GameViewModel;
import code.util.OperateOnMatrix;
import code.viewModel.observers.GameAreaObserver;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {
    private JLabel[][] screenPixels;

    public GameArea(GameViewModel gc){
        this.setBackground(Color.BLACK);
        setFixedSize(this, new Dimension(700,700));

        gc.setGameAreaObserver(new GameAreaObserver() {
            @Override
            public <X> void useUpdatedState(X[][] updatedState) {

                //Skips if the given state is incompatible
                if(!(updatedState instanceof Icon[][] iconState)) return;

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
                    screenPixels[i][j].setIcon(iconState[j][updatedState.length - 1 -i]);
                    revalidate();
                    repaint();
                });

            }
        });

        gc.updateScreen();

        this.revalidate();
        this.repaint();
    }
    private void setFixedSize(Component component, Dimension dimension){
        component.setMaximumSize(dimension);
        component.setMinimumSize(dimension);
        component.setPreferredSize(dimension);
    }
}
