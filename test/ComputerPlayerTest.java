import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.*;

public class ComputerPlayerTest {
    public FakeCommandLineUI fakeUI;
    public ComputerPlayer computerPlayer;

    @Before
    public void setUp() throws Exception {
        fakeUI = new FakeCommandLineUI();
        computerPlayer = new ComputerPlayer(Counter.X, fakeUI);
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
        assertEquals(Counter.X, computerPlayer.opponentCounter());
    }

    @Test
    public void boardUpdatedWithNewCounter() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(2);
        Board board = new Board(3);
        board = computerPlayer.playTurn(board);
        assertNotEquals("" +
                        "[1][2][3]\n" +
                        "[4][5][6]\n" +
                        "[7][8][9]\n",
                fakeUI.displayBoard(board));
    }

    @Test
    public void calculate3x3NumberRangeForRandomCalculation() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(2);
        Board board = new Board(3);
        long range = computerPlayer.calculateNumberRange(board);
        assertEquals(9, range);
    }

    @Test
    public void calculateNextMoveIsValid() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(2);
        Board board = new Board(3);
        Integer nextMove = computerPlayer.calculateRandomPosition(board);
        assertEquals(true, board.validPosition(nextMove));
    }

    @Test
    public void calculateFractionFromRangeIsValid() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(2);
        long randomFraction = computerPlayer.randomFractionFromRange(9);
        assertThat(randomFraction, greaterThanOrEqualTo((long) 0));
        assertThat(randomFraction, lessThanOrEqualTo((long) 8));
    }

    @Test
    public void calculateRandomNumberIs1() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(2);
        long randomNumber = computerPlayer.randomNumberInRange(0);
        assertThat(randomNumber, equalTo((long) 1));
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