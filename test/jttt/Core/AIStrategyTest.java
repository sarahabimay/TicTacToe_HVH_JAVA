package jttt.Core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class AIStrategyTest {
    private Counter X = Counter.X;
    private Counter O = Counter.O;
    private Counter E = Counter.EMPTY;
    private AlphaBetaStrategy alphaBetaStrategy;

    @Before
    public void setUp() throws Exception {
        alphaBetaStrategy = new AlphaBetaStrategy();
    }

    @Test
    public void startFromEmptyBoardThreeByThree() {
        Counter currentBoard[] = {
                E, E, E,
                E, E, E,
                E, E, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        int nextMove = alphaBetaStrategy.calculateNextMove(board, Counter.X );
        assertNotEquals(-1, nextMove);
        assertEquals(1, nextMove);
    }

    @Test
    public void oneChoiceForAlphaBetaStrategy() {
        Counter currentBoard[] = {
                X, O, X,
                O, X, X,
                E, O, O
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(7, alphaBetaStrategy.calculateNextMove(board, Counter.X ));
    }

    @Test
    public void twoChoicesForAlphaBetaStrategy() {
        Counter currentBoard[] = {
                X, X, E,
                X, O, X,
                O, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        assertEquals(3, alphaBetaStrategy.calculateNextMove(board, Counter.X ));
    }

    @Test
    public void fourChoicesForAlphaBetaStrategy() {
        Counter currentBoard[] = {
                X, X, E,
                E, O, X,
                E, O, E
        };
        Board board = new Board(3, arrayToList(currentBoard));
        Integer result = alphaBetaStrategy.calculateNextMove(board, Counter.X );
        assertEquals((Integer) 3, result);
    }

    @Test
    public void oneChoiceFor4x4BoardStrategy() {
        Counter currentBoard[] = {
                E, E, E, E,
                E, X, E, E,
                E, E, X, E,
                E, O, O, X,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        FourByFourAlphaBetaStrategy specialAlphaBeta = new FourByFourAlphaBetaStrategy();
        int nextMove = specialAlphaBeta.calculateNextMove(board, Counter.X );
        assertNotEquals(-1, nextMove);
        assertEquals(1, nextMove);
    }

    @Test
    public void startFromEmptyBoard4x4BoardStrategy() {
        Counter currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        FourByFourAlphaBetaStrategy specialAlphaBeta = new FourByFourAlphaBetaStrategy();
        int nextMove = specialAlphaBeta.calculateNextMove(board, Counter.X );
        assertNotEquals(-1, nextMove);
        assertThat(nextMove, isA(Integer.class));
    }

    @Test
    public void randomStrategyReturnsValidNumber() {
        Counter currentBoard[] = {
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
                E, E, E, E,
        };
        Board board = new Board(4, arrayToList(currentBoard));
        RandomStrategy randomStrategy = new RandomStrategy();
        int nextMove = randomStrategy.calculateNextMove(board, Counter.X);
        assertThat(nextMove, greaterThan(0));
        assertThat(nextMove, lessThanOrEqualTo(16));
        assertThat(nextMove, isA(Integer.class));
    }

    private List<Counter> arrayToList(Counter[] initialBoard) {
        List<Counter> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}
