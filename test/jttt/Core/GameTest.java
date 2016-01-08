package jttt.Core;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.Players.HumanPlayer;
import jttt.Core.Players.Player;
import jttt.Core.Players.PlayerFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private final int DEFAULT_DIMENSION = 3;
    private final int ZERO_DIMENSION_BOARD = 0;
    private Game defaultGame;
    private Game zeroGame;

    @Before
    public void setUp() {
        zeroGame = new Game(new Board(ZERO_DIMENSION_BOARD), GameType.HVH.getNumericGameType(), new PlayerFactory());
        defaultGame = new Game(new Board(DEFAULT_DIMENSION), GameType.HVH.getNumericGameType(), new PlayerFactory());
    }

    @Test
    public void noBoardCreatedYet() {
        assertEquals(0, zeroGame.getBoardSize());
    }

    @Test
    public void gameReceivesBoardSize() {
        assertEquals(9, defaultGame.getBoardSize());
    }

    @Test
    public void gameReceivesValidGameType() {
        assertEquals(HumanPlayer.class, defaultGame.getPlayer(Mark.X).getClass());
        assertEquals(HumanPlayer.class, defaultGame.getPlayer(Mark.O).getClass());
    }

    @Test
    public void createGameFromInputs() {
        assertEquals(9, defaultGame.getBoardSize());
        assertEquals(HumanPlayer.class, defaultGame.getPlayer(Mark.X).getClass());
        assertEquals(HumanPlayer.class, defaultGame.getPlayer(Mark.O).getClass());
    }

    @Test
    public void askedForFirstPlayer() {
        Player playerA = defaultGame.getNextPlayer();
        assertEquals(Mark.X, playerA.getMark());
    }

    @Test
    public void gameAskedForBoard() {
        Board board = new Board(DEFAULT_DIMENSION);
        assertEquals(board.isEmpty(), defaultGame.getBoard().isEmpty());
    }
}
