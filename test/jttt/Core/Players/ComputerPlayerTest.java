package jttt.Core.Players;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.Fakes.FakeCommandLineUI;
import org.junit.Assert;
import org.junit.Before;
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
    private Mark X = Mark.X;
    private Mark O = Mark.O;
    private Mark E = Mark.EMPTY;

    @Before
    public void setUp() {
        fakeUI = new FakeCommandLineUI();
        computerXPlayer = new ComputerPlayer(Mark.X, fakeUI);
        computerOPlayer = new ComputerPlayer(Mark.O, fakeUI);
    }

    @Test
    public void createComputerPlayerType() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1));
        fakeUI.addDummyHumanMoves(initialState);
        ComputerPlayer computer = new ComputerPlayer(Mark.X, fakeUI);
        Assert.assertEquals(ComputerPlayer.class, computer.getClass());
    }

    @Test
    public void getPlayersOpponent() {
        Player computerPlayer = new ComputerPlayer(Mark.O, fakeUI);
        Assert.assertEquals(Mark.X, computerPlayer.opponentCounter());
    }

    @Test
    public void boardUpdatedWithNewCounter() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(2);
        Board board = new Board(3);
        board = computerXPlayer.playTurn(board);
        Assert.assertEquals(1, board.findPositions(Mark.X).size());
    }

    @Test
    public void oneChoiceForAlphaBetaAlgorithm() {
        Mark currentBoard[] = {
                X, O, E,
                O, X, X,
                X, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(X, computerXPlayer.playTurn(board).findMarkAtIndex(2));
    }

    @Test
    public void twoChoicesForAlphaBetaAlgorithm() {
        Mark currentBoard[] = {
                X, X, E,
                X, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(X, computerXPlayer.playTurn(board).findMarkAtIndex(2));
    }

    @Test
    public void threeChoicesForAlphaBetaAlgorithm() {
        Mark currentBoard[] = {
                X, X, E,
                E, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(X, computerXPlayer.playTurn(board).findMarkAtIndex(2));
    }

    @Test
    public void fourChoicesForAlphaBetaAlgorithm() {
        Mark currentBoard[] = {
                X, X, E,
                E, O, X,
                E, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(O, computerOPlayer.playTurn(board).findMarkAtIndex(2));
    }

    @Test
    public void elevenOptionsAlphaBetaAlphaBeta_4x4() {
        Mark currentBoard[] = {
                E, E, E, E,
                E, X, E, E,
                E, E, X, E,
                E, O, O, X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        assertEquals(O, computerOPlayer.playTurn(board).findMarkAtIndex(0));
    }

    @Test
    public void firstMoveIsAICounterlphaBetaAlphaBeta_3x3() {
        Mark currentBoard[] = {
                E, E, E,
                E, E, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(O, computerOPlayer.playTurn(board).findMarkAtIndex(0));
    }

    @Test
    public void firstMoveIsRandomlySelected_4x4() {
        Mark currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        List<Mark> result = computerXPlayer.playTurn(board).getCells();
        assertThat(result, hasItem(X) );
    }

    @Test
    public void firstMoveIsCounterOppAlphaBetaAlphaBeta_4x4() {
        Mark currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
                E, E, E, X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        List<Mark> result = computerOPlayer.playTurn(board).getCells();
        assertThat(result, hasItem(O) );
    }

    @Test
    public void alphaBetaShouldPickPositionToBlockOpponentWin() {
        Mark currentBoard[] = {
                X, X, E,
                E, O, X,
                O, X, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(O, computerOPlayer.playTurn(board).findMarkAtIndex(2));
    }

    @Test
    public void computerChoosesPositionToBlockOpponent() {
        Mark currentBoard[] = {
                E, E, X,
                O, X, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(O, computerOPlayer.playTurn(board).findMarkAtIndex(6));
    }

    @Test
    public void oppStartsOnCornerAIPicksCenter() {
        Mark currentBoard[] = {
                X, E, E,
                E, E, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(O, computerOPlayer.playTurn(board).findMarkAtIndex(4));
    }

    @Test
    public void aiVsPerfectPlayerAIMustPickAnEdge() {
        Mark currentBoard[] = {
                X, E, E,
                E, O, E,
                E, E, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(O, computerOPlayer.playTurn(board).findMarkAtIndex(1));
    }

    @Test
    public void mustBlockOpponent() {
        Mark currentBoard[] = {
                X, E, E,
                E, O, E,
                O, E, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(X, computerXPlayer.playTurn(board).findMarkAtIndex(2));
    }

    private List<Mark> arrayToList(Mark[] initialBoard) {
        List<Mark> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}