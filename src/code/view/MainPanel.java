package code.view;

import code.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JButton mStartBtn;
    //private JButton mSettingsBtn;
    private JButton mQuitBtn;

    public MainPanel() {
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(1,2));
        mStartBtn = new JButton("Start");
        //mSettingsBtn = new JButton("Settings");
        mQuitBtn = new JButton("Quit");

        mStartBtn.addActionListener(e -> Controller.getInstance().onStartPressed());
        //mSettingsBtn.addActionListener(e -> c.onSettingsPressed());
        mQuitBtn.addActionListener(e -> Controller.getInstance().onQuitPressed());

        add(mStartBtn);
        //add(mSettingsBtn);
        add(mQuitBtn);

    }
}
