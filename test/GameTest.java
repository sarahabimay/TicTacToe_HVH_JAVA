import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {

    public FakeCommandLineUI clUI;
    public Game game;

    @Before
    public void setUp() {
        clUI = new FakeCommandLineUI();
        game = new Game(clUI, new Player(Counter.X, clUI), new Player(Counter.O, clUI));
    }


    @Test
    public void choose3x3Game() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        clUI.addDummyInputs(initialState);
        game.play();
        assertEquals(true, clUI.hasAskedUserForDimension());
    }

    @Test
    public void choose4x4Game() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16));
        clUI.addDummyInputs(initialState);
        game.play();
        assertEquals(true, clUI.hasAskedUserForDimension());
    }

    @Test
    public void getNextPlayerMove() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        clUI.addDummyInputs(initialState);
        game.play();
        assertEquals(true, clUI.hasAskedUserForNextPosition());
    }

    @Test
    public void playNextMoveAndSeeResult() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0));
        clUI.addDummyInputs(initialState);
        game.requestBoardSize();
        Board board = game.nextPlayerMakesMove(Counter.X);
        assertEquals("" +
                        "[X][2][3]\n" +
                        "[4][5][6]\n" +
                        "[7][8][9]\n",
                clUI.displayBoard(board));
    }

    @Test
    public void checkPlayerCounterGetsSwitched() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0));
        clUI.addDummyInputs(initialState);
        game.requestBoardSize();
        game.nextPlayerMakesMove(Counter.X);
        assertEquals(Counter.O, game.getNextCounter(Counter.X));
    }

    @Test
    public void displayBoardAfterEachMove() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        clUI.addDummyInputs(initialState);
        game.play();
        assertEquals(true, clUI.hasDisplayedBoardToUser());
    }

    @Test
    public void displayResultAtEndOfGame() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        clUI.addDummyInputs(initialState);
        game.play();
        assertEquals(true, clUI.hasDisplayedResultToUser());
    }

    @Test
    public void requestToQuitAfterGameOver() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        clUI.addDummyInputs(initialState);
        clUI.addDummyPlayAgainChoice(1);
        game.play();
        assertEquals(true, clUI.hasAskedUserToQuitGame());
    }

    @Test
    public void checkGameIsOver() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        clUI.addDummyInputs(initialState);
        game.play();
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void checkResultIsADraw() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        clUI.addDummyInputs(initialState);
        game.play();
        assertEquals(true, game.isGameOver());
        assertEquals(true, clUI.isADraw());
    }

    @Test
    public void checkResultIsAWin() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        clUI.addDummyInputs(initialState);
        game.play();
        assertEquals(true, game.isGameOver());
        assertEquals(false, clUI.isADraw());
        assertEquals(Counter.X, clUI.getWinner());
    }
}
