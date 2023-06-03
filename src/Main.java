import code.model.gameLogic.GameLogic;
import code.view.Direction;
import code.view.MainFrame;
import code.view.terminal.TerminalView;
import code.viewModel.GameViewModel;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                StartGame();
            } catch (IOException err) {
                throw new RuntimeException(err);
            }
        });
    }
    private static void StartGame() throws IOException{
        GameLogic gameLogic = new GameLogic();
        MainFrame myMainFrame = new MainFrame();

        //Logic can be changed here, just comment the view that you don't want, note that the terminal view also requires the following method
        //TerminalView myView = new TerminalView();
        //askForTerminalInput();
    }

    private static void askForTerminalInput() {
        //This is very scuffed, needless to say it should be implemented properly if someone actually wanted to use this
        Scanner myScanner = new Scanner(System.in);
        GameViewModel gVM = GameViewModel.getInstance();
        while(true){
            String ans = myScanner.next();

            if(ans.equalsIgnoreCase("Quit")){
                return;
            }

            if(Arrays.stream(Direction.values()).anyMatch(e -> ans.equals(e.toString()))){
                gVM.getMyGameLogicInstructions().ifPresent(e -> e.computeTurn(Direction.valueOf(ans)));
                gVM.updateScreen();
            }else{
                System.out.println("You wrote the wrong thing");
            }
        }
    }
}
