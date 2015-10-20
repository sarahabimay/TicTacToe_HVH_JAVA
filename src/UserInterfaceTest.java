import org.junit.Test;

import static org.junit.Assert.*;

public class UserInterfaceTest {
    @Test
    public void boardIsDisplayedCorrectly(){
        UserInterface ui = new UserInterface();
        Board board = new Board(3);
        assertEquals("[1][2][3]\n" +
                "[4][5][6]\n" +
                "[7][8][9]\n",ui.displayBoard(board));

    }

    @Test
    public void promptForDimensionsOfBoard() {
        UserInterface ui = new UserInterface();
        assertEquals("Please provide the dimensions of the board:\n", ui.requestBoardSize());
    }

    @Test
    public void promptForNewMove() {
        UserInterface ui = new UserInterface();
        Board board = new Board(3);
        assertEquals("Please enter the position number for your next move:\n", ui.requestNextPosition());
    }

    @Test
    public void displayFinalResult() {
    }

}