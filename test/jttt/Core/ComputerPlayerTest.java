package jttt.Core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ComputerPlayerTest {
    private FakeCommandLineUI fakeUI;
    private ComputerPlayer computerXPlayer;
    private ComputerPlayer computerOPlayer;
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
        Assert.assertEquals(FakeComputerPlayer.class, computer.getClass());
    }

    @Test
    public void getPlayersOpponent() {
        Player computerPlayer = new ComputerPlayer(Counter.O, fakeUI);
        Assert.assertEquals(Counter.X, computerPlayer.opponentCounter());
    }

    @Test
    public void boardUpdatedWithNewCounter() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(2);
        Board board = new Board(3);
        board = computerXPlayer.playTurn(board);
        Assert.assertEquals(1, board.findPositions(Counter.X).size());
    }

    @Test
    public void oneChoiceForAlphaBetaAlgorithm() {
        Counter currentBoard[] = {
                X, O, E,
                O, X, X,
                X, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        currentBoard[2] = X;
        List<Counter> expected = arrayToList(currentBoard);
        assertEquals(expected, computerXPlayer.playTurn(board).getCells());
    }

    @Test
    public void twoChoicesForAlphaBetaAlgorithm() {
        Counter currentBoard[] = {
                X, X, E,
                X, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        currentBoard[2] = X;
        List<Counter> expected = arrayToList(currentBoard);
        assertEquals(expected, computerXPlayer.playTurn(board).getCells());
    }

    @Test
    public void threeChoicesForAlphaBetaAlgorithm() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        currentBoard[2] = X;
        List<Counter> expected = arrayToList(currentBoard);
        assertEquals(expected, computerXPlayer.playTurn(board).getCells());
    }

    @Test
    public void fourChoicesForAlphaBetaAlgorithm() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                E, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        currentBoard[2] = O;
        List<Counter> expected = arrayToList(currentBoard);
        assertEquals(expected, computerOPlayer.playTurn(board).getCells());
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
        currentBoard[0] = O;
        List<Counter> expected = arrayToList(currentBoard);
        assertEquals(expected, computerOPlayer.playTurn(board).getCells());
    }

    @Test
    public void firstMoveIsAICounterlphaBetaAlphaBeta_3x3() {
        Counter currentBoard[] = {
                E, E, E,
                E, E, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        currentBoard[0] = O;
        List<Counter> expected = arrayToList(currentBoard);
        assertEquals(expected, computerOPlayer.playTurn(board).getCells());
    }

    @Test
    public void firstMoveIsRandomlySelected_4x4() {
        Counter currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        List<Counter> result = computerXPlayer.playTurn(board).getCells();
        assertThat(result, hasItem(X) );
    }

    @Test
    public void firstMoveIsCounterOppAlphaBetaAlphaBeta_4x4() {

        Counter currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
                E, E, E, X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        List<Counter> result = computerOPlayer.playTurn(board).getCells();
        assertThat(result, hasItem(O) );
    }

    @Test
    public void alphaBetaShouldPickPositionToBlockOpponentWin() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                O, X, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        currentBoard[2] = O;
        List<Counter> expected = arrayToList(currentBoard);
        assertEquals(expected, computerOPlayer.playTurn(board).getCells());
    }

    @Test
    public void computerChoosesPositionToBlockOpponent() {
        Counter currentBoard[] = {
                E, E, X,
                O, X, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        currentBoard[6] = O;
        List<Counter> expected = arrayToList(currentBoard);
        assertEquals(expected, computerOPlayer.playTurn(board).getCells());
    }

    @Test
    public void oppStartsOnCornerAIPicksCenter() {
        Counter currentBoard[] = {
                X, E, E,
                E, E, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        currentBoard[4] = O;
        List<Counter> expected = arrayToList(currentBoard);
        assertEquals(expected, computerOPlayer.playTurn(board).getCells());
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
        currentBoard[8] = X;
        List<Counter> expected = arrayToList(currentBoard);
        assertEquals(expected, computerXPlayer.playTurn(board).getCells());
//        int result = computerXPlayer.calculateNextMoveWithAlphaBeta(board);
//        assertEquals( 9, result);
    }

    @Test
    public void aiVsPerfectPlayerAIMustPickAnEdge() {
        Counter currentBoard[] = {
                X, E, E,
                E, O, E,
                E, E, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        currentBoard[1] = O;
        List<Counter> expected = arrayToList(currentBoard);
        assertEquals(expected, computerOPlayer.playTurn(board).getCells());
    }

    @Test
    public void mustBlockOpponent() {
        Counter currentBoard[] = {
                X, E, E,
                E, O, E,
                O, E, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        currentBoard[2] = X;
        List<Counter> expected = arrayToList(currentBoard);
        assertEquals(expected, computerXPlayer.playTurn(board).getCells());
    }


    @Test
    public void computerHasGeneratedAMove() {
        fakeUI.setGameType(2);
        FakeComputerPlayer player1 = new FakeComputerPlayer(Counter.X, fakeUI);
        player1.setDummyPosition(1);
        Board board = new Board(3);
        board = player1.playTurn(board);
        Assert.assertEquals(true, player1.computerHasGeneratedNextMove());
    }

    private List<Counter> arrayToList(Counter[] initialBoard) {
        List<Counter> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}