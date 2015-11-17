package jttt.Core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game game;
    Counter X = Counter.X;
    Counter O = Counter.O;
    Counter EMPTY = Counter.EMPTY;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void noBoardCreatedYet() {
        Game game = new Game();
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
        game.setGameType(1); //1 == jttt.Core.GameType.HVH
        game.createPlayers();

        assertEquals(HumanPlayer.class, game.getPlayer(Counter.X).getClass());
        assertEquals(HumanPlayer.class, game.getPlayer(Counter.O).getClass());
    }

    @Test
    public void createGameFromInputs() {
        Game game = new Game(3, 1);

        assertEquals(9, game.getBoardSize());
        assertEquals(HumanPlayer.class, game.getPlayer(Counter.X).getClass());
        assertEquals(HumanPlayer.class, game.getPlayer(Counter.O).getClass());
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
        game.playMove(1);
        assertEquals(Counter.X, game.getBoard().getCells().get(0));
    }

    @Test
    public void askedForNextPlayer() {
        setUpGame(3, 1);
        Player playerA = game.getNextPlayer();
        game.playMove(1);
        Player playerB = game.getNextPlayer();
        assertEquals(Counter.O, playerB.getCounter());
    }

    @Test
    public void gameAskedForComputerMove() {
        setUpGame(3, 3); // 3 == boardSize; 3 == CVH
        Player playerA = game.getNextPlayer();
        // maybe game.getNextPlayerMove() then game.playMove() but then
        // either the UI would need to know about player types or
        // the jttt.Core.Player will need to know about the UI
        // I can't yet see yet how this should go
        game.playMove();
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
        game.setGameType(gameType); //1 == jttt.Core.GameType.HVH
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
