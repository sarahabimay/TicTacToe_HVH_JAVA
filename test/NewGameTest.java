import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NewGameTest {

    private NewGame game;
    Counter X = Counter.X;
    Counter O = Counter.O;
    Counter EMPTY = Counter.EMPTY;

    @Before
    public void setUp() {
        game = new NewGame();
    }

    @Test
    public void noBoardCreatedYet() {
        NewGame game = new NewGame();
        assertEquals(0, game.getBoardSize());
    }

    @Test
    public void gameReceivesBoardSize() {
        game.setBoardDimension(3);
        assertEquals(9, game.getBoardSize());
    }

    @Test
    public void gameReceivesValidGameType() {
        game.setBoardDimension(3);
        game.setGameType(1); //1 == GameType.HVH
        game.createPlayers();

        assertEquals(HumanPlayer.class, game.getPlayers().get(0).getClass());
        assertEquals(HumanPlayer.class, game.getPlayers().get(1).getClass());
    }

    @Test
    public void askedForFirstPlayer() {
        setUpGame(3, 1);
        Player playerA = game.getNextPlayer();
        assertEquals(Counter.X, playerA.getCounter());
    }

    @Test
    public void gameAskedToUpdateBoardWithHumanPlayerMove() {
        setUpGame(3, 1);
        Player playerA = game.getNextPlayer();
        game.playMove(1, playerA);
        assertEquals(Counter.X, game.getBoard().getCells().get(0));
    }

    @Test
    public void askedForNextPlayer() {
        setUpGame(3, 1);
        Player playerA = game.getNextPlayer();
        game.playMove(1, playerA);
        Player playerB = game.getNextPlayer();
        assertEquals(Counter.O, playerB.getCounter());
    }

    @Test
    public void gameAskedForComputerMove() {
        setUpGame(3, 3); // 3 == boardSize; 3 == CVH
        Player playerA = game.getNextPlayer();
        // maybe game.getNextPlayerMove() then game.playMove() but then
        // either the UI would need to know about player types or
        // the Player will need to know about the UI
        // I can't yet see yet how this should go
        game.playMove(playerA);
        assertEquals(Counter.X, game.getBoard().getCells().get(0));
    }

    @Test
    public void gameAskedForBoard() {
        setUpGame(3, 1);
        Board board = new Board(3);
        assertEquals(board.isEmpty(), game.getBoard().isEmpty());
    }

    @Test
    public void gameAskedToPlay() {
        int dimension = 3;
        int gameType = 1;
        game.play(dimension, gameType);

    }

    @Test
    public void gameAskedForResult() {
        setUpGame(3, 3);
        Counter currentBoard[] = {
                X, O, X,
                O, O, X,
                O, X, X
        };

        Board board = new Board(3, arrayToList(currentBoard));

    }

    private void setUpGame(Integer dimension, Integer gameType) {
        game.setBoardDimension(dimension);
        game.setGameType(gameType); //1 == GameType.HVH
        game.createPlayers();
    }

    private List<Counter> arrayToList(Counter[] initialBoard) {
        List<Counter> initialCells = new ArrayList<>(initialBoard.length);
        for (int i = 0; i < initialBoard.length; i++) {
            initialCells.add((initialBoard[i]));
        }
        return initialCells;
    }
}
