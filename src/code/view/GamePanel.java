package code.view;

import code.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel{

    //TODO, GamePanel ha GameController,
    // GameController aspetta un evento da GameLogic,
    // quando riceve un evento allora fa il set del nuovo stato
    // su GameLogic e poi riceve lo stato nuovo e lo da al code.view
    GameController gc;
    private JLabel[][] screenPixels;
    public GamePanel(GameController gc){
        this.gc = gc;
        setKeyBindings();
        screenPixels = new JLabel[16][16];
        this.setLayout(new GridLayout(16, 16));


        // Initializes the screen
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                screenPixels[i][j] = new JLabel();
                this.add(screenPixels[i][j]);
                screenPixels[i][j].setBounds(new Rectangle(16, 16));
            }
        }
        // Tells the screen how to refresh
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

    private void setKeyBindings(){
        ActionMap actionMap = getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition );

        //Mind you, these are rotated by 90 degrees to compensate for the generation of the map
        //This is the quickest fix without having to change the model
        String vkUp = "LEFT";
        String vkLeft = "DOWN";
        String vkDown = "RIGHT";
        String vkRight = "UP";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), vkUp);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), vkLeft);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), vkDown);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), vkRight);

        actionMap.put(vkUp, new KeyAction(vkUp));
        actionMap.put(vkLeft, new KeyAction(vkLeft));
        actionMap.put(vkDown, new KeyAction(vkDown));
        actionMap.put(vkRight, new KeyAction(vkRight));
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 200);
    }

    private class KeyAction extends AbstractAction {
        public KeyAction(String actionCommand) {
            putValue(ACTION_COMMAND_KEY, actionCommand);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvt) {
            gc.onKeyPressed(Directions.valueOf(actionEvt.getActionCommand()));
        }
    }
}
