import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void getPlayersOpponent() {

        Player player1 = new Player(Counter.X, new CommandLineUI());
        assertEquals(Counter.O, player1.opponentCounter());
    }

    @Test
    public void boardUpdatedWithNewCounter() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1));
        FakeCommandLineUI fakeUI = new FakeCommandLineUI();
        fakeUI.addDummyInputs(initialState);
        Player player1 = new Player(Counter.X, fakeUI);
        Board board = new Board(3);
        board = player1.playTurn(board);
        assertEquals(Counter.X, board.cellValue(0));
    }
}