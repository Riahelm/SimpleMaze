package code.viewModel;

import code.viewModel.observers.ChatAreaObserver;
import code.viewModel.observers.GameAreaObserver;
import code.viewModel.observers.GameLogicObserver;
import code.viewModel.observers.GamePanelObserver;
import code.model.actor.impl.NPCQuestions;
import code.util.Pair;
import code.util.api.Counter;
import code.view.Direction;
import code.view.GameOverState;

import java.util.Optional;

public class GameViewModel {

    private static GameViewModel instance;

    private ViewType type;
    private GameAreaObserver myGameAreaInstructions;
    private GameLogicObserver myGameLogicInstructions;
    private GamePanelObserver myGamePanelInstructions;

    private GameViewModel(){}

    //Regular singleton application here
    public static GameViewModel getInstance(){
        if(instance == null){
            instance = new GameViewModel();
        }
        return instance;
    }

    public void setGameAreaObserver(GameAreaObserver l){
        myGameAreaInstructions = l;
    }
    public void setGameLogicObserver(GameLogicObserver l){
        myGameLogicInstructions = l;
    }
    public void setGamePanelObserver(GamePanelObserver l){
        myGamePanelInstructions = l;
    }
    public void setViewType(ViewType type){
        this.type = type;
    }

    public Optional<GameAreaObserver> getMyGameAreaInstructions() {
        return Optional.ofNullable(myGameAreaInstructions);
    }

    public Optional<GameLogicObserver> getMyGameLogicInstructions() {
        return Optional.ofNullable(myGameLogicInstructions);
    }

    public Optional<GamePanelObserver> getMyGamePanelInstructions() {
        return Optional.ofNullable(myGamePanelInstructions);
    }

    public ViewType getViewType(){
        return this.type;
    }

    public void computeTurn(Direction key){
        //Won't compute the turn if you're going to the next level
        if(key.equals(Direction.SKIP)){
            this.goToNextWorld();
        }else{
            getMyGameLogicInstructions().ifPresent(e -> e.computeTurn(key));
        }
        this.updateScreen();
    }
    public void updateScreen(){

        if(getMyGameAreaInstructions().isEmpty() || getMyGameLogicInstructions().isEmpty()) return;

        if(type.equals(ViewType.SWING)){
            myGameAreaInstructions.useUpdatedState(myGameLogicInstructions.getGameState());
        }else{
            myGameAreaInstructions.useUpdatedState(myGameLogicInstructions.getTerminalGameState());
        }

    }
    public void increaseScore(){
        getPlayerInfo().second().increment();
        //BIG expression ahead, all it does is update the chat to the new value
        getMyGameLogicInstructions().ifPresent(e -> ChatViewModel.getInstance().updateScore(getMyGameLogicInstructions().get().getPlayerInfo().second().getValue()));
    }
    public void goToNextWorld(){
        getMyGameLogicInstructions().ifPresent(GameLogicObserver::switchToNextWorld);
    }
    public Pair<? extends Counter,? extends Counter> getPlayerInfo(){
        if(getMyGameLogicInstructions().isPresent()){
            return getMyGameLogicInstructions().get().getPlayerInfo();
        }
        throw new RuntimeException("Model is missing");
    }
    public void askAQuestion(Pair<String, Boolean> question){
        getMyGamePanelInstructions().ifPresent(e -> e.askAQuestion(question));
    }
    public void finishGame(GameOverState state){
        getMyGamePanelInstructions().ifPresent(e -> e.setToGameOver(state));
    }
    public void restartGame() {
        NPCQuestions.resetQuestions();
        if(getMyGameLogicInstructions().isPresent()){
            getMyGameLogicInstructions().get().resetPlayerStatus();
            ChatViewModel.getInstance().updateScore(getMyGameLogicInstructions().get().getPlayerInfo().second().getValue());
        }
        ChatViewModel.getInstance().resetChat();
    }


    public static class ChatViewModel {
        private static ChatViewModel instance;
        private ChatAreaObserver chatAreaInstructions;

        public static ChatViewModel getInstance(){
            if(instance == null){
                instance = new ChatViewModel();
            }
            return instance;
        }

        public void setChatAreaInstructions(ChatAreaObserver l){
            chatAreaInstructions = l;
        }

        public Optional<ChatAreaObserver> getChatAreaInstructions() {
            return Optional.ofNullable(chatAreaInstructions);
        }

        public void sendMessage(String message){
            getChatAreaInstructions().ifPresent(e -> e.receiveMessage(message));
        }
        public void updateScore(int score){
            getChatAreaInstructions().ifPresent(e -> e.receiveScore(score));
        }

        public void resetChat(){ getChatAreaInstructions().ifPresent(ChatAreaObserver::resetChat);}
    }
}
