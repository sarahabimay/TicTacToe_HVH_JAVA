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
    public void boardReplaceWithNewCounter() {
        UserInterface ui = new UserInterface();
        Player player1 = new Player(Counter.X, ui);
        Board board = new Board(3);
        board = player1.playTurn(board);
        ArrayList<String> foundPositions = board.findPositions(Counter.X);
        assertEquals(1, foundPositions.size());
    }
}