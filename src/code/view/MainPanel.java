package code.view;

import code.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JButton mStartBtn;
    //private JButton mSettingsBtn;
    private JButton mQuitBtn;

    public MainPanel(Controller c) {
        mStartBtn = new JButton("Start");
        //mSettingsBtn = new JButton("Settings");
        mQuitBtn = new JButton("Quit");
        this.setLayout(new GridLayout(1, 2));

        mStartBtn.addActionListener(e -> c.onStartPressed());
        //mSettingsBtn.addActionListener(e -> c.onSettingsPressed());
        mQuitBtn.addActionListener(e -> c.onQuitPressed());

        add(mStartBtn);
        //add(mSettingsBtn);
        add(mQuitBtn);
    }
}
