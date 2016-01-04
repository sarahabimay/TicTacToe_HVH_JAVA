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
        computerXPlayer = new ComputerPlayer(Mark.X);
        computerOPlayer = new ComputerPlayer(Mark.O);
    }

    @Test
    public void createComputerPlayerType() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1));
        fakeUI.addDummyHumanMoves(initialState);
        ComputerPlayer computer = new ComputerPlayer(Mark.X);
        Assert.assertEquals(ComputerPlayer.class, computer.getClass());
    }

    @Test
    public void getPlayersOpponent() {
        Player computerPlayer = new ComputerPlayer(Mark.O);
        Assert.assertEquals(Mark.X, computerPlayer.opponentCounter());
    }

    @Test
    public void boardUpdatedWithNewCounter() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(2);
        Board board = new Board(3);
        int nextMove = computerXPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, Mark.X);
        assertEquals(computerXPlayer.getMark(), board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void oneChoiceForAlphaBetaAlgorithm() {
        Mark currentBoard[] = {
                X, O, E,
                O, X, X,
                X, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerXPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, Mark.X);
        assertEquals(X, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void twoChoicesForAlphaBetaAlgorithm() {
        Mark currentBoard[] = {
                X, X, E,
                X, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerXPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, Mark.X);
        assertEquals(X, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void threeChoicesForAlphaBetaAlgorithm() {
        Mark currentBoard[] = {
                X, X, E,
                E, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerXPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, Mark.X);
        assertEquals(X, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void fourChoicesForAlphaBetaAlgorithm() {
        Mark currentBoard[] = {
                X, X, E,
                E, O, X,
                E, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, Mark.O);
        assertEquals(O, board.findMarkAtDisplayPosition(nextMove));
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
        int nextMove = computerOPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, Mark.O);
        assertEquals(O, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void firstMoveIsAI_3x3() {
        Mark currentBoard[] = {
                E, E, E,
                E, E, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, Mark.O);
        assertEquals(O, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void firstMoveIsAI_RandomlySelectedStrategy_4x4() {
        Mark currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        int nextMove = computerXPlayer.getNextPosition(board);
        board = board.playCounterInPosition(nextMove, Mark.X);
        assertThat(board.getCells(), hasItem(X) );
    }

    @Test
    public void aiPlayerShouldBlockOpponentWin_1() {
        Mark currentBoard[] = {
                X, X, E,
                E, O, X,
                O, X, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        assertEquals(3, nextMove);
    }

    @Test
    public void aiPlayerShouldBlockOpponentWin_2() {
        Mark currentBoard[] = {
                E, E, X,
                O, X, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        assertEquals(7, nextMove);
    }

    @Test
    public void aiPlayerShouldChooseWinningPosition() {
        Mark currentBoard[] = {
                X, E, E,
                E, O, E,
                O, E, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        assertEquals(3, nextMove);
    }

    @Test
    public void aiPlayerShouldPicksCenter() {
        Mark currentBoard[] = {
                X, E, E,
                E, E, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, Mark.O);
        assertEquals(O, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void aiVsPerfectPlayerAIMustPickAnEdge() {
        Mark currentBoard[] = {
                X, E, E,
                E, O, E,
                E, E, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, Mark.O);
        assertEquals(O, board.findMarkAtDisplayPosition(nextMove));
    }

    private List<Mark> arrayToList(Mark[] initialBoard) {
        List<Mark> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}