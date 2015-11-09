import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ComputerPlayerTest {
    public FakeCommandLineUI fakeUI;
    public ComputerPlayer computerXPlayer;
    public ComputerPlayer computerOPlayer;
    private Counter X = Counter.X;
    private Counter O = Counter.O;
    private Counter E = Counter.EMPTY;

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
    public void oneChoiceForMinimaxAlgorithm() {
        Counter currentBoard[] = {
                X, O, E,
                O, X, X,
                X, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals((Integer) 3, computerXPlayer.calculateNextMoveWithMinimax(board));
    }

    @Test
    public void twoChoicesForMinimaxAlgorithm() {
        Counter currentBoard[] = {
                X, X, E,
                X, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals((Integer) 3, computerOPlayer.calculateNextMoveWithMinimax(board));
    }

    @Test
    public void threeChoicesForMinimaxAlgorithm() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerXPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 3, result);
    }

    @Test
    public void fourChoicesForMinimaxAlgorithm() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                E, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 3, result);
    }

    @Test
    public void minimaxAIPlayerCannotWin() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                X, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 4, result);
    }

    @Test
    public void minimaxShouldPickPositionToBlockOpponentWin() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                O, X, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 3, result);
    }

    @Test
    public void computerChoosesPositionToBlockOpponent() {
        Counter currentBoard[] = {
                X, E, E,
                O, X, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 9, result);
    }

    @Test
    public void oppStartsOnCornerAIPicksCenter() {
        Counter currentBoard[] = {
                X, E, E,
                E, E, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 5, result);
    }

    @Test
    public void aiVsPerfectPlayerMustPick8() {
        Counter currentBoard[] = {
                X, E, E,
                E, O, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerXPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 9, result);
    }

    @Test
    public void aiVsPerfectPlayerAIMustPickAnEdge() {
        Counter currentBoard[] = {
                X, E, E,
                E, O, E,
                E, E, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 8, result);
    }

    @Test
    public void aiVsPerfectPlayerAIMustPlayPerfectly() {
        Counter currentBoard[] = {
                X, E, E,
                E, O, X,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 9, result);
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

    private List<Counter> arrayToList(Counter[] initialBoard) {
        List<Counter> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}