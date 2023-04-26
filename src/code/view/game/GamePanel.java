package code.view.game;

import code.controller.GameChatController;
import code.controller.GameController;
import code.controller.listeners.GamePanelListener;
import code.util.Pair;
import code.view.Direction;
import code.view.GameOverState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class GamePanel extends JLayeredPane{

    private final GameArea gameArea;
    private final ChatArea chatArea;
    private final GameController gc;
    public GamePanel(){
        this.gc = GameController.getInstance();
        this.setOpaque(true);
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

        gc.setGameOverListener(new GamePanelListener() {
            @Override
            public void setToGameOver(GameOverState state, int score) {
                    JPanel gameOverPanel = new JPanel();
                    JButton resetButton = new JButton("RESET");
                    undoKeyBindings();
                    remove(gameArea);
                    remove(chatArea);
                    setBackground(Color.WHITE);

                    gameOverPanel.setMinimumSize(new Dimension(1366, 768));
                    gameOverPanel.setLayout(new BorderLayout());
                    gameOverPanel.add(resetButton, BorderLayout.NORTH);
                    gameOverPanel.add(new JLabel("<html>YOU " + state +"<br> PLAYER SCORE: " + score + "</html>"), BorderLayout.CENTER);
                    add(gameOverPanel);

                    resetButton.addActionListener(e -> {
                        gc.restartGame();
                        setBackground(Color.BLACK);
                        remove(gameOverPanel);
                        add(gameArea);
                        add(chatArea);
                        setKeyBindings();
                        gc.forceRefresh();
                        revalidate();
                        repaint();
                    });

                    revalidate();
                    repaint();
            }

            @Override
            public void askAQuestion(Pair<String, Boolean> question){
                boolean answer = JOptionPane.showConfirmDialog(gameArea, question.x(), "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0;
                if(answer == question.y()) {
                    GameChatController.getInstance().sendMessage("You chose the correct answer!");
                }else {
                       gc.finishGame(GameOverState.LOSE, -1000);
                }
            }
        });
    }

    private void undoKeyBindings() {
        ActionMap actionMap = getActionMap();
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        Arrays.stream(inputMap.allKeys()).forEach(inputMap::remove);
        Arrays.stream(actionMap.allKeys()).forEach(actionMap::remove);
    }

    private void setKeyBindings(){
        ActionMap actionMap = getActionMap();
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        String vkUp = "UP";
        String vkLeft = "LEFT";
        String vkDown = "DOWN";
        String vkRight = "RIGHT";
        String vkSpace = "SPACE";

        //Mind you, these are rotated by 90 degrees to compensate for the generation of the map
        //This is the quickest fix without having to change the model
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), vkLeft);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), vkDown);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), vkRight);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), vkUp);
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
            gc.computeTurn(Direction.valueOf(actionEvt.getActionCommand()));
        }
    }
}
