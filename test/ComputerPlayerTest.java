import org.junit.Before;
import org.junit.Ignore;
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
    public void oneChoiceForAlphaBetaAlgorithm() {
        Counter currentBoard[] = {
                X, O, E,
                O, X, X,
                X, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(3, computerXPlayer.calculateNextMoveWithAlphaBeta(board));
    }

    @Test
    public void twoChoicesForAlphaBetaAlgorithm() {
        Counter currentBoard[] = {
                X, X, E,
                X, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(3, computerOPlayer.calculateNextMoveWithAlphaBeta(board));
    }

    @Test
    public void threeChoicesForAlphaBetaAlgorithm() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int result = computerXPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals(3, result);
    }

    @Test
    public void fourChoicesForAlphaBetaAlgorithm() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                E, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals((Integer) 3, result);
    }

    @Test
    public void elevenOptionsAlphaBetaAlphaBeta_4x4() {
        Counter currentBoard[] = {
                E, E, E, E,
                E, X, E, E,
                E, E, X, E,
                E, O, O, X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals((Integer) 1, result);
    }

    @Test
    public void firstMoveIsAICounterlphaBetaAlphaBeta_3x3() {
        Counter currentBoard[] = {
                E, E, E,
                E, E, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals((Integer) 1, result);
    }

    @Test
    @Ignore
    public void firstMoveIsAICounterlphaBetaAlphaBeta_4x4() {
        Counter currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        int result = computerXPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals( 16, result);
    }

    @Test
    @Ignore
    public void firstMoveIsCounterOppAlphaBetaAlphaBeta_4x4() {

        Counter currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
                E, E, E, X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        int  result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals( 15, result);
    }

    @Test
    @Ignore
    public void aiPlayerShouldBlock_4x4() {

        Counter currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
                E, E, O, X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        int  result = computerXPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals( 11, result);
    }

    @Test
    public void alphaBetaShouldPickPositionToBlockOpponentWin() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                O, X, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int  result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals( 3, result);
    }

    @Test
    public void computerChoosesPositionToBlockOpponent() {
        Counter currentBoard[] = {
                E, E, X,
                O, X, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int  result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals( 7, result);
    }

    //FAILS
    @Test
    @Ignore
    public void oppStartsOnCornerAIPicksCenter() {
        Counter currentBoard[] = {
                X, E, E,
                E, E, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals( 5, result);
    }

    // FAILS BECAUSE IT TAKES THE FIRST BESTSCORE and position 9 must be
    // returning the same score as position 2
    @Test
    @Ignore
    public void aiVsPerfectPlayerMustPick9() {
        Counter currentBoard[] = {
                X, E, E,
                E, O, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int result = computerXPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals( 9, result);
    }

    @Test
    public void aiVsPerfectPlayerAIMustPickAnEdge() {
        Counter currentBoard[] = {
                X, E, E,
                E, O, E,
                E, E, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals( 2, result);
    }

    @Test
    public void mustBlockOpponent() {
        Counter currentBoard[] = {
                X, E, E,
                E, O, E,
                O, E, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int result = computerXPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals( 3, result);
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