package jttt.Core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private FakeCommandLineUI fakeUI;
    private Game game;

    @Before
    public void setUp() {
        fakeUI = new FakeCommandLineUI();
        game = new Game(fakeUI);
    }


    @Test
    public void playNextMoveAndSeeResult() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0));
        fakeUI = generateFakeUI(initialState, 3, 1);
        game.requestBoardSize();
        game.selectPlayers(game.requestGameType());
        Board board = game.nextPlayerMakesMove(Counter.X);
        assertEquals("" +
                        "[X][2][3]\n" +
                        "[4][5][6]\n" +
                        "[7][8][9]\n",
                fakeUI.displayBoard(board));
    }

    @Test
    public void checkPlayerCounterGetsSwitched() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0));
        fakeUI = generateFakeUI(initialState, 3, 1);
        game.requestBoardSize();
        game.selectPlayers(1);
        game.nextPlayerMakesMove(Counter.X);
        assertEquals(Counter.O, game.getNextCounter(Counter.X));
    }





//    @Test
//    public void computerShouldBlockHuman() {
//        fakeUI = generateFakeUI(Arrays.asList(
//                1, 5, 9
//                ), 3, 2);
//        game.play();
//        assertEquals("" +
//                        "[X][O][X]\n" +
//                        "[X][O][6]\n" +
//                        "[O][O][X]\n",
//                game.displayBoard());
//    }

    private FakeCommandLineUI generateFakeUI(List<Integer> moves, int dimension, Integer gameType) {
        List<Integer> initialState = new ArrayList<>(moves);
        fakeUI.addDummyDimension(dimension);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(gameType);
        return fakeUI;
    }
}
