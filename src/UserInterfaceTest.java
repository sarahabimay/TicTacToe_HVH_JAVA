import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserInterfaceTest {

    public MockUserInterface mockUI;

    @Before
    public void setUp() throws Exception {
        mockUI = new MockUserInterface();
    }

    @Test
    public void emptyBoardIsDisplayedCorrectly() {
        UserInterface ui = new UserInterface();
        Board board = new Board(3);
        assertEquals("[1][2][3]\n" +
                "[4][5][6]\n" +
                "[7][8][9]\n", ui.displayBoard(board));

    }

    @Test
    public void userChoosesToQuit() {
        mockUI.addDummyPlayAgainChoice(1);
        assertEquals(false, mockUI.requestPlayAgain());
    }

    @Test
    public void userChoosesToReplay() {
        mockUI.addDummyPlayAgainChoice(2);
        assertEquals(true, mockUI.requestPlayAgain());
    }

    @Test
    public void invalidEmptyBoardDimension() {
        mockUI.addDummyInputs(mockUI.aListOfMoves(new Integer[]{}));
        assertEquals(false, mockUI.validate(mockUI.requestBoardSize(), mockUI::validateDimension));
    }

    @Test
    public void invalidNonEmptyBoardDimension() {
        mockUI.addDummyInputs(mockUI.aListOfMoves(new Integer[]{1, 2, 3, 4}));
        assertEquals(false, mockUI.validate(mockUI.requestBoardSize(), mockUI::validateDimension));
    }

    @Test
    public void validBoardDimension() {
        mockUI.addDummyInputs(mockUI.aListOfMoves(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        assertEquals(true, mockUI.validate(mockUI.requestBoardSize(), mockUI::validateDimension));
    }

    @Test
    public void invalidPositionIsIgnored() {
        mockUI.addDummyInputs(mockUI.aListOfMoves(new Integer[]{20, 1, 4, 2, 5, 3}));
        assertEquals((Integer) 1, mockUI.requestNextPosition());
        assertEquals((Integer) 4, mockUI.requestNextPosition());
        assertEquals((Integer) 2, mockUI.requestNextPosition());
    }
}