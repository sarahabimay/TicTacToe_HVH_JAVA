package jttt.core.board;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jttt.core.board.Mark.*;
import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void threeByThreeGameHasNineOpenPositions() {
        Board threeByThree = new Board(3);
        assertEquals(9, threeByThree.numberOfOpenPositions());
    }

    @Test
    public void fourByFourGameHasSixteenOpenPositions() {
        Board fourByFour = new Board(4);
        assertEquals(16, fourByFour.numberOfOpenPositions());
    }

    @Test
    public void rightNumberOfPositionsLeftAfterTwoMoves() {
        Board board = new Board(3);
        board.playCounterInPosition(2, X);
        board.playCounterInPosition(5, O);
        assertEquals(7, board.numberOfOpenPositions());
    }

    @Test
    public void counterPlayedInAPosition() {
        Board emptyBoard = new Board(3);
        Board boardWithMove = emptyBoard.playCounterInPosition(1, X);
        assertEquals(X, boardWithMove.findMarkAtIndex(0));
    }

    @Test
    public void counterPlayedInOccupiedPosition() {
        Mark currentBoard[] = {
                X,      EMPTY, EMPTY,
                EMPTY,  EMPTY, EMPTY,
                EMPTY,  EMPTY, EMPTY
        };
        Board board = new Board(3, arrayToList(currentBoard));
        board = board.playCounterInPosition(1, O);
        assertEquals(Arrays.asList(
                X,      EMPTY, EMPTY,
                EMPTY,  EMPTY, EMPTY,
                EMPTY,  EMPTY, EMPTY
        ), board.getCells());
    }

    @Test
    public void foundRowWinInRow1_3x3Game() {
        Mark currentBoard[] = {
                X,      X,      X,
                O,      O,      EMPTY,
                EMPTY,  EMPTY,  EMPTY
        };
        Board playerXRowWin = new Board(3, arrayToList(currentBoard));
        assertEquals(true, playerXRowWin.hasAWinner());
        assertEquals(X, playerXRowWin.findWinner());
    }

    @Test
    public void foundWinInRow3_4x4Game() {
        Mark currentBoard[] = {
                X, O, X, X,
                X, O, O, O,
                X, X, X, X,
                O, O, X, X
        };
        Board playerXRowWin = new Board(4, arrayToList(currentBoard));
        assertEquals(true, playerXRowWin.hasAWinner());
        assertEquals(X, playerXRowWin.findWinner());
    }

    @Test
    public void foundWinInColumn_3x3Game() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };
        Board playerXColumnWin = new Board(3, arrayToList(currentBoard));
        assertEquals(true, playerXColumnWin.hasAWinner());
        assertEquals(X, playerXColumnWin.findWinner());
    }

    @Test
    public void foundWinInColumn_4X4Game() {
        Mark currentBoard[] = {
                O, X, O, X,
                X, X, O, X,
                O, X, X, O,
                X, X, O, X
        };
        Board playerXColumnWin = new Board(4, arrayToList(currentBoard));
        assertEquals(X, playerXColumnWin.findWinner());
        assertEquals(true, playerXColumnWin.hasAWinner());
    }

    @Test
    public void foundDiagonalWin_3x3Game() {
        Mark currentBoard[] = {
                X, O, O,
                O, X, X,
                O, O, X
        };
        Board playerXDiagonalWin = new Board(3, arrayToList(currentBoard));
        assertEquals(X, playerXDiagonalWin.findWinner());
        assertEquals(true, playerXDiagonalWin.hasAWinner());
    }

    @Test
    public void foundDiagonalWin_4x4Game() {
        Mark currentBoard[] = {
                O, O, X, X,
                X, X, X, O,
                X, X, O, O,
                X, O, O, O
        };
        Board playerXDiagonalWin = new Board(4, arrayToList(currentBoard));
        assertEquals(X, playerXDiagonalWin.findWinner());
        assertEquals(true, playerXDiagonalWin.hasAWinner());
    }

    @Test
    public void itsADraw() {
        Mark currentBoard[] = {
                O, X, O,
                O, O, X,
                X, O, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(false, board.hasAWinner());
        assertEquals(EMPTY, board.findWinner());
    }

    @Test
    public void getSeveralRemainingPositions() {
        Mark currentBoard[] = {
                O,      O,      EMPTY,
                EMPTY,  X,      X,
                X,      O,      O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        ArrayList<Integer> openPositions = dummyListOfPositions(new int[]{2, 3});
        assertEquals(openPositions, board.remainingPositions());
        assertEquals(2, board.remainingPositions().size());
    }

    private ArrayList<Integer> dummyListOfPositions(int[] emptyPositions) {
        ArrayList<Integer> openPositions = new ArrayList<>();
        for (int i = 0; i < emptyPositions.length; i++) {
            openPositions.add(emptyPositions[i]);
        }
        return openPositions;
    }

    private List<Mark> arrayToList(Mark[] initialBoard) {
        List<Mark> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}
