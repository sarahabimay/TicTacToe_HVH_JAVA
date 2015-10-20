import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    Counter X = Counter.X;
    Counter O = Counter.O;
    Counter EMPTY = Counter.EMPTY;
    public Game fourByFourGame;
    public Game threeByThreeGame;

    @Before
    public void setUp() throws Exception {
        fourByFourGame = new Game(new Board(4), new Player(Counter.X), new Player(Counter.O));
        threeByThreeGame = new Game(new Board(3), new Player(Counter.X), new Player(Counter.O));
    }

    @Test
    public void resetBoard() {
        threeByThreeGame.resetBoard();
        assertEquals("[1][2][3]\n" +
                        "[4][5][6]\n" +
                        "[7][8][9]\n",
                threeByThreeGame.displayBoard());
    }

    @Test
    public void reset4x4Board() {
        fourByFourGame.resetBoard();
        assertEquals("[1][2][3][4]\n" +
                        "[5][6][7][8]\n" +
                        "[9][10][11][12]\n" +
                        "[13][14][15][16]\n",
                fourByFourGame.displayBoard());
    }

    @Test
    public void playTheGame() {
        Counter currentBoard[] = {X, X, X,
                O, O, EMPTY,
                EMPTY, EMPTY, EMPTY};

        Board board = new Board(3, arrayToList(currentBoard));
        Game game = new Game(board, new Player(Counter.X), new Player(Counter.O));
        game.play();
        assertEquals("[X][X][X]\n" +
                        "[O][O][6]\n" +
                        "[7][8][9]\n",
                game.displayBoard());
        assertEquals(true, game.foundWin());
    }

    @Test
    public void playWinningMove() {
        Counter currentBoard[] = {X, X, X,
                O, O, EMPTY,
                EMPTY, EMPTY, EMPTY};

        Board board = new Board(3, arrayToList(currentBoard));
        Game game = new Game(board, new Player(Counter.X), new Player(Counter.O));
        game.play();
        assertEquals("[X][X][X]\n" +
                        "[O][O][6]\n" +
                        "[7][8][9]\n",
                game.displayBoard());
        assertEquals(true, game.foundWin());
    }

    @Test
    public void playTwoMoves() {
        threeByThreeGame.resetBoard();
        threeByThreeGame.playNextMove(7);
        threeByThreeGame.playNextMove(6);
        assertEquals("[1][2][3]\n" +
                        "[4][5][O]\n" +
                        "[X][8][9]\n",
                threeByThreeGame.displayBoard());
    }

    @Test
    public void promptForNextXMove() {
        threeByThreeGame.resetBoard();
        assertEquals("Player(X) please choose a position:\n", threeByThreeGame.requestNextMove());
    }

    @Test
    public void playNextMoveAfterPrompt() {
        threeByThreeGame.playNextMove(4);
        assertEquals("[1][2][3]\n" +
                        "[X][5][6]\n" +
                        "[7][8][9]\n",
                threeByThreeGame.displayBoard());
        assertEquals(Counter.O, threeByThreeGame.getCurrentMarker());
    }

    private List<Counter> arrayToList(Counter[] initialBoard) {
        List<Counter> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }

}