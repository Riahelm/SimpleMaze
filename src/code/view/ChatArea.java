package code.view;

import code.controller.GameChatController;
import code.view.listener.OnMessageSentListener;

import javax.swing.*;
import java.awt.*;

public class ChatArea extends JPanel {
    private final JLabel[] chatDisplay;

    public ChatArea(GameChatController gCC) {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setFixedSize(this, new Dimension(400,700));
        chatDisplay = new JLabel[7];

        for (int i = 0; i < chatDisplay.length; i++) {
            chatDisplay[i] = new JLabel();
            setFixedSize(chatDisplay[i], new Dimension(400,95));

            this.add(chatDisplay[i]);
            if(i == chatDisplay.length - 1){
                chatDisplay[i].setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4, true),
                                                                                                    "Player score"));
            }
        }

        gCC.receiveMessage(new OnMessageSentListener() {
            @Override
            public void receiveMessage(String message) {

                for (int i = 1; i < chatDisplay.length - 1; i++) {
                    chatDisplay[i - 1].setText(chatDisplay[i].getText());
                }
                chatDisplay[chatDisplay.length - 2].setText(message);
                revalidate();
                repaint();
            }

            @Override
            public void receiveScore(int score) {
                chatDisplay[chatDisplay.length - 1].setText(String.valueOf(score));
                revalidate();
                repaint();
            }

        });
    }

    private void setFixedSize(Component component, Dimension dimension){
        component.setMinimumSize(dimension);
        component.setMaximumSize(dimension);
        component.setPreferredSize(dimension);
    }
}
