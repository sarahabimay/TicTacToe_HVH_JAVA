import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class HumanPlayerTest {

    public FakeCommandLineUI fakeUI;

    @Before
    public void setUp() throws Exception {
        fakeUI = new FakeCommandLineUI();
    }

    @Test
    public void createHumanPlayerType() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1));
        fakeUI.addDummyHumanMoves(initialState);
        HumanPlayer human = new HumanPlayer(Counter.X, fakeUI);
        assertEquals(HumanPlayer.class, human.getClass());
    }

    @Test
    public void getPlayersOpponent() {
        Player player1 = new HumanPlayer(Counter.X, fakeUI);
        assertEquals(Counter.O, player1.opponentCounter());
    }

    @Test
    public void boardUpdatedWithNewCounter() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(10, 1));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        Player player1 = new HumanPlayer(Counter.X, fakeUI);
        Board board = new Board(3);
        board = player1.playTurn(board);
        assertEquals(Counter.X, board.cellValue(0));
    }
}