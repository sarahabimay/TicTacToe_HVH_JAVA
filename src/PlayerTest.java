import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void getPlayersOpponent(){
        Player player1 = new Player(Counter.X);
        assertEquals(Counter.O, player1.opponentMarker());
    }

    @Test
    public void boardReplaceWithNewCounter() {
        Player player1 = new Player(Counter.X);
        Board board = new Board(3);
        Game game = new Game(board, player1, new Player(Counter.O));
        board = player1.playTurn(board);
        ArrayList<String> foundPositions = board.findPositions(Counter.X);
        assertEquals(1, foundPositions.size());
        assertEquals("[1][2][X]\n" +
                        "[4][5][6]\n" +
                        "[7][8][9]\n",
                game.displayBoard());
    }
}