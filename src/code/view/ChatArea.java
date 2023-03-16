package code.view;

import code.controller.GameChatController;
import code.view.listener.OnMessageSentListener;

import javax.swing.*;
import java.awt.*;

public class ChatArea extends JPanel {

    private JLabel[] chatLog;
    private GameChatController gameCController;

    public ChatArea(GameChatController gCC){
        this.gameCController = gCC;
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(200,1000));
        GridBagConstraints c = new GridBagConstraints();
        chatLog = new JLabel[10];

        for (int i = 0; i < chatLog.length; i++) {
            chatLog[i] = new JLabel("Text!");
            chatLog[i].setSize(new Dimension(200, 100));
            c.gridy = i;
            this.add(chatLog[i], c);
        }

        gameCController.receiveMessage(message -> {
            System.out.println(message);
            for (int i = 1; i < chatLog.length; i++) {
                chatLog[i - 1].setText(chatLog[i].getText());
            }
            chatLog[chatLog.length - 1].setText(message);
            this.revalidate();
            this.repaint();
        });

        this.revalidate();
        this.repaint();
    }

}
