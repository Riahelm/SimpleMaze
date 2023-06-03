package code.view.menu;

import code.viewModel.MainFrameViewModel;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(1,3));
        JButton mStartBtn = new JButton("Start");
        JButton mInstructionsBtn = new JButton("Instructions");
        JButton mQuitBtn = new JButton("Quit");

        mStartBtn.addActionListener(e -> MainFrameViewModel.getInstance().onStartPressed());
        mInstructionsBtn.addActionListener(e -> MainFrameViewModel.getInstance().onInstructionsPressed());
        mQuitBtn.addActionListener(e -> MainFrameViewModel.getInstance().onQuitPressed());

        add(mStartBtn);
        add(mInstructionsBtn);
        add(mQuitBtn);

    }
}
