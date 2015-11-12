import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NewGameTest {

    private NewGame game;

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
        // maybe game.getNextPlayerMove() then game.playMove() but then UI would need to know
        // the difference between playerTypes.
        // I can't see 
        game.playMove(playerA);
        assertEquals(Counter.X, game.getBoard().getCells().get(0));
    }

    private void setUpGame(Integer dimension, Integer gameType) {
        game.setBoardDimension(dimension);
        game.setGameType(gameType); //1 == GameType.HVH
        game.createPlayers();
    }
}
