package code.view;

import code.controller.GameChatController;
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
    private GameArea gameArea;
    private ChatArea chatArea;
    private GameController gc;
    public GamePanel(GameController gc, GameChatController gCC){
        this.gc = gc;
        this.setBackground(Color.BLACK);
        this.setLayout(new FlowLayout());
        setKeyBindings();

        gameArea = new GameArea(gc);
        gameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        chatArea = new ChatArea(gCC);
        chatArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Message Log"));

        this.add(gameArea);
        this.add(chatArea);

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
        String vkSpace = "SPACE";

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), vkUp);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), vkLeft);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), vkDown);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), vkRight);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), vkSpace);

        actionMap.put(vkUp, new KeyAction(vkUp));
        actionMap.put(vkLeft, new KeyAction(vkLeft));
        actionMap.put(vkDown, new KeyAction(vkDown));
        actionMap.put(vkRight, new KeyAction(vkRight));
        actionMap.put(vkSpace, new KeyAction(vkSpace));
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
            gc.onKeyPressed(Direction.valueOf(actionEvt.getActionCommand()));
        }
    }
}
