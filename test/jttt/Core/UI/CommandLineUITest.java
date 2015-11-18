package jttt.Core.UI;

import jttt.Core.*;
import jttt.Core.Fakes.FakeCommandLineUI;
import jttt.Core.Players.ComputerPlayer;
import jttt.Core.Players.HumanPlayer;
import jttt.Core.Players.PlayerFactory;
import jttt.UI.CommandLineUI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CommandLineUITest {

    private FakeCommandLineUI fakeUI;

    @Before
    public void setUp() throws Exception {
        fakeUI = new FakeCommandLineUI();
    }

    @Test
    public void emptyBoardIsDisplayedCorrectly() {
        CommandLineUI ui = new CommandLineUI();
        ui.createNewGame(3, 1);
        Assert.assertEquals("" +
                        "[1][2][3]\n" +
                        "[4][5][6]\n" +
                        "[7][8][9]\n",
                ui.displayBoard());
    }

    @Test
    public void userChoosesToQuit() {
        fakeUI.addDummyPlayAgainChoice(1);
        Assert.assertEquals(false, fakeUI.requestPlayAgain());
    }

    @Test
    public void userChoosesToReplay() {
        fakeUI.addDummyPlayAgainChoice(2);
        Assert.assertEquals(true, fakeUI.requestPlayAgain());
    }

    @Test
    public void invalidEmptyBoardDimension() {
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{}));
        Assert.assertEquals(false, fakeUI.validate(fakeUI.requestBoardSize(), fakeUI::validateDimension));
    }

    @Test
    public void invalidNonEmptyBoardDimension() {
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{1, 2, 3, 4}));
        Assert.assertEquals(false, fakeUI.validate(fakeUI.requestBoardSize(), fakeUI::validateDimension));
    }

    @Test
    public void validBoardDimension() {
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        fakeUI.setGameType(1);
        fakeUI.addDummyDimension(3);
        Assert.assertEquals(true, fakeUI.validate(fakeUI.requestBoardSize(), fakeUI::validateDimension));
    }

    @Test
    public void invalidPositionIsIgnored() {
        Board board = new Board(3);
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{20, 1}));
        fakeUI.addDummyDimension(3);
        Assert.assertEquals(false, board.validPosition(fakeUI.requestNextPosition()));
        Assert.assertEquals(true, board.validPosition(fakeUI.requestNextPosition()));
    }

    @Test
    public void invalidGameType() {
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{}));
        fakeUI.setGameType(5);
        Assert.assertEquals(false, PlayerFactory.validPlayerTypes(fakeUI.requestGameType()));
    }

    @Test
    public void validGameType() {
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{}));
        fakeUI.setGameType(1);
        PlayerFactory factory = new PlayerFactory();
        Assert.assertEquals(true, PlayerFactory.validPlayerTypes(fakeUI.requestGameType()));
        Assert.assertEquals(true, fakeUI.validGameType(fakeUI.requestGameType()));
    }

    @Test
    public void cliFullHVHGamePlayedThenQuit() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(1);
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        fakeUI.addDummyPlayAgainChoice(1);
        fakeUI.start();
        Assert.assertEquals(true, fakeUI.hasAskedUserForDimension());
        Assert.assertEquals(true, fakeUI.hasAskedUserForGameType());
        Assert.assertEquals(true, fakeUI.hasAskedUserForNextPosition());
        Assert.assertEquals(true, fakeUI.hasAskedUserToQuitGame());
    }

    @Test
    public void choose3x3Game() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        fakeUI.start();
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
        fakeUI.start();
        assertEquals(true, fakeUI.hasAskedUserForDimension());
    }

    @Test
    public void checkUserHasBeenAskedForTypeOfGame() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        fakeUI.start();
        assertEquals(true, fakeUI.hasAskedUserForGameType());
    }

    @Test
    public void checkHumanHasBeenAskedForNextMove() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        fakeUI.start();
        assertEquals(true, fakeUI.hasAskedUserForNextPosition());
    }

    @Test
    public void checkValidateGameTypeHasBeenCalled() {
        fakeUI = generateFakeUI(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9), 3, 1);
        fakeUI.start();
        assertEquals(true, fakeUI.hasGameTypeBeenValidated());
    }

    @Test
    public void displayBoardAfterEachMove() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        fakeUI.start();
        assertEquals(true, fakeUI.hasDisplayedBoardToUser());
    }

    @Test
    public void displayResultAtEndOfGame() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        fakeUI.start();
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
        fakeUI.start();
        assertEquals(true, fakeUI.hasAskedUserToQuitGame());
    }

    @Test
    public void checkGameIsOver() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        fakeUI.start();
        assertEquals(true, fakeUI.getGame().isGameOver());
    }

    @Test
    public void checkResultIsADraw() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        fakeUI.start();
        assertEquals(true, fakeUI.getGame().isGameOver());
        assertEquals(true, fakeUI.isADraw());
    }

    @Test
    public void checkResultIsAWin() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 1);
        fakeUI.start();
        assertEquals(true, fakeUI.getGame().isGameOver());
        assertEquals(false, fakeUI.isADraw());
        assertEquals(Mark.X, fakeUI.getWinner());
    }

    @Test
    public void humanVsComputer() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 2);
        fakeUI.start();
        assertEquals(HumanPlayer.class, fakeUI.getGame().getPlayer(Mark.X).getClass());
        assertEquals(ComputerPlayer.class, fakeUI.getGame().getPlayer(Mark.O).getClass());
    }

    @Test
    public void computerVsHuman() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI = generateFakeUI(initialState, 3, 3);
        fakeUI.start();
        assertEquals(ComputerPlayer.class, fakeUI.getGame().getPlayer(Mark.X).getClass());
        assertEquals(HumanPlayer.class, fakeUI.getGame().getPlayer(Mark.O).getClass());
    }

    @Test
    public void duplicateMoveRepeatsMoveRequestTillValid() {
        fakeUI = generateFakeUI(Arrays.asList(
                1, 2, 2,
                4, 7, 3,
                8, 9, 5), 3, 1);
        fakeUI.start();
        assertEquals("" +
                        "[X][O][X]\n" +
                        "[X][O][6]\n" +
                        "[O][O][X]\n",
                fakeUI.displayBoard());
    }

    private FakeCommandLineUI generateFakeUI(List<Integer> moves, int dimension, Integer gameType) {
        List<Integer> initialState = new ArrayList<>(moves);
        fakeUI.addDummyDimension(dimension);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(gameType);
        return fakeUI;
    }
}