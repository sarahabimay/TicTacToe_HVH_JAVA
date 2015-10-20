//package tictactoe;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardTest {
    Counter X = Counter.X;
    Counter O = Counter.O;
    Counter EMPTY = Counter.EMPTY;

    @Test
    public void threeByThreeGameHasNineOpenPositions() {
        Board threeByThree = new Board(3);
        assertEquals(9, threeByThree.numberOfOpenPositions());
    }

    @Test
    public void fourByFourGameHasNineOpenPositions() {
        Board fourByFour = new Board(4);
        assertEquals(16, fourByFour.numberOfOpenPositions());
    }

    @Test
    public void foundRowWinInRow1_3x3Game() {
        Counter currentBoard[] = {X, X, X,
                                  O, O, EMPTY,
                                  EMPTY, EMPTY, EMPTY};
        Board playerXRowWin = new Board(3, arrayToList(currentBoard));
        assertEquals(true, playerXRowWin.findRowWin(X));
    }

    @Test
    public void foundWinInRow3_4x4Game() {
        Counter currentBoard[] = {X, O, X, X,
                                  X, O, O, O,
                                  X, X, X, X,
                                  O, O, X, X};
        Board playerXRowWin = new Board(4, arrayToList(currentBoard));
        assertEquals(true, playerXRowWin.findRowWin(X));
    }

    @Test
    public void foundWinInColumn1_3x3Game() {
        Counter currentBoard[] = {X, O, O,
                                  X, O, X,
                                  X, X, O};
        Board playerXColumnWin = new Board(3, arrayToList(currentBoard));
        assertEquals(true, playerXColumnWin.findColumnWin(X));
    }

    @Test
    public void foundWinInColumn2_4X4Game() {
        Counter currentBoard[] = {O, X, O, X,
                                  X, X, O, X,
                                  O, X, X, O,
                                  X, X, O, X};
        Board playerXColumnWin = new Board(4, arrayToList(currentBoard));
        assertEquals(true, playerXColumnWin.findColumnWin(X));
    }

    @Test
    public void foundDiagonalWin1() {
        Counter currentBoard[] = {X, O, O,
                                  O, X, X,
                                  O, O, X};
        Board playerXDiagonalWin = new Board(3, arrayToList(currentBoard));
        assertEquals(true, playerXDiagonalWin.findDiagonalWin(X));
    }

    @Test
    public void foundDiagonalWin2_4x4Game() {
        Counter currentBoard[] = {O, O, X, X,
                                  X, X, X, O,
                                  X, X, O, O,
                                  X, O, O, O};
        Board playerXDiagonalWin = new Board(4, arrayToList(currentBoard));
        assertEquals(true, playerXDiagonalWin.findDiagonalWin(X));
    }

    @Test
    public void findWinner() {
        Counter currentBoard[] = {O, O, X, X,
                X, X, X, O,
                X, X, O, O,
                X, O, O, O};
        Board playerXDiagonalWin = new Board(4, arrayToList(currentBoard));
        assertEquals(true, playerXDiagonalWin.findWin(X));
    }

    @Test
    public void whoIsTheWinner() {
        Counter currentBoard[] = {X, O, O,
                O, X, X,
                O, O, X};
        Board playerXDiagonalWin = new Board(3, arrayToList(currentBoard));
        assertEquals(X, playerXDiagonalWin.isWinner());
    }

    @Test
    public void playsCounterInAPosition() {
        Board emptyBoard = new Board(3);
        Board boardWithMove = emptyBoard.playCounterInPosition(1, X);
        assertEquals(X, boardWithMove.cellValue(0));
    }

    @Test
    public void tryToMarkOccupiedPosition() {
        Board board = new Board(3);
        Board boardWithMove = board.playCounterInPosition(1, X);
        assertEquals(Arrays.asList(X, EMPTY, EMPTY,
                                   EMPTY, EMPTY, EMPTY,
                                   EMPTY, EMPTY, EMPTY),
                boardWithMove.getCells());

        boardWithMove = board.playCounterInPosition(1, O);
        assertEquals(X, boardWithMove.cellValue(0));
        assertEquals(Arrays.asList(X, EMPTY, EMPTY,
                                   EMPTY, EMPTY, EMPTY,
                                   EMPTY, EMPTY, EMPTY),
                boardWithMove.getCells());
    }

    @Test
    public void noWinnerGameOver() {
        Counter currentBoard[] = {O, X, O,
                                  O, O, X,
                                  X, O, X};
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(false, board.findWin());
    }

    @Test
    public void rightNumberOfPositionsLeftAfterTwoMoves() {
        Board board = new Board(3);
        board.playCounterInPosition(2, X);
        board.playCounterInPosition(5, O);
        assertEquals(7, board.numberOfOpenPositions());
    }

    private List<Counter> arrayToList(Counter[] initialBoard) {
        List<Counter> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }

}