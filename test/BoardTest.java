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
        assertEquals(X, boardWithMove.cellValue(0));
    }

    @Test
    public void counterPlayedInOccupiedPosition() {
        Counter currentBoard[] = {
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
        Counter currentBoard[] = {
                X, X, X,
                O, O, EMPTY,
                EMPTY, EMPTY, EMPTY
        };
        Board playerXRowWin = new Board(3, arrayToList(currentBoard));
        assertEquals(true, playerXRowWin.foundWinInRow());
    }

    @Test
    public void foundWinInRow3_4x4Game() {
        Counter currentBoard[] = {
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
        Counter currentBoard[] = {
                X, O, O,
                X, O, X,
                X, X, O
        };
        Board playerXColumnWin = new Board(3, arrayToList(currentBoard));
        assertEquals(true, playerXColumnWin.foundWinInColumn());
    }

    @Test
    public void foundWinInColumn2_4X4Game() {
        Counter currentBoard[] = {
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
        Counter currentBoard[] = {
                X, O, O,
                O, X, X,
                O, O, X
        };
        Board playerXDiagonalWin = new Board(3, arrayToList(currentBoard));
        assertEquals(true, playerXDiagonalWin.foundWinInDiagonal());
    }

    @Test
    public void foundDiagonalWin2_4x4Game() {
        Counter currentBoard[] = {
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
        Counter currentBoard[] = {
                O, O, X, X,
                X, X, X, O,
                X, X, O, O,
                X, O, O, O
        };
        Board playerXDiagonalWin = new Board(4, arrayToList(currentBoard));
        assertEquals(Counter.X, playerXDiagonalWin.findWinner());
    }

    @Test
    public void getWinner() {
        Counter currentBoard[] = {
                X, O, O,
                O, X, X,
                O, O, X
        };
        Board playerXDiagonalWin = new Board(3, arrayToList(currentBoard));
        assertEquals(X, playerXDiagonalWin.findWinner());
    }


    @Test
    public void itsADraw() {
        Counter currentBoard[] = {
                O, X, O,
                O, O, X,
                X, O, X
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(false, board.isAWinner());
    }

    @Test
    public void findWinInARow() {
        Counter currentBoard[] = {
                X, X, X,
                O, O, X,
                O, O, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(true, board.foundWinInRow());
    }

    @Test
    public void findWinInAColumn() {
        Counter currentBoard[] = {
                X, O, X,
                O, X, X,
                O, O, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(true, board.foundWinInColumn());
    }

    @Test
    public void findWinOnDiagonal1() {
        Counter currentBoard[] = {
                X, O, X,
                X, X, O,
                O, O, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(true, board.foundWinInDiagonal());
    }

    @Test
    public void foundWinOnOther_3x3Game() {
        Counter currentBoard[] = {
                O, O, X,
                O, X, X,
                X, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(true, board.foundWinInDiagonal());
    }

    @Test
    public void foundWinOnDiagonal_4x4Game() {
        Counter currentBoard[] = {
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
        Counter currentBoard[] = {
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
        Counter currentBoard[] = {
                X, X, X,
                O, O, O,
                O, X, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(true, board.hasAWinner());
    }

    @Test
    public void findWinnerUsingLines_ColumnWin() {
        Counter currentBoard[] = {
                X, O, X,
                O, O, X,
                O, X, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(true, board.hasAWinner());
    }


    @Test
    public void createListOfRowLines() {
        Counter currentBoard[] = {
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
        Counter currentBoard[] = {
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
        Counter currentBoard[] = {
                X, X, X,
                O, O, O,
                O, X, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(X, board.findWinner());
    }

    @Test
    public void winnerIsXCounter_ColumnWin() {
        Counter currentBoard[] = {
                X, O, X,
                O, O, X,
                O, X, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(X, board.findWinner());
    }

    @Test
    public void thereIsNoWinningCounter() {
        Counter currentBoard[] = {
                X, O, X,
                X, O, O,
                O, X, X
        };

        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(EMPTY, board.findWinner());
    }

    @Test
    public void scoreTheBoardWhenAIPlayerIsX() {
        Counter currentBoard[] = {
                O, O, X,
                O, X, X,
                X, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Counter aiCounter = X;
        assertEquals(10, board.calculateBoardScore(aiCounter));
    }

    @Test
    public void scoreTheBoardWhenAIPlayerIsO() {
        Counter currentBoard[] = {
                O, O, X,
                O, X, X,
                X, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Counter aiCounter = O;
        assertEquals(-10, board.calculateBoardScore(aiCounter));
    }

    private List<Counter> arrayToList(Counter[] initialBoard) {
        List<Counter> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}
