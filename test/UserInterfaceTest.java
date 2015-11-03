import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserInterfaceTest {

    public FakeCommandLineUI fakeUI;

    @Before
    public void setUp() throws Exception {
        fakeUI = new FakeCommandLineUI();
    }

    @Test
    public void emptyBoardIsDisplayedCorrectly() {
        CommandLineUI ui = new CommandLineUI();
        Board board = new Board(3);
        assertEquals("" +
                        "[1][2][3]\n" +
                        "[4][5][6]\n" +
                        "[7][8][9]\n",
                ui.displayBoard(board));
    }

    @Test
    public void userChoosesToQuit() {
        fakeUI.addDummyPlayAgainChoice(1);
        assertEquals(false, fakeUI.requestPlayAgain());
    }

    @Test
    public void userChoosesToReplay() {
        fakeUI.addDummyPlayAgainChoice(2);
        assertEquals(true, fakeUI.requestPlayAgain());
    }

    @Test
    public void invalidEmptyBoardDimension() {
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{}));
        assertEquals(false, fakeUI.validate(fakeUI.requestBoardSize(), fakeUI::validateDimension));
    }

    @Test
    public void invalidNonEmptyBoardDimension() {
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{1, 2, 3, 4}));
        assertEquals(false, fakeUI.validate(fakeUI.requestBoardSize(), fakeUI::validateDimension));
    }

    @Test
    public void validBoardDimension() {
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        fakeUI.setGameType("HVH");
        fakeUI.addDummyDimension(3);
        assertEquals(true, fakeUI.validate(fakeUI.requestBoardSize(), fakeUI::validateDimension));
    }

    @Test
    public void invalidPositionIsIgnored() {
        Board board = new Board(3);
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{20, 1}));
        fakeUI.addDummyDimension(3);
        assertEquals(false, board.validPosition(fakeUI.requestNextPosition()));
        assertEquals(true, board.validPosition(fakeUI.requestNextPosition()));
    }

    @Test
    public void invalidPlayerType() {
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{}));
        fakeUI.setGameType("hvh");
        assertEquals(false, PlayerFactory.validPlayerTypes(fakeUI.requestGameType()));
    }

    @Test
    public void validatePlayerTypes() {
        fakeUI.addDummyHumanMoves(fakeUI.aListOfMoves(new Integer[]{}));
        fakeUI.setGameType("HVH");
        PlayerFactory factory = new PlayerFactory();
        assertEquals(true, fakeUI.validateGameType(fakeUI.requestGameType()));
    }
}