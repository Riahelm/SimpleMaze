package code.view.instructions;

import code.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class InstructionsPanel extends JPanel {

    JButton backButton;
    JLabel instructions;
    public InstructionsPanel(){
        this.setLayout(new BorderLayout());

        backButton = new JButton("GO BACK");
        backButton.addActionListener(e -> Controller.getInstance().onMenuPressed());

        instructions = new JLabel(
                    """
                        <html>
                            <head> <title> TUTORIAL </title> </head>
                            <body>
                                <h1>
                                    Hello traveller!<br>
                                    Welcome to the tutorial for this game! <br>
                                    In short, you have three things to watch out for!
                                </h1>
                                
                                <h2>
                                    Movement
                                </h2>
                                <p>
                                    You move by using the directional arrow keys and you can also use space to avoid moving. <br>
                                    When moving, you always have the priority of movement, then followed by the enemies. This means you may be eaten by someone you don't expect!
                                </p>
                                <h2>
                                Types of tiles
                                </h2>
                                <p>
                                    You will be able to see four different types of tiles: <br>
                                    First, a black tile. You can't walk on these, and neither can anyone else. <br>
                                    Second, a white tile. You can walk on these, and so can everyone else. <br>
                                    Third, a brown tile. These are stairs, you can walk into these to go to the next level! <br>
                                    Fourth, a green tile. Walk into this tile to get out of the maze and win the game!
                                </p>
                                <h2>
                                    Types of entities
                                </h2>
                                <p>
                                    As of now, there are only two entities: <br>
                                    First, a blue entity. This is you, and they move as soon as you give a command. <br>
                                    Beware, for you can have multiple blue entities at the same time! As soon as one is defeated, the game is over! <br>
                                    Second, a red entity. This is your sworn enemy, and they will move randomly, if they touch you, you're finished!
                                </p>
                            </body>
                        </html>
                        """);

        this.add(backButton, BorderLayout.PAGE_START);
        this.add(instructions, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }
}
