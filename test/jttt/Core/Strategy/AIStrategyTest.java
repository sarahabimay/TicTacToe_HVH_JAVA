package jttt.Core.Strategy;

import jttt.Core.Board;
import jttt.Core.Mark;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class AIStrategyTest {
    private Mark X = Mark.X;
    private Mark O = Mark.O;
    private Mark E = Mark.EMPTY;
    private AlphaBetaStrategy alphaBetaStrategy;

    @Before
    public void setUp() throws Exception {
        alphaBetaStrategy = new AlphaBetaStrategy();
    }

    @Test
    public void startFromEmptyBoardThreeByThree() {
        Mark currentBoard[] = {
                E, E, E,
                E, E, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = alphaBetaStrategy.calculateNextMove(board, Mark.X );
        assertNotEquals(-1, nextMove);
        assertEquals(1, nextMove);
    }

    @Test
    public void oneChoiceForAlphaBetaStrategy() {
        Mark currentBoard[] = {
                X, O, X,
                O, X, X,
                E, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(7, alphaBetaStrategy.calculateNextMove(board, Mark.X ));
    }

    @Test
    public void twoChoicesForAlphaBetaStrategy() {
        Mark currentBoard[] = {
                X, X, E,
                X, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(3, alphaBetaStrategy.calculateNextMove(board, Mark.X ));
    }

    @Test
    public void fourChoicesForAlphaBetaStrategy() {
        Mark currentBoard[] = {
                X, X, E,
                E, O, X,
                E, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int result = alphaBetaStrategy.calculateNextMove(board, Mark.X );
        assertEquals(3, result);
    }

    @Test
    public void oneChoiceFor4x4BoardStrategy() {
        Mark currentBoard[] = {
                E, E, E, E,
                E, X, E, E,
                E, E, X, E,
                E, O, O, X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        FourByFourAlphaBetaStrategy specialAlphaBeta = new FourByFourAlphaBetaStrategy();
        int nextMove = specialAlphaBeta.calculateNextMove(board, Mark.X );
        assertEquals(1, nextMove);
    }

    @Test
    public void startFromEmptyBoard4x4BoardStrategy() {
        Mark currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        FourByFourAlphaBetaStrategy specialAlphaBeta = new FourByFourAlphaBetaStrategy();
        int nextMove = specialAlphaBeta.calculateNextMove(board, Mark.X );
        assertNotEquals(-1, nextMove);
        assertThat(nextMove, isA(Integer.class));
    }

    @Test
    public void randomStrategyReturnsValidNumber() {
        Mark currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        RandomStrategy randomStrategy = new RandomStrategy();
        int nextMove = randomStrategy.calculateNextMove(board, Mark.X);
        assertThat(nextMove, greaterThan(0));
        assertThat(nextMove, lessThanOrEqualTo(16));
        assertThat(nextMove, isA(Integer.class));
    }

    private List<Mark> arrayToList(Mark[] initialBoard) {
        List<Mark> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}
