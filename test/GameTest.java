import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {

    public FakeCommandLineUI fakeUI;
    public Game game;

    @Before
    public void setUp() {
        fakeUI = new FakeCommandLineUI();
        game = new Game(fakeUI,
                new HumanPlayer(Counter.X, Player.Type.Human, fakeUI),
                new HumanPlayer(Counter.O, Player.Type.Human, fakeUI));
    }


    @Test
    public void choose3x3Game() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVH");
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
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVH");
        game.play();
        assertEquals(true, fakeUI.hasAskedUserForDimension());
    }

    @Test
    public void chooseHVHTypeOfGame() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVH");
        Game newGame = new Game(fakeUI);
        newGame.play();
        assertEquals(true, fakeUI.hasAskedUserForGameType());
    }

    @Test
    public void chooseHVCTypeOfGame() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVC");
        Game newGame = new Game(fakeUI);
        newGame.play();
        assertEquals("HVC", newGame.typeOfGame());
    }

    @Test
    public void getNextPlayerMove() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVH");
        game.play();
        assertEquals(true, fakeUI.hasAskedUserForNextPosition());
    }

    @Test
    public void playNextMoveAndSeeResult() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0));
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVH");
        game.requestBoardSize();
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
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVH");
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
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVH");
        game.play();
        assertEquals(true, fakeUI.hasDisplayedBoardToUser());
    }

    @Test
    public void displayResultAtEndOfGame() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVH");
        game.play();
        assertEquals(true, fakeUI.hasDisplayedResultToUser());
    }

    @Test
    public void requestToQuitAfterGameOver() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVH");
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
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVH");
        game.play();
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void checkResultIsADraw() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVH");
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
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVH");
        game.play();
        assertEquals(true, game.isGameOver());
        assertEquals(false, fakeUI.isADraw());
        assertEquals(Counter.X, fakeUI.getWinner());
    }

    @Test
    public void checkGameTypeIsHVC() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVC");
        game.play();
        assertEquals("HVC", game.typeOfGame());
    }

    @Test
    public void humanVsComputer() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("HVC");
        game.play();
        assertEquals(Player.Type.Human, game.getPlayerType(Counter.X));
        assertEquals(Player.Type.Computer, game.getPlayerType(Counter.O));
    }

    @Test
    public void computerVsHuman() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI.addDummyInputs(initialState);
        fakeUI.setGameType("CVH");
        game.play();
        assertEquals(Player.Type.Computer, game.getPlayerType(Counter.X));
        assertEquals(Player.Type.Human, game.getPlayerType(Counter.O));
    }
}
