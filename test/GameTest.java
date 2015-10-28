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
    public void setUp() throws Exception {
        clUI = new FakeCommandLineUI();
        game = new Game(clUI);
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
    public void requestToContinueOrQuitGameNotApplicableWhenGameOver() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        clUI.addDummyInputs(initialState);
        game.play();
        assertEquals(false, clUI.hasRequestedToQuit());
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

//    @Test
//    public void dontDisplayResultInMiddleOfGame() {
//
//        List<Integer> initialState = new ArrayList<>(Arrays.asList(
//                1, 2, 3,
//                4, 5, 6,
//                7, 0, 0));
//        clUI.addDummyInputs(initialState);
//        game.play();
//        assertEquals(false, clUI.hasRequestedToQuit());
//        assertEquals(false, clUI.hasDisplayedResultToUser());
//    }
}
