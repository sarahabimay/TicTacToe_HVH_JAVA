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
        assertEquals((Integer) 3, computerXPlayer.calculateNextMoveWithAlphaBeta(board));
    }

    @Test
    public void twoChoicesForAlphaBetaAlgorithm() {
        Counter currentBoard[] = {
                X, X, E,
                X, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals((Integer) 3, computerOPlayer.calculateNextMoveWithAlphaBeta(board));
    }

    @Test
    public void threeChoicesForAlphaBetaAlgorithm() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerXPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals((Integer) 3, result);
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
        assertEquals((Integer) 9, result);
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
        Integer result = computerXPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals((Integer) 16, result);
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
        Integer result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals((Integer) 15, result);
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
        Integer result = computerXPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals((Integer) 11, result);
    }

    @Test
    public void alphaBetaShouldPickPositionToBlockOpponentWin() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                O, X, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals((Integer) 3, result);
    }
    // FAILS
    @Test
    public void computerChoosesPositionToBlockOpponent() {
        Counter currentBoard[] = {
                E, E, X,
                O, X, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
//        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 7, result);
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
        Integer result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
//        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 5, result);
    }

    @Test
    public void aiVsPerfectPlayerMustPick9() {
        Counter currentBoard[] = {
                X, E, E,
                E, O, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerXPlayer.calculateNextMoveWithAlphaBeta(board);
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
        Integer result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
        assertEquals((Integer) 8, result);
    }

    // FAILS
    @Test
    public void mustBlockOpponent_1() {
        Counter currentBoard[] = {
                X, E, E,
                E, O, E,
                O, E, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerXPlayer.calculateNextMoveWithAlphaBeta(board);
//        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 3, result);
    }

    // FAILS
    @Test
    public void mustBlockOpponent_2() {
        Counter currentBoard[] = {
                X, E, E,
                X, O, E,
                O, X, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerXPlayer.calculateNextMoveWithAlphaBeta(board);
//        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 3, result);
    }
    // FAILS
    @Test
    public void mustBlockOpponent_3() {
        Counter currentBoard[] = {
                X, E, E,
                X, E, E,
                E, E, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
//        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 7, result);
    }
    // FAILS BOTH MINIMAX AND ABPruning
    @Test
    public void mustBlockOpponent_4() {
        Counter currentBoard[] = {
                X, E, E,
                E, E, O,
                X, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
//        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
        assertEquals((Integer) 4, result);
    }

    @Test
    public void aiVsPerfectPlayerAIMustPlayPerfectly() {
        Counter currentBoard[] = {
                X, E, E,
                E, O, X,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = computerOPlayer.calculateNextMoveWithAlphaBeta(board);
//        Integer result = computerOPlayer.calculateNextMoveWithMinimax(board);
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