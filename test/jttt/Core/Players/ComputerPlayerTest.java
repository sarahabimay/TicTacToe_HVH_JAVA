package jttt.core.players;

import jttt.UI.FakeCommandLineUI;
import jttt.core.board.Board;
import jttt.core.board.Mark;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jttt.core.board.Mark.*;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ComputerPlayerTest {
    private FakeCommandLineUI fakeUI;
    private ComputerPlayer computerXPlayer;
    private ComputerPlayer computerOPlayer;
    private OutputStream output;
    private InputStream inputStream;
    private Writer writer;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        writer = new OutputStreamWriter(output);
        inputStream = new ByteArrayInputStream("1".getBytes());
        fakeUI = new FakeCommandLineUI(null, inputStream, writer);
        computerXPlayer = new ComputerPlayer(X);
        computerOPlayer = new ComputerPlayer(O);
    }

    @Test
    public void createComputerPlayerType() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1));
        fakeUI.addDummyHumanMoves(initialState);
        ComputerPlayer computer = new ComputerPlayer(X);
        Assert.assertEquals(ComputerPlayer.class, computer.getClass());
    }

    @Test
    public void getPlayersOpponent() {
        Player computerPlayer = new ComputerPlayer(O);
        Assert.assertEquals(X, computerPlayer.opponentCounter());
    }

    @Test
    public void boardUpdatedWithNewCounter() {
        fakeUI.addDummyDimension(3);
        fakeUI.setGameType(2);
        Board board = new Board(3);
        int nextMove = computerXPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, X);
        assertEquals(computerXPlayer.getMark(), board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void oneChoiceForAlphaBetaAlgorithm() {
        Mark currentBoard[] = {
                X, O, EMPTY,
                O, X, X,
                X, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerXPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, X);
        assertEquals(X, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void twoChoicesForAlphaBetaAlgorithm() {
        Mark currentBoard[] = {
                X, X, EMPTY,
                X, O, X,
                O, O, EMPTY
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerXPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, X);
        assertEquals(X, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void threeChoicesForAlphaBetaAlgorithm() {
        Mark currentBoard[] = {
                X,      X,  EMPTY,
                EMPTY,  O,  X,
                O,      O,  EMPTY
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerXPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, X);
        assertEquals(X, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void fourChoicesForAlphaBetaAlgorithm() {
        Mark currentBoard[] = {
                X,      X,  EMPTY,
                EMPTY,  O,  X,
                EMPTY,  O,  EMPTY
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, O);
        assertEquals(O, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void elevenOptionsAlphaBetaAlphaBeta_4x4() {
        Mark currentBoard[] = {
                EMPTY,  EMPTY,   EMPTY,  EMPTY,
                EMPTY,  X,       EMPTY,  EMPTY,
                EMPTY,  EMPTY,   X,      EMPTY,
                EMPTY,  O,       O,      X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, O);
        assertEquals(O, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void firstMoveIsAI_3x3() {
        Mark currentBoard[] = {
                EMPTY, EMPTY, EMPTY ,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, O);
        assertEquals(O, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void firstMoveIsAI_RandomlySelectedStrategy_4x4() {
        Mark currentBoard[] = {
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        int nextMove = computerXPlayer.getNextPosition(board);
        board = board.playCounterInPosition(nextMove, X);
        assertThat(board.getCells(), hasItem(X));
    }

    @Test
    public void aiPlayerShouldBlockOpponentWin_1() {
        Mark currentBoard[] = {
                X,      X,  EMPTY,
                EMPTY,  O,  X,
                O,      X,  O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        assertEquals(3, nextMove);
    }

    @Test
    public void aiPlayerShouldBlockOpponentWin_2() {
        Mark currentBoard[] = {
                EMPTY,  EMPTY,  X,
                O,      X,      EMPTY,
                EMPTY,  EMPTY,  EMPTY
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        assertEquals(7, nextMove);
    }

    @Test
    public void aiPlayerShouldChooseWinningPosition() {
        Mark currentBoard[] = {
                X,      EMPTY,  EMPTY,
                EMPTY,  O,      EMPTY,
                O,      EMPTY,  X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        assertEquals(3, nextMove);
    }

    @Test
    public void aiPlayerShouldPicksCenter() {
        Mark currentBoard[] = {
                X,      EMPTY, EMPTY,
                EMPTY,  EMPTY, EMPTY,
                EMPTY,  EMPTY, EMPTY,
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, O);
        assertEquals(O, board.findMarkAtDisplayPosition(nextMove));
    }

    @Test
    public void aiVsPerfectPlayerAIMustPickAnEdge() {
        Mark currentBoard[] = {
                X,      EMPTY,  EMPTY,
                EMPTY,  O,      EMPTY,
                EMPTY,  EMPTY,  X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = computerOPlayer.getNextPosition(board);
        board.playCounterInPosition(nextMove, O);
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