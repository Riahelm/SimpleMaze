package code.view.terminal;

import code.util.OperateOnMatrix;
import code.util.Pair;
import code.view.GameOverState;
import code.viewModel.GameViewModel;
import code.viewModel.ViewType;
import code.viewModel.observers.ChatAreaObserver;
import code.viewModel.observers.GameAreaObserver;
import code.viewModel.observers.GamePanelObserver;

import java.util.Scanner;

public class TerminalView{
    public TerminalView(){

        GameViewModel gVM = GameViewModel.getInstance();
        gVM.setGameAreaObserver(new GameAreaObserver() {
            @Override
            public <X> void useUpdatedState(X[][] updatedState) {

                //Does nothing if given type is incompatible
                if(!(updatedState instanceof Integer[][] integerState)) return;

                OperateOnMatrix.operateOnEachElement(integerState, (i, j) -> {
                    if(j == integerState.length-1){
                        System.out.println(integerState[j][integerState.length -1 - i]);
                    }else{
                        System.out.print(integerState[j][integerState.length -1 - i] + "\t");
                    }
                });
            }
        });
        gVM.setGamePanelObserver(new GamePanelObserver() {
            @Override
            public void setToGameOver(GameOverState state) {
                System.out.println("Game over! You " + state + "\n" + "With a score of " + gVM.getPlayerInfo().second().getValue());
                System.exit(0);
            }

            @Override
            public void askAQuestion(Pair<String, Boolean> question) {
                Scanner myScanner = new Scanner(System.in);
                System.out.println(question.first());
                String ans = myScanner.next();
                if(ans.equalsIgnoreCase("Yes") || ans.equalsIgnoreCase("No")){
                    if(ans.equalsIgnoreCase(question.second().toString())){
                        System.out.println("You are correct!");
                    }else
                        setToGameOver(GameOverState.LOSE);
                }else{
                    System.out.println("You wrote the wrong thing, the only accepted values are 'Yes' and 'no'");
                    askAQuestion(question);
                }
            }
        });

        GameViewModel.ChatViewModel.getInstance().setChatAreaInstructions(new ChatAreaObserver() {
            @Override
            public void receiveMessage(String message) {
                System.out.println("CHAT: " + message);
            }

            @Override
            public void receiveScore(int score) {
                System.out.println("YOUR SCORE: " + score);
            }

            @Override
            public void resetChat() {
                //Don't need to do anything here
            }
        });

        gVM.setViewType(ViewType.TERMINAL);
    }
}
