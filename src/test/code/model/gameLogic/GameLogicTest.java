package code.model.gameLogic;

import code.viewModel.GameViewModel;
import code.viewModel.observers.GameLogicObserver;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class GameLogicTest {

    GameLogic gL;
    GameViewModel gVM;

    GameLogicObserver gLO;
    @Before
    public void setUp(){
        gL = new GameLogic();  //Or whatever logic you want to put here
        gVM = GameViewModel.getInstance();
        try{
            //It must be specified that no class should have a reference to the Observer itself
            //Here it is done in order to test all the functionalities

            gLO = gVM.getMyGameLogicInstructions().get();
        }catch (NoSuchElementException e){
            fail("GameLogic wasn't implemented properly! It MUST implement the model instructions");
        }
    }

    @Test
    public void getLevelCounter() {
        assertNotNull(gVM.getPlayerInfo().first());
        assertNotNull(gLO.getPlayerInfo().first());
        //Reset here
    }

    @Test
    public void getScoreCounter() {
        assertNotNull(gVM.getPlayerInfo().second());
        assertNotNull(gLO.getPlayerInfo().second());
    }

    @Test (expected = RuntimeException.class)
    public void switchToNextWorld() {
        int lvl;

        while(true){
            lvl = gLO.getPlayerInfo().first().getValue();
            gLO.switchToNextWorld();
            assertEquals(1, gLO.getPlayerInfo().first().getValue() - lvl);
            //Needs to throw a RuntimeException because there can't be infinite maps
        }


    }
}