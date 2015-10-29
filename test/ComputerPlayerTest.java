import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ComputerPlayerTest {
    public FakeCommandLineUI fakeUI;

    @Before
    public void setUp() throws Exception {
        fakeUI = new FakeCommandLineUI();
    }

    @Test
    public void createComputerPlayerType() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1));
        fakeUI.addDummyInputs(initialState);
        ComputerPlayer human = new ComputerPlayer(Counter.X, fakeUI);
        assertEquals(ComputerPlayer.class, human.getClass());
    }

    @Test
    public void getPlayersOpponent() {
        Player player1 = new ComputerPlayer(Counter.X, fakeUI);
        assertEquals(Counter.O, player1.opponentCounter());
    }

    @Test
    public void boardUpdatedWithNewCounter() {
        fakeUI.setGameType("HVC");
        Player player1 = new ComputerPlayer(Counter.X, fakeUI);
        Board board = new Board(3);
        board = player1.playTurn(board);
        assertNotEquals("" +
                        "[1][2][3]\n" +
                        "[4][5][6]\n" +
                        "[7][8][9]\n",
                fakeUI.displayBoard(board));
        assertEquals("" +
                        "[1][X][3]\n" +
                        "[4][5][6]\n" +
                        "[7][8][9]\n",
                fakeUI.displayBoard(board));
    }
}