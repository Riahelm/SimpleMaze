package code.view;

import code.controller.GameChatController;
import code.controller.GameController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel{

    private final GameArea gameArea;
    private final ChatArea chatArea;
    private final GameController gc;
    public GamePanel(){
        this.gc = GameController.getInstance();
        this.setBackground(Color.BLACK);
        this.setLayout(new FlowLayout());

        gameArea = new GameArea(gc);
        gameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        chatArea = new ChatArea(GameChatController.getInstance());
        chatArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Message Log"));

        this.add(gameArea);
        this.add(chatArea);

        init();
        setKeyBindings();

    }

    private void init(){
        gc.onGameOver((state, score) -> {

            remove(gameArea);
            remove(chatArea);
            setBackground(Color.WHITE);

            //This creates and puts the label in the middle
            setLayout(new BorderLayout());
            setBorder(new EmptyBorder(0, getWidth()/2,0,0));
            //Yes, JLabels need html to allow for easy multiline input.
            add(new JLabel("<html>YOU " + state +"<br> PLAYER SCORE: " + score + "</html>"), BorderLayout.CENTER);

            revalidate();
            repaint();
        });
    }

    private void setKeyBindings(){
        ActionMap actionMap = getActionMap();
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        //Mind you, these are rotated by 90 degrees to compensate for the generation of the map
        //This is the quickest fix without having to change the model
        String vkUp = "LEFT";
        String vkLeft = "DOWN";
        String vkDown = "RIGHT";
        String vkRight = "UP";
        String vkSpace = "SPACE";

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), vkUp);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), vkLeft);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), vkDown);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), vkRight);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), vkSpace);

        actionMap.put(vkUp, new KeyAction(vkUp));
        actionMap.put(vkLeft, new KeyAction(vkLeft));
        actionMap.put(vkDown, new KeyAction(vkDown));
        actionMap.put(vkRight, new KeyAction(vkRight));
        actionMap.put(vkSpace, new KeyAction(vkSpace));
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1366, 768);
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
