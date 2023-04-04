package code.view.menu;

import code.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(1,3));
        JButton mStartBtn = new JButton("Start");
        JButton mInstructionsBtn = new JButton("Instructions");
        JButton mQuitBtn = new JButton("Quit");

        mStartBtn.addActionListener(e -> Controller.getInstance().onStartPressed());
        mInstructionsBtn.addActionListener(e -> Controller.getInstance().onInstructionsPressed());
        mQuitBtn.addActionListener(e -> Controller.getInstance().onQuitPressed());

        add(mStartBtn);
        add(mInstructionsBtn);
        add(mQuitBtn);

    }
}
