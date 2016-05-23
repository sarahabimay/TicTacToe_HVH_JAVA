package jttt.core.strategy;

import org.junit.Before;
import org.junit.Test;

import jttt.core.board.Board;
import jttt.core.board.Mark;
import java.util.ArrayList;
import java.util.List;

import static jttt.core.board.Mark.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.*;

public class AIStrategyTest {
    private AlphaBetaStrategy alphaBetaStrategy;

    @Before
    public void setUp() throws Exception {
        alphaBetaStrategy = new AlphaBetaStrategy();
    }

    @Test
    public void startFromEmptyBoardThreeByThree() {
        Mark currentBoard[] = {
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY,
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = alphaBetaStrategy.calculateNextMove(board, X);
        assertNotEquals(-1, nextMove);
        assertEquals(1, nextMove);
    }

    @Test
    public void oneChoiceForAlphaBetaStrategy() {
        Mark currentBoard[] = {
                X,      O,  X,
                O,      X,  X,
                EMPTY,  O,  O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(7, alphaBetaStrategy.calculateNextMove(board, X));
    }

    @Test
    public void twoChoicesForAlphaBetaStrategy() {
        Mark currentBoard[] = {
                X, X, EMPTY,
                X, O, X,
                O, O, EMPTY
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(3, alphaBetaStrategy.calculateNextMove(board, X));
    }

    @Test
    public void fourChoicesForAlphaBetaStrategy() {
        Mark currentBoard[] = {
                X,      X, EMPTY,
                EMPTY,  O, X,
                EMPTY,  O, EMPTY
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int result = alphaBetaStrategy.calculateNextMove(board, X);
        assertEquals(3, result);
    }

    @Test
    public void oneChoiceFor4x4BoardStrategy() {
        Mark currentBoard[] = {
                EMPTY,  EMPTY,  EMPTY,  EMPTY,
                EMPTY,  X,      EMPTY,  EMPTY,
                EMPTY,  EMPTY,  X,      EMPTY,
                EMPTY,  O,      O,      X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        FourByFourAlphaBetaStrategy specialAlphaBeta = new FourByFourAlphaBetaStrategy();
        int nextMove = specialAlphaBeta.calculateNextMove(board, X);
        assertEquals(1, nextMove);
    }

    @Test
    public void startFromEmptyBoard4x4BoardStrategy() {
        Mark currentBoard[] = {
                EMPTY,  EMPTY,  EMPTY,  EMPTY,
                EMPTY,  EMPTY,  EMPTY,  EMPTY,
                EMPTY,  EMPTY,  EMPTY,  EMPTY,
                EMPTY,  EMPTY,  EMPTY,  EMPTY,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        FourByFourAlphaBetaStrategy specialAlphaBeta = new FourByFourAlphaBetaStrategy();
        int nextMove = specialAlphaBeta.calculateNextMove(board, X);
        assertNotEquals(-1, nextMove);
    }

    @Test
    public void randomStrategyReturnsValidNumber() {
        Mark currentBoard[] = {
                EMPTY,  EMPTY,  EMPTY,  EMPTY,
                EMPTY,  EMPTY,  EMPTY,  EMPTY,
                EMPTY,  EMPTY,  EMPTY,  EMPTY,
                EMPTY,  EMPTY,  EMPTY,  EMPTY,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        RandomStrategy randomStrategy = new RandomStrategy();
        int nextMove = randomStrategy.calculateNextMove(board, X);
        assertThat(nextMove, greaterThan(0));
        assertThat(nextMove, lessThanOrEqualTo(16));
    }

    private List<Mark> arrayToList(Mark[] initialBoard) {
        List<Mark> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}
