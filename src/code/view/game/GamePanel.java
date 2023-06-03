package code.view.game;

import code.viewModel.GameViewModel;
import code.viewModel.ViewType;
import code.viewModel.observers.GamePanelObserver;
import code.util.Pair;
import code.view.Direction;
import code.view.GameOverState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class GamePanel extends JPanel{

    private final GameArea gameArea;
    private final ChatArea chatArea;
    private final GameViewModel gVM;
    public GamePanel(){
        this.gVM = GameViewModel.getInstance();
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        this.setLayout(new FlowLayout());
        gVM.setViewType(ViewType.SWING);
        gameArea = new GameArea(gVM);
        gameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        chatArea = new ChatArea(GameViewModel.ChatViewModel.getInstance());
        chatArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Message Log"));
        this.add(gameArea);
        this.add(chatArea);

        init();
        setKeyBindings();

    }

    private void init(){

        gVM.setGamePanelObserver(new GamePanelObserver() {
            @Override
            public void setToGameOver(GameOverState state) {
                    JPanel gameOverPanel = new JPanel();
                    JButton resetButton = new JButton("RESET");
                    undoKeyBindings();
                    remove(gameArea);
                    remove(chatArea);
                    setBackground(Color.WHITE);

                    gameOverPanel.setMinimumSize(new Dimension(1366, 768));
                    gameOverPanel.setLayout(new BorderLayout());
                    gameOverPanel.add(resetButton, BorderLayout.NORTH);
                    gameOverPanel.add(new JLabel("<html>YOU " + state +"<br> PLAYER SCORE: " + gVM.getPlayerInfo().second().getValue() + "</html>"), BorderLayout.CENTER);
                    add(gameOverPanel);

                    resetButton.addActionListener(e -> {
                        gVM.restartGame();
                        setBackground(Color.BLACK);
                        remove(gameOverPanel);
                        add(gameArea);
                        add(chatArea);
                        setKeyBindings();
                        gVM.updateScreen();
                        revalidate();
                        repaint();
                    });

                    revalidate();
                    repaint();
            }

            @Override
            public void askAQuestion(Pair<String, Boolean> question){
                boolean answer = JOptionPane.showConfirmDialog(gameArea, question.first(), "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0;
                if(answer == question.second()) {
                    GameViewModel.ChatViewModel.getInstance().sendMessage("You chose the correct answer!");
                }else {
                       gVM.finishGame(GameOverState.LOSE);
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
        String vkSkip = "SKIP";

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), vkUp);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), vkLeft);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), vkDown);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), vkRight);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), vkSpace);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0 ), vkSkip);

        actionMap.put(vkUp, new KeyAction(vkUp));
        actionMap.put(vkLeft, new KeyAction(vkLeft));
        actionMap.put(vkDown, new KeyAction(vkDown));
        actionMap.put(vkRight, new KeyAction(vkRight));
        actionMap.put(vkSpace, new KeyAction(vkSpace));
        actionMap.put(vkSkip, new KeyAction(vkSkip));
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
            gVM.computeTurn(Direction.valueOf(actionEvt.getActionCommand()));
        }
    }
}
