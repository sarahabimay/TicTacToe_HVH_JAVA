import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void getPlayersOpponent() {
        Player player1 = new Player(Counter.X, new UserInterface());
        assertEquals(Counter.O, player1.opponentMarker());
    }

    @Test
    public void boardUpdatedWithNewCounter() {
        MockUserInterface mockUI = new MockUserInterface();
        mockUI.addDummyInputs(mockUI.aListOfMoves(new Integer[]{1, 4, 2, 5, 3}));
        Player player1 = new Player(Counter.X, mockUI);
        Board board = new Board(3);
        board = player1.playTurn(board);
        ArrayList<String> foundPositions = board.findPositions(Counter.X);
        assertEquals(1, foundPositions.size());
    }
}