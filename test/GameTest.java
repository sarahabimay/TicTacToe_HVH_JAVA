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
    public void choose3x3Game() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        game.play();
        assertEquals(true, fakeUI.hasAskedUserForDimension());
    }

    @Test
    public void choose4x4Game() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16));
        fakeUI = generateFakeUI(initialState, 4, 1);
        game.play();
        assertEquals(true, fakeUI.hasAskedUserForDimension());
    }

    @Test
    public void checkUserHasBeenAskedForTypeOfGame() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        Game newGame = new Game(fakeUI);
        newGame.play();
        assertEquals(true, fakeUI.hasAskedUserForGameType());
    }

    @Test
    public void checkHumanHasBeenAskedForNextMove() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        game.play();
        assertEquals(true, fakeUI.hasAskedUserForNextPosition());
    }

    @Test
    public void checkValidateGameTypeHasBeenCalled() {
        fakeUI = generateFakeUI(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9), 3, 1);
        game.play();
        assertEquals(true, fakeUI.hasGameTypeBeenValidated());
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

    @Test
    public void displayBoardAfterEachMove() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        game.play();
        assertEquals(true, fakeUI.hasDisplayedBoardToUser());
    }

    @Test
    public void displayResultAtEndOfGame() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        game.play();
        assertEquals(true, fakeUI.hasDisplayedResultToUser());
    }

    @Test
    public void requestToQuitAfterGameOver() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        fakeUI.addDummyPlayAgainChoice(1);
        game.play();
        assertEquals(true, fakeUI.hasAskedUserToQuitGame());
    }

    @Test
    public void checkGameIsOver() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        game.play();
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void checkResultIsADraw() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        game.play();
        assertEquals(true, game.isGameOver());
        assertEquals(true, fakeUI.isADraw());
    }

    @Test
    public void checkResultIsAWin() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        game.play();
        assertEquals(true, game.isGameOver());
        assertEquals(false, fakeUI.isADraw());
        assertEquals(Counter.X, fakeUI.getWinner());
    }

    @Test
    public void humanVsComputer() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 2);
        game.play();
        assertEquals(HumanPlayer.class, game.getPlayer(Counter.X).getClass());
        assertEquals(ComputerPlayer.class, game.getPlayer(Counter.O).getClass());
    }

    @Test
    public void computerVsHuman() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 3);
        game.play();
        assertEquals(ComputerPlayer.class, game.getPlayer(Counter.X).getClass());
        assertEquals(HumanPlayer.class, game.getPlayer(Counter.O).getClass());
    }

    @Test
    public void duplicateMoveRepeatsMoveRequestTillValid() {
        fakeUI = generateFakeUI(Arrays.asList(
                1, 2, 2,
                4, 7, 3,
                8, 9, 5), 3, 1);
        game.play();
        assertEquals("" +
                        "[X][O][X]\n" +
                        "[X][O][6]\n" +
                        "[O][O][X]\n",
                game.displayBoard());
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
