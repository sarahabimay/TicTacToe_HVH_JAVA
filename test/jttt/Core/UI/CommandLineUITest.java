package jttt.Core.UI;

import jttt.Core.Board;
import jttt.Core.FakeCommandLineUI;
import jttt.Core.PlayerFactory;
import jttt.UI.CommandLineUI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}