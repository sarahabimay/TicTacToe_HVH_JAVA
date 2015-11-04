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
        fakeUI.addDummyHumanMoves(initialState);
        FakeComputerPlayer computer = new FakeComputerPlayer(Counter.X, fakeUI);
        assertEquals(FakeComputerPlayer.class, computer.getClass());
    }

    @Test
    public void getPlayersOpponent() {
        Player computerPlayer = new ComputerPlayer(Counter.O, fakeUI);
//        Player player1 = new FakeComputerPlayer(Counter.X, fakeUI);
        assertEquals(Counter.X, computerPlayer.opponentCounter());
    }

    @Test
    public void boardUpdatedWithNewCounter() {
        fakeUI.setGameType(2);
        FakeComputerPlayer player1 = new FakeComputerPlayer(Counter.X, fakeUI);
        player1.setDummyPosition(1);
        Board board = new Board(3);
        board = player1.playTurn(board);
        assertNotEquals("" +
                        "[1][2][3]\n" +
                        "[4][5][6]\n" +
                        "[7][8][9]\n",
                fakeUI.displayBoard(board));
    }

    @Test
    public void computerHasGeneratedAMove() {
        fakeUI.setGameType(2);
        FakeComputerPlayer player1 = new FakeComputerPlayer(Counter.X, fakeUI);
        player1.setDummyPosition(1);
        Board board = new Board(3);
        board = player1.playTurn(board);
        assertEquals(true, player1.computerHasGeneratedNextMove());
    }

    @Test
    public void randomPositionGenerator() {
        fakeUI.setGameType(2);
        Player player1 = new ComputerPlayer(Counter.X, fakeUI);
        Board board = new Board(3);
        for (int i = 0; i < 9; i++) {
            board = player1.playTurn(board);
            assertNotEquals("" +
                            "[1][2][3]\n" +
                            "[4][5][6]\n" +
                            "[7][8][9]\n",
                    fakeUI.displayBoard(board));
        }
    }
}