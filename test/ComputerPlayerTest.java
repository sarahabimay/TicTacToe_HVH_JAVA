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
    public ComputerPlayer computerXPlayer;
    public ComputerPlayer computerOPlayer;
    private Counter X = Counter.X;
    private Counter O = Counter.O;

    @Before
    public void setUp() throws Exception {
        fakeUI = new FakeCommandLineUI();
        computerXPlayer = new ComputerPlayer(Counter.X, fakeUI);
        computerOPlayer = new ComputerPlayer(Counter.O, fakeUI);
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
        board = computerXPlayer.playTurn(board);
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
        long range = computerXPlayer.calculateNumberRange(board);
        assertEquals(9, range);
    }

    @Test
    public void calculateNextMoveIsValid() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(2);
        Board board = new Board(3);
        Integer nextMove = computerXPlayer.calculateRandomPosition(board);
        assertEquals(true, board.validPosition(nextMove));
    }

    @Test
    public void calculateFractionFromRangeIsValid() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(2);
        long randomFraction = computerXPlayer.randomFractionFromRange(9);
        assertThat(randomFraction, greaterThanOrEqualTo((long) 0));
        assertThat(randomFraction, lessThanOrEqualTo((long) 8));
    }

    @Test
    public void calculateRandomNumberIs1() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(2);
        long randomNumber = computerXPlayer.randomNumberInRange(0);
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
    private List<Counter> arrayToList(Counter[] initialBoard) {
        List<Counter> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}