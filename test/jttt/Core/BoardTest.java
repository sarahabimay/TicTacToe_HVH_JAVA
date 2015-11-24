package jttt.Core;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardTest {
    Mark X = Mark.X;
    Mark O = Mark.O;
    Mark EMPTY = Mark.EMPTY;

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
        assertEquals(X, boardWithMove.findCounterAtIndex(0));
    }

    @Test
    public void counterPlayedInOccupiedPosition() {
        Mark currentBoard[] = {
                X, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY
        };
        Board board = new Board(3, arrayToList(currentBoard));
        board = board.playCounterInPosition(1, O);
        assertEquals(Arrays.asList(
                X, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY
        ), board.getCells());
    }

    @Test
    public void foundRowWinInRow1_3x3Game() {
        Mark currentBoard[] = {
                X, X, X,
                O, O, EMPTY,
                EMPTY, EMPTY, EMPTY
        };
        Board playerXRowWin = new Board(3, arrayToList(currentBoard));
        assertEquals(true, playerXRowWin.foundWinInRow());
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
        assertEquals(true, playerXRowWin.foundWinInRow());
    }

    @Test
    public void foundWinInColumn1_3x3Game() {
        Mark currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };
        Board playerXColumnWin = new Board(3, arrayToList(currentBoard));
        assertEquals(true, playerXColumnWin.foundWinInColumn());
    }

    @Test
    public void foundWinInColumn2_4X4Game() {
        Mark currentBoard[] = {
                O, X, O, X,
                X, X, O, X,
                O, X, X, O,
                X, X, O, X
        };
        Board playerXColumnWin = new Board(4, arrayToList(currentBoard));
        assertEquals(true, playerXColumnWin.foundWinInColumn());
    }

    @Test
    public void foundDiagonalWin1_3x3Game() {
        Mark currentBoard[] = {
                X, O, O,
                O, X, X,
                O, O, X
        };
        Board playerXDiagonalWin = new Board(3, arrayToList(currentBoard));
        assertEquals(true, playerXDiagonalWin.foundWinInDiagonal());
    }

    @Test
    public void foundDiagonalWin2_4x4Game() {
        Mark currentBoard[] = {
                O, O, X, X,
                X, X, X, O,
                X, X, O, O,
                X, O, O, O
        };
        Board playerXDiagonalWin = new Board(4, arrayToList(currentBoard));
        assertEquals(true, playerXDiagonalWin.foundWinInDiagonal());
    }

    @Test
    public void findCounterXWinner_4x4() {
        Mark currentBoard[] = {
                O, O, X, X,
                X, X, X, O,
                X, X, O, O,
                X, O, O, O
        };
        Board playerXDiagonalWin = new Board(4, arrayToList(currentBoard));
        assertEquals(Mark.X, playerXDiagonalWin.findWinner());
    }

    @Test
    public void getWinner() {
        Mark currentBoard[] = {
                X, O, O,
                O, X, X,
                O, O, X
        };
        Board playerXDiagonalWin = new Board(3, arrayToList(currentBoard));
        assertEquals(X, playerXDiagonalWin.findWinner());
    }


    @Test
    public void itsADraw() {
        Mark currentBoard[] = {
                O, X, O,
                O, O, X,
                X, O, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(false, board.isAWinner());
    }

    @Test
    public void findWinInARow() {
        Mark currentBoard[] = {
                X, X, X,
                O, O, X,
                O, O, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(true, board.foundWinInRow());
    }

    @Test
    public void findWinInAColumn() {
        Mark currentBoard[] = {
                X, O, X,
                O, X, X,
                O, O, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(true, board.foundWinInColumn());
    }

    @Test
    public void findWinOnDiagonal1() {
        Mark currentBoard[] = {
                X, O, X,
                X, X, O,
                O, O, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(true, board.foundWinInDiagonal());
    }

    @Test
    public void foundWinOnOther_3x3Game() {
        Mark currentBoard[] = {
                O, O, X,
                O, X, X,
                X, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(true, board.foundWinInDiagonal());
    }

    @Test
    public void foundWinOnDiagonal_4x4Game() {
        Mark currentBoard[] = {
                O, O, X, X,
                X, X, X, O,
                X, X, O, O,
                X, O, O, O
        };
        Board board = new Board(4, arrayToList(currentBoard));
        assertEquals(true, board.foundWinInDiagonal());
    }

    @Test
    public void foundWinOnOtherDiagonal_4x4Game() {
        Mark currentBoard[] = {
                X, O, X, X,
                O, X, X, O,
                X, O, X, O,
                O, O, O, X
        };
        Board board = new Board(4, arrayToList(currentBoard));
        assertEquals(true, board.foundWinInDiagonal());
    }

    @Test
    public void findWinnerUsingLines() {
        Mark currentBoard[] = {
                X, X, X,
                O, O, O,
                O, X, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(true, board.hasAWinner());
    }

    @Test
    public void findWinnerUsingLines_ColumnWin() {
        Mark currentBoard[] = {
                X, O, X,
                O, O, X,
                O, X, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(true, board.hasAWinner());
    }

    @Test
    public void createListOfRowLines() {
        Mark currentBoard[] = {
                X, X, X,
                O, O, X,
                O, O, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(3, board.getRows().size());
        assertEquals(true, board.getRows().get(0).hasAWinner());
    }

    @Test
    public void createListOfColumnLines() {
        Mark currentBoard[] = {
                X, X, X,
                O, O, X,
                O, O, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(3, board.getColumns().size());
        assertEquals(true, board.getColumns().get(2).hasAWinner());
    }

    @Test
    public void winnerIsXCounter_RowWin() {
        Mark currentBoard[] = {
                X, X, X,
                O, O, O,
                O, X, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(X, board.findWinner());
    }

    @Test
    public void winnerIsXCounter_ColumnWin() {
        Mark currentBoard[] = {
                X, O, X,
                O, O, X,
                O, X, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(X, board.findWinner());
    }

    @Test
    public void thereIsNoWinningCounter() {
        Mark currentBoard[] = {
                X, O, X,
                X, O, O,
                O, X, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
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
