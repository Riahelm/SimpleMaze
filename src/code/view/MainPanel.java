package code.view;

import code.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel() {
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(1,2));
        JButton mStartBtn = new JButton("Start");
        JButton mQuitBtn = new JButton("Quit");

        mStartBtn.addActionListener(e -> Controller.getInstance().onStartPressed());
        mQuitBtn.addActionListener(e -> Controller.getInstance().onQuitPressed());

        add(mStartBtn);
        add(mQuitBtn);

    }
}
